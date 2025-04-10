public class task1 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Thread is running...");
                synchronized (task1.class) {
                    task1.class.wait();
                }
            } catch (InterruptedException e) {
                System.out.println("Thread was failed");
            }
        });

        System.out.println("State after creation: " + thread.getState());
        thread.start();
        System.out.println("State after starting: " + thread.getState());

        sleep(100);
        System.out.println("State after delay: " + thread.getState());
        synchronized (task1.class) {
            System.out.println("State after synchronize : " + thread.getState());
        }

        synchronized (task1.class) {
            task1.class.notify();
        }
        sleep(100);
        System.out.println("State after notifying: " + thread.getState());

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("State after termination: " + thread.getState());
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
