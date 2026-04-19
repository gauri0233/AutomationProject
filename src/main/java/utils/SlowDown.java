package utils;

public class SlowDown {

    public static void pause(int seconds) {
        try {
            System.out.println("    [WAIT] Waiting " + seconds + " second(s)...");
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}