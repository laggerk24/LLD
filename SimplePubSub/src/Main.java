// ===================== Buffer.java =====================
import java.util.LinkedList;
import java.util.Queue;

class Buffer {
    private Queue<Integer> queue = new LinkedList<>();
    private int capacity;

    public Buffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int value) throws InterruptedException {
        while (queue.size() == capacity) {  // buffer full
            wait();
        }

        queue.add(value);
        System.out.println("Produced: " + value);

        notifyAll();  // wake up consumer(s)
    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {  // buffer empty
            wait();
        }

        int value = queue.poll();
        System.out.println("Consumed: " + value);

        notifyAll();  // wake up producer(s)
        return value;
    }
}



// ===================== Producer.java =====================
class Producer extends Thread {
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int value = 0;

        try {
            while (true) {
                buffer.produce(value++);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}



// ===================== Consumer.java =====================
class Consumer extends Thread {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                buffer.consume();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}



// ===================== Main.java =====================
public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5); // queue capacity = 5

        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        producer.start();
        consumer.start();
    }
}
