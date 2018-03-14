package util;

/**
 * Class Timer
 * Simple static program execution timer.
 * Singleton class, only one timer can be running at a time
 * @author /u/Philboyd_Studge on 12/26/2016.
 */
public class Timer {

    private Timer() {}

    private static long time;
    private static boolean timing;
    private static boolean nano;

    /**
     * Start millisecond timer
     * @throws IllegalStateException if timer is already running
     */
    public static void startTimer() {
        if (timing) {
            throw new IllegalStateException("Timer already running.");
        }
        if (nano) nano = false;
        time = System.currentTimeMillis();
        timing = true;
    }

    /**
     * start nanosecond timer
     * @throws IllegalStateException if timer is already running
     */
    public static void startNanoTimer() {
        if (timing) {
            throw new IllegalStateException("Timer already running.");
        }
        time = System.nanoTime();
        timing = true;
        nano = true;
    }

    /**
     * end the timer and return the elapsed time as a string
     * @return elapsed time as string
     * @throws IllegalStateException if timer has not been started
     */
    public static String endTimer() {
        if (!timing) {
            throw new IllegalStateException("Timer not running.");
        }
        timing = false;
        if (nano) {
            return (System.nanoTime() - time)  + " nanoseconds";
        }
        return ((System.currentTimeMillis() - time) / 1000.0) + " seconds";
    }
}
