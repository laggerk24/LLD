import Enum.*;

public class Main {
    public static void main(String[] args) {
        ElevatorSystem system = ElevatorSystem.getInstance(0,10);
        Thread request1 = new Thread(() -> {
            int elevatorId = system.getExternalRequest(1, Direction.Up);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            system.getInternalRequest(elevatorId,8);
        });

        Thread request2 = new Thread(() -> {
            int elevatorId = system.getExternalRequest(1, Direction.Up);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            system.getInternalRequest(elevatorId,5);
        });

        Thread request3 = new Thread(() -> {
            int elevatorId = system.getExternalRequest(0, Direction.Up);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            system.getInternalRequest(elevatorId,2);
        });

        request1.start();
//        request2.start();
//        request3.start();
    }
}