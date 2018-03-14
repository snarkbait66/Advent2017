package Advent2017;

import util.AdventOfCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day23 extends AdventOfCode {
    Map<Character, Long> registers;
    List<Instruction> instructions;
    int position;
    int mulcount;

    public Day23(List<String> input) {
        super(input);
        part1Description = "Number of times multiply is called: ";
        part2Description = "Value of registersCopy 'h': ";
    }

    private void process() {
        while (position < instructions.size()) {
            Instruction current = instructions.get(position);
            long y = current.y;
            long x = current.x;
            if (current.toRegister != 'x') y = registers.get(current.toRegister);
            if (current.register != 'x')  x = registers.get(current.register);
            long result = current.cmd.apply(x, y);
            if (current.cmd == Command.mul) mulcount++;
            switch (current.cmd) {
                case jnz:
                    position += result;
                    break;
                default:
                    registers.put(current.register, result);
                    position++;
            }
        }
    }

    @Override
    public Object part1() {
        process();
        return mulcount;
    }

    @Override
    public Object part2() {
        parse();
        registers.put('a', 1L);
        process();
        return registers.get('h');
    }

    @Override
    public void parse() {
        position = 0;
        instructions = new ArrayList<>();
        registers = new HashMap<>();
        for (String each : input) {
            String[] instr = each.split(" ");
            Command cmd = Command.valueOf(instr[0]);
            char register = 'x';
            long x = 0;
            try {
                x = (long) Integer.parseInt(instr[1]);
            } catch (NumberFormatException e) {
                register = instr[1].charAt(0);
                registers.putIfAbsent(register, 0L);
            }
            long y = 0;
            char toRegister = 'x';
            if (instr.length > 2) {
                try {
                    y = (long) Integer.parseInt(instr[2]);
                } catch (NumberFormatException e) {
                    toRegister = instr[2].charAt(0);
                }
            }
            instructions.add(new Instruction(cmd, register, x, y, toRegister));
        }
    }

}