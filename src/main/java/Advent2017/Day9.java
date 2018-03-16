package Advent2017;

import util.AdventOfCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day9 extends AdventOfCode {

    private int garbageCount;
    private String data;

    enum State {
        OPEN, CLOSE, GARBAGE, IGNORE, OTHER;

        final Map<Character, State> transitions = new HashMap<>();

        // Rules for Finite State Machine
        static {
            State.OTHER.addTransition('{', State.OPEN);
            State.OTHER.addTransition('}', State.CLOSE);
            State.OTHER.addTransition('<', State.GARBAGE);
            State.OPEN.addTransition('}', State.CLOSE);
            State.OPEN.addTransition('<', State.GARBAGE);
            State.OPEN.addTransition(',', State.OTHER);
            State.CLOSE.addTransition('{', State.OPEN);
            State.CLOSE.addTransition('<', State.GARBAGE);
            State.CLOSE.addTransition(',', State.OTHER);
            State.GARBAGE.addTransition('!', State.IGNORE);
            State.GARBAGE.addTransition('>', State.OTHER);
            State.IGNORE.addTransition('!', State.GARBAGE);
        }

        private void addTransition(char c, State accept) {
            transitions.put(c, accept);
        }

        public State getTransition(char c) {
            return transitions.getOrDefault(c, this);
        }
    }

    public Day9(List<String> input) {
        super(input);
        title = "Stream Processing";
        part1Description = "Score of the bracketed groups: ";
        part2Description = "Number on non-cancelled characters in garbage: ";
    }

    @Override
    public Object part1() {
        State current = State.OTHER;

        int level = 0;
        int score = 0;

        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (current == State.IGNORE) c = '!';
            State next = current.getTransition(c);
            switch (next) {
                case OPEN:
                    level++;
                    break;
                case CLOSE:
                    score += level--;
                    break;
                case GARBAGE:
                    if (current == State.GARBAGE) garbageCount++;
            }
            current = next;
        }
        return score;
    }

    @Override
    public Object part2() {
        return garbageCount;
    }

    @Override
    public void parse() {
        data = input.get(0);
    }
}
