import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Enum.RequestSource;
import Enum.Direction;
import Models.ElevatorCar;
import Models.ElevatorController;
import Models.Request;
import Strategy.*;


public class ElevatorSystem {
    private static ElevatorSystem instance;

    HashMap<Integer, ElevatorController> elevatorControllers;
    ElevatorSelectionStrategy elevatorSelectionStrategy;
    ExecutorService threadPool;
    int minFloor;
    int maxFloor;


    private ElevatorSystem(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        elevatorControllers = new HashMap<>();
        threadPool = Executors.newFixedThreadPool(4);

        for(int i=0;i<4;i++){
            ElevatorController controller = new ElevatorController(new ElevatorCar(i,0, Direction.IDLE,minFloor,maxFloor));
            controller.setStrategy(new ScanAlgorithmStrategy());
            threadPool.submit(controller);
            elevatorControllers.put(i,controller);
        }

        elevatorSelectionStrategy = new OddEvenSelectionStrategy();
    }

    public static ElevatorSystem getInstance(int minFloor, int maxFloor){
        if(instance == null){
            synchronized (ElevatorSystem.class){
                if(instance == null){
                    instance = new ElevatorSystem(minFloor, maxFloor);
                }
            }
        }
        return instance;
    }

    // floor from where request is initiated
    public int getExternalRequest(int floor, Direction direction){
        if(floor< minFloor || floor > maxFloor) {
            throw new IllegalArgumentException("Invalid floor Provided");
        }

        Request request = new Request(floor,direction, RequestSource.EXTERNAL);
        int controllerId = elevatorSelectionStrategy.getElevatorController(request,elevatorControllers);
        ElevatorController controller = elevatorControllers.get(controllerId);
        if(controller == null){
            System.err.println("Error in accepting request for floor " + floor);
            return -1;
        } else {
            System.out.println("Elevator " + controllerId + " recieved External Request from " + floor + " floor " + " Direction " + direction.toString());
            controller.acceptRequest(request);
            return controllerId;
        }
    }

    public void getInternalRequest(int elevatorId,int destinationFloor){
        if(destinationFloor< minFloor || destinationFloor > maxFloor) {
            throw new IllegalArgumentException("Invalid floor Provided");
        }

        Request request = new Request(destinationFloor,null, RequestSource.INTERNAL);
        ElevatorController elevatorController = elevatorControllers.get(elevatorId);

        if(elevatorController == null){
            System.err.println("Invalid Elevator Id");
        } else {
            System.out.println("Elevator " + elevatorId + " recieved Internal Request for " + destinationFloor + " floor");
            elevatorController.acceptRequest(request);
        }
    }

    public void setElevatorSelectionStrategy(ElevatorSelectionStrategy elevatorSelectionStrategy) {
        this.elevatorSelectionStrategy = elevatorSelectionStrategy;
    }
}
