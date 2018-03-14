package Advent2017;

import util.BitCommand;
import util.BitInstruction;
import util.FileIO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day2Bin {
    private List<BitInstruction> program;
    private Map<String, Long> registers;
    private long position;
    private long remainder;
    private long quotient;

    public Day2Bin() {
        parse();
    }

    public long getRemainder() { return remainder; }
    public long getQuotient() { return quotient; }


    void set(long a, long b) {
        registers.put("a", a);
        registers.put("b", b);
    }


    private Map<String, Long> copyRegisters() {
        return registers.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void process() {
        position = 0;
        Map<String, Long> registersCopy = copyRegisters();
        while (position < program.size()) {
            BitInstruction current = program.get((int) position);
            long x = current.value;
            long y = current.toValue;
            if (!current.toRegister.equals("null")) y = registersCopy.get(current.toRegister);
            if (!current.register.equals("null"))  x = registersCopy.get(current.register);
            if (current.cmd != null)  {
                long result = current.cmd.apply(x, y);
                switch (current.cmd) {
                    case JNZ:
                    case JZR:
                    case JGZ:
                        position += result;
                        break;
                    case JMP:
                        registersCopy.put(current.register, position + 1);
                        position = registersCopy.get(current.toRegister);
                        break;
                    case RET:
                        position = registersCopy.get(current.register);
                        break;
                    default:
                        registersCopy.put(current.register, result);
                        position++;
                }
            } else {
                position++;
            }
        }
        remainder = registersCopy.get("r");
        quotient = registersCopy.get("q");
        System.out.println(registersCopy.get("w"));
    }

    void parse() {
        List<String> input = FileIO.getFileAsList("day2_binary.txt");
        registers = new HashMap<>();
        program = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            BitCommand cmd;
            String[] split = input.get(i).split(" ");
            if (!split[0].startsWith("."))  {
                cmd = BitCommand.valueOf(split[0].toUpperCase());
            } else {
                registers.put(split[0].substring(1), (long) i);
                cmd = null;
            }
            String reg1 = "null";
            String reg2 = "null";
            long x = 0;
            long y = 0;
            if (split.length > 1) {
                try {
                    x = Long.parseLong(split[1]);
                } catch (NumberFormatException e) {
                    reg1 = split[1];
                    registers.putIfAbsent(reg1, 0L);
                }
                if (split.length > 2) {
                    try {
                        y = Long.parseLong(split[2]);
                    } catch (NumberFormatException e) {
                        reg2 = split[2];
                    }
                }
            }
            BitInstruction bi = new BitInstruction(cmd, reg1,  x, reg2, y);
            program.add(bi);
        }
    }

    public static void main(String[] args) {
        Day2Bin d = new Day2Bin();
        d.parse();
    }
}
