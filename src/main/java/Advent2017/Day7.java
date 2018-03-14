package Advent2017;

import util.AdventOfCode;
import util.FileIO;

import java.util.*;

public class Day7 extends AdventOfCode {

    private int unbalanced;
    private Map<String, Leaf> tree;

    public Day7(List<String> input) {
        super(input);
        title = "Recursive Circus";
        part1Description = "Name of program at the bottom: ";
        part2Description = "Weight to balance tower: ";
    }

    @Override
    public Object part1() {

        // find root node of tree
        Leaf root = tree.entrySet().stream()
                .filter(x -> x.getValue().parent == null)
                .map(Map.Entry::getValue)
                .findFirst().get();


        return sumOfChildren(root.children);
    }

    @Override
    public Object part2() {
        return unbalanced;
    }

    @Override
    public void parse() {
        tree = new HashMap<>();

        // build tree, first run just create each node
        for (String each : input) {
            String[] split = each.split(" ");
            int weight = Integer.parseInt(split[1].replaceAll("[()]", ""));
            Leaf node = new Leaf(split[0], weight);
            tree.put(split[0], node);
        }

        // then assign the parent/child relationships
        for (String each : input) {
            String[] split = each.split(" ");
            Leaf parent = tree.get(split[0]);
            if (split.length > 2) {
                for (int i = 3; i < split.length; i++) {
                    String name = split[i].replaceAll(",", "");
                    Leaf child = tree.get(name);
                    child.parent = parent;
                    parent.children.add(child);
                }
            }
        }
    }

    private int sumOfChildren(List<Leaf> children) {
        if (children.size() == 0) return 0;

        int sum = 0;
        int target = 0;
        for (Leaf each : children){
            if (each.children.size() == 0) {
                sum += each.weight;
                if (target == 0) target = each.weight;
            } else {

                int subtotal = sumOfChildren(each.children);
                each.weightOfChildren = subtotal;
                subtotal += each.weight;
                if (target == 0) target = subtotal;
                sum += subtotal;
            }
        }
        if (sum / children.size() != target) {
            if (unbalanced == 0) {
                Leaf off = children.stream()
                        .max(Comparator.comparing(Leaf::total))
                        .get();
                unbalanced = off.weight - (off.total() - target);
            }
        }
        return sum;
    }

    static class Leaf {
        String name;
        int weight;
        int weightOfChildren;
        Leaf parent;
        List<Leaf> children;
        Leaf(String name, int weight){
            this.name = name;
            this.weight = weight;
            children = new ArrayList<>();
        }

        int total() { return weight + weightOfChildren; }

    }
}
