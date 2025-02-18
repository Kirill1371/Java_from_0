import java.util.LinkedList;
import java.util.Random;

public class task3 {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5); 
        Thread producer = new Thread(new Producer(buffer), "Producer");
        Thread consumer = new Thread(new Consumer(buffer), "Consumer");
        producer.start();
        consumer.start();
    }
}

class Buffer {
    private final LinkedList<Integer> buffer;
    private final int capacity;

    public Buffer(int capacity) {
        this.buffer = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void add(int value) throws InterruptedException {
        while (buffer.size() == capacity) {
            wait(); 
        }
        buffer.add(value);
        System.out.println("Produced: " + value);
        notifyAll(); 
    }

    public synchronized int remove() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait(); 
        }
        int value = buffer.removeFirst();
        System.out.println("Consumed: " + value);
        notifyAll(); 
        return value;
    }
}

class Producer implements Runnable {
    private final Buffer buffer;
    private final Random random = new Random();

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int value = random.nextInt(100); 
                buffer.add(value); // Добавляем его в буфер
                Thread.sleep(random.nextInt(500)); 
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {
    private final Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                buffer.remove(); 
                Thread.sleep(new Random().nextInt(500));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
