package Advent2017;

import util.AdventOfCode;
import util.Node;

import java.util.List;
import java.util.stream.Collectors;

public class Day24 extends AdventOfCode{

    class Port extends Node {
        Port(int x, int y) {
            super(x, y);
        }

        boolean used = false;
        int sum() { return this.getX() + this.getY(); }
    }

    private List<Port> ports;
    private int strongest;
    private int longest;
    private int strongestLongest;

    public Day24(List<String> input) {
        super(input);
        part1Description = "Strongest bridge of ports: ";
        part2Description = "Strongest, longest bridge of ports: ";
    }

    private void search(int port, int length, int strength) {
        strongest = Math.max(strength, strongest);
        if (length > longest) {
            longest = length;
            strongestLongest = strength;
        } else if (length == longest) {
            strongestLongest = Math.max(strength, strongestLongest);
        }

        for (Port each : ports) {
            if (!each.used && (each.getX() == port || each.getY() == port)) {
                each.used = true;
                search(each.getX() == port ? each.getY() : each.getX(),
                        length + 1, strength + each.sum());
                each.used = false;
            }
        }
    }

    @Override
    public Object part1() {
        search(0, 0, 0);
        return strongest;
    }

    @Override
    public Object part2() {
        return strongestLongest;
    }

    @Override
    public void parse() {
        ports = input.stream()
                .map(x -> x.split("/"))
                .map(x -> new Port(Integer.parseInt(x[0]), Integer.parseInt(x[1])))
                .collect(Collectors.toList());
    }
}
