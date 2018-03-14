package Advent2017;



import util.FileIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



public class Horrible {

    static class Component {
        final int sideA;
        final int sideB;

        Component(String[] input) {
            sideA= Integer.parseInt(input[0]);
            sideB= Integer.parseInt(input[1]);
        }

        @Override
        public String toString () {
            return sideA + "/" + sideB;
        }
    }

    static class Bridge {
        final List<Component> links= new LinkedList<>();
        private final List<Component> unusedComponents;
        int nextPort= 0;

        Bridge (List<Component> components) {
            unusedComponents= new ArrayList<>(components);
        }

        Bridge (Bridge bridge, Component nextComponent, int lastPort) {
            List<Component> existing = new ArrayList<>(bridge.unusedComponents);
            if (!existing.remove(nextComponent)) {
                throw new IllegalArgumentException("couldn't remove next component " + nextComponent);
            }
            this.unusedComponents= existing;
            this.links.addAll(bridge.links);
            this.links.add(nextComponent);
            this.nextPort= nextComponent.sideA == lastPort ? nextComponent.sideB : nextComponent.sideA;
        }

        int getStrength () {
            return links.stream().map(c -> c.sideA + c.sideB).mapToInt(i -> i).sum();
        }

        @Override
        public String toString () {
            return links.stream().map(Component::toString).collect(Collectors.joining(" - "));
        }
    }



    private int solveTask1 (List<String> input) {
        List<Component> components= parse(input);

        List<Bridge> endState= solveBFS(new Bridge(components), 0);

        Bridge maxStrengthBridge= getMaxStrengthBridge(endState);
        int maxStrength= maxStrengthBridge.getStrength();
        System.out.println("Winning bridge: " + maxStrengthBridge);
        System.out.println("\tstrength: " + maxStrength);
        return maxStrength;
    }

    private int solveTask2 (List<String> input) {
        List<Component> components= parse(input);

        int maxLen= 0;
        List<Bridge> maxLenBridges= new ArrayList<>();

        List<Bridge> endState= solveBFS(new Bridge(components), 0);
        for (Bridge b : endState) {
            int length= b.links.size();

            if (length > maxLen) {
                maxLen= length;
                maxLenBridges= new ArrayList<>();
                maxLenBridges.add(b);
            } else if (length == maxLen) {
                maxLenBridges.add(b);
            }
        }

        Bridge maxStrengthBridge= getMaxStrengthBridge(maxLenBridges);
        int maxStrength= maxStrengthBridge.getStrength();
        System.out.println("Winning bridge: " + maxStrengthBridge);
        System.out.println("\tstrength: " + maxStrength);
        return maxStrength;
    }

    private Bridge getMaxStrengthBridge (List<Bridge> endState) {
        Bridge maxStrengthBridge= null;
        int maxStrength= 0;
        for (Bridge b : endState) {
            int strength= b.getStrength();
            if (strength > maxStrength) {
                maxStrength= strength;
                maxStrengthBridge= b;
            }
        }
        return maxStrengthBridge;
    }

    private List<Bridge> solveBFS (Bridge currentBridge, int depth) {
        if (depth > 1000) {
            System.out.println("*** MAX depth reached! ***");
            return Collections.emptyList();
        }

        Set<Bridge> nextPossible= possibleConnections(currentBridge);
        Set<Bridge> queue= new HashSet<>(nextPossible);

        if (queue.isEmpty()) {
            return Collections.singletonList(currentBridge);
        }

        List<Bridge> solutions= new ArrayList<>();
        for (Bridge bridge : queue) {
            // recurse
            List<Bridge> subtree= solveBFS(bridge, depth + 1);
            solutions.addAll(subtree);
        }
        return solutions;
    }

    private Set<Bridge> possibleConnections (Bridge bridge) {
        Set<Bridge> next= new HashSet<>();
        List<Component> possibleComponents= byPort(bridge.unusedComponents, bridge.nextPort);
        for (Component c : possibleComponents) {
            next.add(new Bridge(bridge, c, bridge.nextPort));
        }
        return next;
    }

    private List<Component> byPort (List<Component> components, int portNo) {
        List<Component> byA= components
                .stream()
                .filter(c -> c.sideA == portNo)
                .collect(Collectors.toList());
        List<Component> byB= components
                .stream()
                .filter(c -> c.sideB == portNo)
                .collect(Collectors.toList());
        byA.addAll(byB);
        return byA;
    }

    private List<Component> parse (List<String> input) {
        return input.stream()
                .map(s -> s.split("/"))
                .map(Component::new)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Horrible day = new Horrible();
        System.out.println(day.solveTask1(FileIO.getFileAsList("advent2017_day24.txt")));
        System.out.println(day.solveTask2(FileIO.getFileAsList("advent2017_day24.txt")));
    }

}