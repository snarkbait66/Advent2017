package Advent2017;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Program {
    private int position;
    private Program pairedProgram;
    private Map<Character, Long> registers;
    private List<Instruction> instructions;
    private Queue<Long> queue = new LinkedList<>();
    private boolean locked;
    private boolean part1;
    private long lastSound;
    private int sends;

    public Program(List<Instruction> instructions, Map<Character, Long> registers, boolean part1) {
        this.instructions = instructions;
        this.registers = registers;
        this.part1 = part1;
    }

    public void pairWith(Program p) {
        this.pairedProgram = p;
    }

    public boolean isLocked() { return locked;}

    public void receive(long a) {
        if (locked) locked = false;
        queue.add(a);
    }

    public void send(long val) {
        sends++;
        pairedProgram.receive(val);
    }

    public long next() {
        Instruction curr = instructions.get(position);
        if (curr.toRegister != 'x') {
            curr.y = registers.get(curr.toRegister);
        }
        long result = curr.cmd.apply(registers.get(curr.register), curr.y);
        switch (curr.cmd) {
            case snd:
                if (part1) {
                    lastSound = result;
                } else {
                    send(result);
                }
                position++;
                break;
            case rcv:
                if (part1) {
                    if (result != 0) {
                        return lastSound;
                    }
                } else {
                    if (queue.isEmpty()) {
                        locked = true;
                        return sends;
                    }
                    registers.put(curr.register, queue.remove());
                }
                position++;
                break;
            case jgz:
                position += result;
                break;
            default:
                registers.put(curr.register, result);
                position++;
        }
        return 0L;
    }
}
