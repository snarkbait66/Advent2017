package Advent2017;

import util.AdventOfCode;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.ToIntBiFunction;
import java.util.stream.Collectors;

public class Day8 extends AdventOfCode {

    private int highest;
    private Map<String, Integer> registers = new HashMap<>();
    private List<Instruction> instructions;

    // create map of functions for integer comparisons
    private static final Map<String, BiPredicate<Integer, Integer>> comparisons = new HashMap<>();

    static {
        comparisons.put("==", Integer::equals);
        comparisons.put("!=", (x, y) -> !x.equals(y));
        comparisons.put("<", (x, y) -> x < y);
        comparisons.put(">", (x, y) -> x > y);
        comparisons.put("<=", (x, y) -> x <= y);
        comparisons.put(">=", (x, y) -> x >= y);

    }

    // create map of functions for commands
    private static final Map<String, ToIntBiFunction<Integer, Integer>> commands = new HashMap<>();

    static {
        commands.put("inc", (x, y) -> x + y);
        commands.put("dec", (x, y) -> x - y);
    }

    public Day8(List<String> input) {
        super(input);
        title = "I Heard You Like Registers";
        part1Description = "Largest value in any register: ";
        part2Description = "Highest held value during process: ";
    }

    @Override
    public Object part1() {
        for (Instruction current : instructions) {

            // initialize register if new
            registers.putIfAbsent(current.register, 0);

            // test testRegister vs testAmount using proper comparison function
            if (comparisons.get(current.comparison)
                    .test(registers.getOrDefault(current.testRegister, 0),
                            current.testAmt)) {

                int amountToChange = registers.get(current.register);

                // apply amount to change using proper command function
                int changed = commands.get(current.command)
                        .applyAsInt(amountToChange, current.amount);
                registers.put(current.register, changed);

                if (changed > highest) {
                    highest = changed;
                }
            }
        }
        return Collections.max(registers.values());
    }

    @Override
    public Object part2() {
        return highest;
    }

    @Override
    public void parse() {
        instructions = input.stream()
                .map(x -> x.split(" "))
                .map(Instruction::new)
                .collect(Collectors.toList());
    }


    private class Instruction {
        final String register;
        final String command;
        final int amount;
        final String testRegister;
        final String comparison;
        final int testAmt;

        Instruction(String[] inp) {
            this.register = inp[0];
            this.command = inp[1];
            this.amount = Integer.parseInt(inp[2]);
            this.testRegister = inp[4];
            this.comparison = inp[5];
            this.testAmt = Integer.parseInt(inp[6]);
        }
    }
}
