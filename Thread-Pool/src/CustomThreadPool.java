import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CustomThreadPool {
    private final BlockingQueue<Runnable> taskQueue;
    private final List<Worker> workers;
    private final int maxThreads;
    private static volatile CustomThreadPool threadPool;

    private CustomThreadPool(int maxThreads,int maxQueueSize) {
        this.taskQueue = new ArrayBlockingQueue<>(maxQueueSize);
        this.maxThreads = maxThreads;
        workers = new ArrayList<>();
        for(int i=0;i<maxThreads;i++){
            Worker worker = new Worker(taskQueue);
            workers.add(worker);
            worker.start();
        }
    }

    public static CustomThreadPool getThreadPool(int maxThreads, int maxQueueSize){
        if(threadPool == null){
            synchronized (CustomThreadPool.class){
                if(threadPool == null){
                    return new CustomThreadPool(maxThreads,maxQueueSize);
                }
            }
        }
        return threadPool;
    }

    public void submit(Runnable task) throws InterruptedException {
        taskQueue.put(task);
    }

    public void shutdown(){
        for(Worker worker: workers){
            worker.stopTask();
        }
    }
}
