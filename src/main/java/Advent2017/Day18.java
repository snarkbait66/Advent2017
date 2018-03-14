package Advent2017;

import util.AdventOfCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day18 extends AdventOfCode {

    Map<Character, Long> registers;
    List<Instruction> instructions;

    public Day18(List<String> input) {
        super(input);
        part1Description = "Value of the recovered frequency: ";
        part2Description = "Number of sends by program 1: ";
    }

    @Override
    public Object part1() {
        Program part1 = new Program(instructions, registers, true);
        long result = 0L;
        while (result == 0L) {
            result = part1.next();
        }
        return result;
    }

    @Override
    public Object part2() {
        //reset
        parse();
        Program zero = new Program(instructions, registers, false);

        // clone registers
        Map<Character, Long> register2 = new HashMap<>();
        register2.putAll(registers);
        register2.put('p', 1L);

        Program one = new Program(instructions, register2, false);

        // pair
        zero.pairWith(one);
        one.pairWith(zero);

        // run
        long sends = 0L;
        while (!zero.isLocked() || !one.isLocked()) {
            if (!zero.isLocked()) zero.next();
            if (!one.isLocked()) sends = one.next();

        }
        return sends;
    }


    @Override
    public void parse() {
        instructions = new ArrayList<>();
        registers = new HashMap<>();
        for (String each : input) {
            String[] instr = each.split(" ");
            Command cmd = Command.valueOf(instr[0]);
            char register = instr[1].charAt(0);
            registers.putIfAbsent(register, 0L);
            if (register == '1') registers.put('1', 1L); /// OMG TOPAZ YOU BASTARD
            long y = 0;
            char toRegister = 'x';
            if (instr.length > 2) {
                try {
                    y = (long) Integer.parseInt(instr[2]);
                } catch (NumberFormatException e) {
                    toRegister = instr[2].charAt(0);
                }
            }
            instructions.add(new Instruction(cmd, register, 0L, y, toRegister));
        }

    }
}