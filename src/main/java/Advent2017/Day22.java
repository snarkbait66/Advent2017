package Advent2017;

import util.AdventOfCode;
import util.Direction;
import util.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Day22 extends AdventOfCode {

    enum State {
        CLEAN(Direction::getLeft),
        WEAKENED(Function.identity()),
        INFECTED(Direction::getRight),
        FLAGGED(Direction::getOpposite);

        Function<Direction, Direction> newDir;

        State(Function<Direction, Direction> newDir) {
            this.newDir = newDir;
        }

        Direction nextDir(Direction dir) {
            return newDir.apply(dir);
        }

        static State getFromChar(char c) {
            return c == '#' ? INFECTED : CLEAN;
        }
    }

    private Node position;
    private Direction dir;
    private final State[] states = State.values();
    private Map<Node, State> map;
    private int infected;

    public Day22(List<String> input) {
        super(input);
        part1Description = "Number of bursts to cause infected node: ";
        part2Description = "Number of bursts to cause infected node, part 2: ";
    }

    void tick(int stateModifier) {
        State current = map.getOrDefault(position, State.CLEAN);
        dir = current.nextDir(dir);
        map.put(position, states[(current.ordinal() + stateModifier) % 4]);
        if (map.get(position) == State.INFECTED) infected++;
        position = dir.move(position);
    }

    @Override
    public Object part1() {
        for (int i = 0; i < 10000; i++) {
            tick(2);
        }
        return infected;
    }

    @Override
    public Object part2() {
        parse();
        infected = 0;
        for (int i = 0; i < 10000000; i++) {
            tick(1);
        }
        return infected;
    }

    @Override
    public void parse() {
        //input = Arrays.asList("..#", "#..", "...");
        position = new Node(input.size() / 2, input.size()/2);
        dir = Direction.NORTH;
        map = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                map.put(new Node(j, i), State.getFromChar(input.get(i).charAt(j)));
            }
        }
    }
}