package Advent2017;

import util.AdventOfCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Day25 extends AdventOfCode {

    private Map<Integer, Boolean> tape;
    private int position;
    private State current;

    enum State {
        A(State::tlfr, State::reverse),
        B(State::trlf, State::reverse),
        C(State::tlfr, State::reverse),
        D(pos -> -1, bit -> true),
        E(pos -> 1, bit -> false),
        F(pos -> 1, bit -> true);

        Function<Boolean, Integer> nextPos;
        Function<Boolean, Boolean> nextBit;
        State zero;
        State one;

        static {
            A.zero = B;
            A.one = B;
            B.zero = C;
            B.one = E;
            C.zero = E;
            C.one = D;
            D.zero = A;
            D.one = A;
            E.zero = A;
            E.one = F;
            F.zero = E;
            F.one = A;
        }

        State(Function<Boolean, Integer> nextPos, Function<Boolean, Boolean> nextBit) {
            this.nextPos = nextPos;
            this.nextBit = nextBit;
        }

        State next(boolean bit) { return bit ? one : zero; }
        static boolean reverse(boolean bit) {
            return !bit;
        }

        static int trlf(boolean bit) {
            return bit ? 1 : -1;
        }

        static int tlfr(boolean bit) { return bit ? -1 : 1; }

    }

    public Day25(List<String> input) {
        super(input);
        part1Description = "Checksum after 12861455 steps: ";
        part2Description = "No part 2 today! Merry Christmas, Everyone!!";
    }

    @Override
    public Object part1() {
        for (int i = 0; i < 12861455; i++) {
            machineStep();
        }
        return getOnes();
    }

    private void machineStep() {
        boolean bit = tape.getOrDefault(position, false);
        tape.put(position, current.nextBit.apply(bit));
        position += current.nextPos.apply(bit);
        current = current.next(bit);
    }

    private int getOnes() {
        return (int) tape.entrySet().stream()
                .filter(Map.Entry::getValue)
                .count();
    }

    @Override
    public Object part2() {
        return "";
    }

    @Override
    public void parse() {
        tape = new HashMap<>();
        tape.put(0, false);
        current = State.A;
    }

}