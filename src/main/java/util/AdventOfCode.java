package util;

import java.util.List;

/**
 * @author /u/Philboyd_Studge on 10/20/2017.
 */
public abstract class AdventOfCode {

    protected List<String> input;
    protected String title = "";
    protected String part1Description = "Part 1: ";
    protected String part2Description = "Part 2: ";

    public AdventOfCode(List<String> input) {
        this.input = input;
        parse();
    }

    public abstract Object part1();
    public abstract Object part2();
    public void parse() {}

    public void run() {
        System.out.println(part1Description + part1().toString());
        System.out.println(part2Description + part2().toString());
    }
}
