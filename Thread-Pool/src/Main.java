public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Initialize the thread pool with configuration (only allowed once)
        int maxThreads = 3;
        int queueSize = 5;

        CustomThreadPool threadPool = CustomThreadPool.getThreadPool(maxThreads, queueSize);

        // Submit some tasks
        for (int i = 0; i < 30; i++) {
            int taskId = i;
            try {
                threadPool.submit(() -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("Task " + taskId + " is running on " + threadName);
                    try {
                        Thread.sleep(1000); // simulate work
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }

        // Wait some time to allow tasks to complete
//        Thread.sleep(6000);

        // Shut down the thread pool
//        threadPool.shutdown();

        System.out.println("All tasks completed and thread pool shut down.");
    }
}
