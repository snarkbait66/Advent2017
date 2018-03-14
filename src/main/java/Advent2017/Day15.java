package Advent2017;

import util.AdventOfCode;

import java.util.List;

public class Day15 extends AdventOfCode {

    Generator A;
    Generator B;

    class Generator {
        int factor;
        long last;
        public Generator(int seed, int factor) { last = seed; this.factor = factor; }
        public int next() {
            last = (last * (long) factor) % Integer.MAX_VALUE;
            return (int) last;
        }

        int next4() {
            int result = next();
            while ((last & 3) != 0) {
                result = next();
            }
            return result;
        }

        int next8() {
            int result = next();
            while ((last & 7) != 0) {
                result = next();
            }
            return result;
        }
    }
    public Day15(List<String> input) {
        super(input);
        part1Description = "Final count of matching pairs: ";
        part2Description = "Final count of pairs, part 2: ";
    }

    @Override
    public Object part1() {
        int matches = 0;
        for (int i = 0; i < 40000000; i++) {
            if ((A.next() & 0xFFFF) == (B.next() & 0xFFFF)) matches++;
        }
        return matches;
    }

    @Override
    public Object part2() {
        parse();
        int matches = 0;
        for (int i = 0; i < 5000000; i++) {
            if ((A.next4() & 0xFFFF) == (B.next8() & 0xFFFF)) {
                matches++;
            }
        }
        //System.out.println(opcount);
        return matches;
    }

    @Override
    public void parse() {
        A = new Generator(512, 16807);
        B = new Generator(191, 48271);
    }
}
