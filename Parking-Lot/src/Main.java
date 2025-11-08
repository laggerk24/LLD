import Enum.*;
import Models.Ticket;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void checkConcurrancy(int vehicleNumber,VehicleType vehicleType) throws InterruptedException {
        ParkingSystem parkingSystem = ParkingSystem.getInstance();
        ExecutorService executorService = Executors.newFixedThreadPool(vehicleNumber);
        Set<Ticket> set = ConcurrentHashMap.newKeySet();;

        CountDownLatch countDownLatch = new CountDownLatch(vehicleNumber);
        CyclicBarrier barrier = new CyclicBarrier(vehicleNumber);
        for(int i=0;i<vehicleNumber;i++){
            executorService.submit(()-> {
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                String vehicle = UUID.randomUUID().toString();
                Ticket ticket  = parkingSystem.parkVehicle(vehicle, VehicleType.TRUCK);
                if(ticket != null) set.add(ticket);
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        executorService.shutdown();

        Thread.sleep(2000);

        for(Ticket ticket: set){
            System.out.println("Parking price " + parkingSystem.unParkVehicle(ticket));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ParkingSystem parkingSystem = ParkingSystem.getInstance();
        parkingSystem.showAllParkingSpots();
//        for(int i=0;i<100;i++){
//            String vehicle = UUID.randomUUID().toString();
//            parkingSystem.parkVehicle(vehicle, VehicleType.TRUCK);
//        }
        checkConcurrancy(10,VehicleType.TRUCK);
//        parkingSystem.showAllParkingSpots();
    }

}