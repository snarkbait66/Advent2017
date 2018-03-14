package Advent2017;

import util.AdventOfCode;

import java.util.List;

public class Day5 extends AdventOfCode {

    interface Updater {
        int update(int x);
    }

    private int[] nums;

    public Day5(List<String> input) {
        super(input);
        title = "A Maze of Twisty Trampolines, All Alike";
        part1Description = "Steps to reach exit: ";
        part2Description = "Steps to reach exit part 2: ";
    }

    private int solve(Updater updater) {
        int current = 0;
        int steps = 0;

        while (current < nums.length) {
            int jump = nums[current];
            nums[current] += updater.update(nums[current]);
            current += jump;
            steps++;
        }
        return steps;
    }

    @Override
    public Object part1() {
        return solve(x -> 1);
    }

    @Override
    public Object part2() {
        parse();
        return solve(x -> x >= 3 ? -1 : 1);
    }

    @Override
    public void parse() {
        nums = input.stream()
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
