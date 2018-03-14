package Advent2017;

import util.AdventOfCode;
import util.ArrayUtils;

import java.util.List;

public class Day17 extends AdventOfCode {

    private int steps = 349;
    private final int ITERATIONS_PART1 = 2017;
    private final int ITERATIONS_PART2 = 50_000_000;
    private Integer[] buffer = { 0 };
    private int position = 0;
    private int num = 1;

    public Day17(List<String> input) {
        super(input);
        part1Description = "Value after '2017' in circular buffer: ";
        part2Description = "Value after '0' after 50 million spinlocks: ";
    }

    private void step() {
        position = (position + steps) % buffer.length;
        growAndMove();
    }

    private void growAndMove() {
        buffer = ArrayUtils.insert(buffer, num++, ++position );
    }

    @Override
    public Object part1() {
        while (num <= ITERATIONS_PART1) {
            step();
        }
        return buffer[position + 1];
    }

    /**
     * If you print out the first 20 or so of the arrays for part 1
     * after each iteration, you will see that position 0 always stays 0.
     * So, for part two we want the second value. Rebuilding the array everytime
     * will take too long so...
     * Notice that the number after the zero will ONLY CHANGE IF THE NEXT POSITION
     * LANDS ON ZERO, and the array grows by one every time. So we just do the iterations,
     * and just get the current position plus the step length, MODULUS the
     * current iteration number. This gives us the position of the next insertion point, and
     * the current iterations value will be the next number inserted in the second array
     * place.
     * @return
     */
    @Override
    public Object part2() {
        position = 0;
        int second = 0;
        for (int i = 1; i <= ITERATIONS_PART2; i++) {
            position = (position + steps) % i;
            if (position == 0) second = i;
            position++;
        }

        return second;
    }

    @Override
    public void parse() { }

}