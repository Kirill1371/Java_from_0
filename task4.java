import java.text.SimpleDateFormat;
import java.util.Date;

public class task4 extends Thread {
    private final int intervalInSeconds;

    public task4(int intervalInSeconds) {
        this.intervalInSeconds = intervalInSeconds;
    }

    @Override
    public void run() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        try {
            while (true) {
                String currentTime = formatter.format(new Date());
                System.out.println("Current time: " + currentTime);
                Thread.sleep(intervalInSeconds * 1000L);
            }
        } catch (InterruptedException e) {
            System.out.println("Time printer thread interrupted.");
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        task4 timePrinter = new task4(1);
        timePrinter.start();
    }
}
