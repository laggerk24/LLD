import java.util.concurrent.BlockingQueue;

public class Worker extends Thread{
    private BlockingQueue<Runnable> taskQueue;
    private volatile boolean isStopped;

    Worker(BlockingQueue<Runnable> taskQueue){
        this.taskQueue = taskQueue;
        this.isStopped = false;
    }

    @Override
    public void run() {
        while (!isStopped){
            try {
                Runnable task = taskQueue.take();
                task.run();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " Thread Stopped ");
            }
        }
    }

    public void stopTask(){
        isStopped = true;
        this.interrupt();
    }
}

