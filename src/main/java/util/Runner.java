package util;

public class Runner {

    private static void runAll(int year, int upToDay) {
        for (int i = 1; i <= upToDay; i++) {
            run(year, i);
        }
    }

    public static void run(int year, int day) {
        AdventOfCode challenge = DayLoader.getClassForDay(year, day);
        if (challenge == null) throw new RuntimeException("Unable to load class/input");
        Timer.startTimer();
        System.out.println("<===== Advent of Code Year: " + year + " - Day " + day + ": "
                + challenge.title + " =====>");
        challenge.run();
        System.out.println(Timer.endTimer());
    }

    public static void main(String[] args) {

        run(2017, 25);
    }
}
