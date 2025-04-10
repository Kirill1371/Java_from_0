public class task2 {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> printName("Thread-1"));
        Thread thread2 = new Thread(() -> printName("Thread-2"));

        thread1.start();
        thread2.start();
    }

    private static void printName(String name) {
        for (int i = 0; i < 5; i++) { 
            synchronized (lock) {
                System.out.println(name);
                try {
                    lock.notify(); 
                    lock.wait();   
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        synchronized (lock) {
            lock.notify(); 
        }
    }
}
