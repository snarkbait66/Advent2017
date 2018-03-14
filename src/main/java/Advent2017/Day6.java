package Advent2017;

import util.AdventOfCode;
import util.CircularList;

import java.util.*;
import java.util.stream.Collectors;

public class Day6 extends AdventOfCode {

    private int firstCycle;

    CircularList<Integer> nums;

    public Day6(List<String> input) {
        super(input);
        title = "Memory Reallocation";
        part1Description = "Redistribution cycles required: ";
        part2Description = "Number of cycles in the loop: ";
    }

    @Override
    public Object part1() {
        Map<CircularList<Integer>, Integer> seen = new HashMap<>();

        seen.put(nums, 0);
        int steps = 0;

        while (true) {
            int index = firstMax(nums);
            int redistribute = nums.get(index);
            nums.put(0, index++);
            while (redistribute-- > 0) {
                nums.put(nums.get(index) + 1, index++);
            }
            steps++;

            if (seen.containsKey(nums)) {
                firstCycle =  steps - seen.get(nums);
                break;
            }
            seen.put(nums, steps);


        }
        return steps;
    }

    @Override
    public Object part2() {
        return firstCycle;
    }

    @Override
    public void parse() {
        nums = new CircularList<>(Arrays.stream(input.get(0).split("\\s+"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList()));
    }


    @Override
    public void run() {
        super.run();
    }

    private static int firstMax(CircularList<Integer> a) {
        int max = 0;
        for (Integer num : a) {
            if (num > max) max = num;
        }
        return a.indexOf(max);
    }
}
