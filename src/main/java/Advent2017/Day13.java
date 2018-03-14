package Advent2017;

import util.AdventOfCode;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day13 extends AdventOfCode {

    private Map<Integer, Integer> layers;

    public Day13(List<String> input) {
        super(input);
        title = "Packet Scanners";
        part1Description = "Severity of trip through firewall: ";
        part2Description = "Fewest amount of time to delay trip: ";
    }

    @Override
    public void parse() {
        layers = input.stream()
                .map(x -> x.split(": "))
                .collect(Collectors.toMap(x -> Integer.parseInt(x[0]),
                        x -> Integer.parseInt(x[1])));

    }

    @Override
    public Integer part1() {
        return layers.entrySet().stream()
                .filter(x -> x.getKey() % ((x.getValue() - 1) * 2) == 0)
                .mapToInt(x -> x.getKey() * x.getValue())
                .sum();
    }

    @Override
    public Integer part2() {
        int delay = 2;

        while (true) {
            boolean caught = false;

            for (Map.Entry<Integer, Integer> layer : layers.entrySet()) {
                if ((layer.getKey() + delay) % ((layer.getValue() - 1) * 2) == 0) {
                    caught = true;
                    break;
                }
            }
            if (!caught) return delay;
            delay+= 2;

        }
    }
}
