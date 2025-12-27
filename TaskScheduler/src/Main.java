import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

enum TaskFrequency {
    RECURRING,
    ONETIME
}

interface TaskSchduleStrategy {
    Long updateNextTaskExecutionTime(Long lastExecutionTime);
}

class RecurringTaskExecutionStrategy implements TaskSchduleStrategy{
    final Long taskFrequency;
    final Long firstRun;

    RecurringTaskExecutionStrategy(Long taskFrequency, Long firstRun) {
        this.taskFrequency = taskFrequency;
        this.firstRun = firstRun;
    }

    @Override
    public Long updateNextTaskExecutionTime(Long lastExecutionTime) {
        if(lastExecutionTime == null) return firstRun;
        return lastExecutionTime + taskFrequency;
    }
}

class OneTimeExecutionStrategy implements TaskSchduleStrategy {
    final Long executionTime;

    OneTimeExecutionStrategy(Long executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public Long updateNextTaskExecutionTime(Long lastExecutionTime) {
        if(lastExecutionTime == null){
            return executionTime;
        }
        return null;
    }
}

abstract class Task {
    abstract void execute();
}

class SendEmailTask extends Task {
    private final String email;
    SendEmailTask(String email) {
        this.email = email;
    }


    @Override
    public void execute() {
        try {
            System.out.println("Sending email to " + email);
            Thread.sleep(5000);
            System.out.println("Email Sent for " + email);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class CheckForNewMessage extends Task {
    private final String email;
    CheckForNewMessage(String email) {
        this.email = email;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Checking for new message " + email);
            Thread.sleep(3000);
            System.out.println("Message checked " + email + " Messaged Recieved ");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class ScheduledTask{
    final Task task;
    final TaskSchduleStrategy strategy;
    Long lastExecutionTime;
    Long nextExecutionTime;

    ScheduledTask(Task task, TaskSchduleStrategy strategy) {
        this.task = task;
        this.strategy = strategy;
        evaluateNextExecutionTime();
    }

    private void evaluateNextExecutionTime() {
        nextExecutionTime = strategy.updateNextTaskExecutionTime(lastExecutionTime);
    }

    public void runTask(){
        task.execute();
        updateExecutionTime();
    }

    private void updateExecutionTime(){
        lastExecutionTime = System.currentTimeMillis();
        evaluateNextExecutionTime();
    }
}

class Workers extends Thread{
    final ArrayBlockingQueue<ScheduledTask> blockingQueue;

    public Workers(ArrayBlockingQueue<ScheduledTask> blockingQueue){
        this.blockingQueue = blockingQueue;
        this.setDaemon(true);
    }

    @Override
    public void run() {
        while (true){
            try {
                ScheduledTask task = blockingQueue.take();
                task.runTask();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class TaskService {

    private static final TaskService instance = new TaskService();
    final int QUEUE_SIZE = 10;
    final int WORKER_COUNT = 5;
    final ArrayBlockingQueue<Task> blockingQueue;
    Workers[] workers;

    private TaskService(){
        blockingQueue = new ArrayBlockingQueue<>(QUEUE_SIZE);
        workers = new Workers[WORKER_COUNT];
        startWorkers();
    }

    private void startWorkers(){
        for(int i=0;i<WORKER_COUNT;i++){
            workers[i] = new Workers(blockingQueue);
            workers[i].start();
        }
    }

    public void submitTask(Task task){
        blockingQueue.offer(task);
    }

    public static TaskService getInstance(){
        return instance;
    }
}

public class Main {
    // Functional Reuquirement :
    // 1. Users should be able to submit tasks
    // 2. Tasks can be recurring or one time execution --> 2 ways to achive complete tasks (May be strategy pattern)
    // Task (asbtract class) , TaskService --> Submit the task, Inside the task i will workers which will be picking the tasks

    public static void main(String[] args) {
        List<String> emails = Arrays.asList("lagger","raman","aman","naman");
        TaskService taskService = TaskService.getInstance();
        for(String email: emails){
            SendEmailTask sendEmailTask = new SendEmailTask(email);
            CheckForNewMessage checkForNewMessage = new CheckForNewMessage(email);
            taskService.submitTask(sendEmailTask);
            taskService.submitTask(checkForNewMessage);
        }

        try {
            Thread.sleep(10000);
            taskService.submitTask(new CheckForNewMessage("newEmail"));
            Thread.sleep(3000);
            taskService.submitTask(new SendEmailTask("newEmail"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}