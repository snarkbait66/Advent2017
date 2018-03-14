package util;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author /u/Philboyd_Studge on 1/11/2017.
 */
public class Snippets {



    enum Calc {
        ADD((x, y) -> x + y),
        SUBTRACT((x, y) -> x - y),
        MULTIPLY((x, y) -> x * y),
        DIVIDE((x, y) -> x / y);
        BiFunction<Integer, Integer, Integer> function;

        Calc(BiFunction<Integer, Integer, Integer> function) {
            this.function = function;
        }

    }

    enum Grades {
        A(250, 5000),
        B(200, 4000),
        C(175, 3000),
        D(150, 2000),
        E(Integer.MIN_VALUE, 0);
        private int lowBound, scholarship;
        Grades(int lowBound, int scholarship) {
            this.lowBound = lowBound;
            this.scholarship = scholarship;
        }
        static Grades getGradeFromMarks(int totalMarks) {
            for (Grades grade : Grades.values()) {
                if (totalMarks >= grade.getLowBound()) return grade;
            }
            // get rid of null pointer warning
            return Grades.E;
        }
        int getLowBound() { return lowBound; }
        int getScholarship() { return scholarship; }
    }


    static String longest(String s1, String s2) {
        Set<Character> set = new TreeSet<>();

        s1.chars().forEach(x -> set.add((char) x));
        s2.chars().forEach(x -> set.add((char) x));

        return set.stream()
                .map(x -> Character.toString(x))
                .collect(Collectors.joining());
    }

    public static double futureInvestmentValue(double investmentAmount, double monthlyInterestRate, int years){

        double Fv = investmentAmount * Math.pow((1 + (monthlyInterestRate)), years * 12);
        return Fv;
    }

    static enum CompoundType {
        YEARLY(1), BIANNUALLY(2), QUARTERLY(4), MONTHLY(12), WEEKLY(52), DAILY(356);
        int compounding;
        CompoundType(int compounding) { this.compounding = compounding; }
        public int getCompounding() { return compounding; }
    }

    static double futureValue(double investment, double monthlyRate, int periods, CompoundType cmp) {
        return investment * Math.pow(1 + (monthlyRate / 100)/cmp.getCompounding(), periods * cmp.getCompounding());
    }

    static <T> T[] arrayDelete(T[] array, int index) {
        if (array == null || array.length == 0 || index >= array.length || index < 0) {
            throw new IllegalArgumentException();
        }
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        return Arrays.copyOf(array, array.length - 1);
    }

    static <T> T[] arrayInsert(T[] array, T data, int index) {
        if (array == null || array.length == 0 || index > array.length || index < 0) {
            throw new IllegalArgumentException();
        }
        array = Arrays.copyOf(array, array.length + 1);
        System.arraycopy(array, index, array, index + 1, array.length - index - 1);
        array[index] = data;
        return array;
    }

    static List<Tuple3<Integer, Integer, Integer>> pythagoreanTriple(int[] array) {
        int[] squares = new int[array.length];
        int bigO = 0;
        int maxSquare = 0;
        List<Tuple3<Integer, Integer, Integer>> results = new ArrayList<>();

        // fill array with squares and find the max
        for (int i = 0; i < array.length; i++) {
            squares[i] = array[i] * array[i];
            if (squares[i] > maxSquare) maxSquare = squares[i];
            bigO++;
        }

        // create and fill lookup table
        int[] lookupSquares = new int[maxSquare + 1];
        for (int i = 0; i < squares.length; i++) {

            // set to original value
            lookupSquares[squares[i]] = array[i];
            bigO++;
        }

        // go through the possible pairs
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i; j < array.length; j++) {
                bigO++;
                int m = squares[i] + squares[j];
                if (m < lookupSquares.length && lookupSquares[m] > 0) {
                    results.add(new Tuple3<>(array[i], array[j], lookupSquares[m]));
                    System.out.println(array[i] + ":" + array[j] + ":" + lookupSquares[m]);
                    System.out.println(bigO);
                    //return true;
                }

            }
        }
        return results;

    }

    static class Expense {
        String name;
        int amount;

        Expense(String name, int amount) {
            this.name = name;
            this.amount = amount;
        }

        public int getAmount() { return amount; }
    }

    public static void main(String[] args) {
/*        System.out.println(longest("loopingisfunbutdangerous", "lessdangerousthancoding"));

        List<Expense> list = new ArrayList<>();

        list.add(new Expense("poop", 234));
        list.add(new Expense("larf", 321));

        System.out.println(list.stream().mapToInt(Expense::getAmount).sum());

        System.out.println(Grades.getGradeFromMarks(1000).getScholarship());

        String[] input = "multiply,25,10".split(",");
        Calc calcfunc = Calc.valueOf(input[0].toUpperCase());
        int a = Integer.parseInt(input[1]);
        int b = Integer.parseInt(input[2]);
        System.out.println(calcfunc.function.apply(a, b));

        int[] t = { 1, 2, 3, 4, 5};
        Integer[] o = ArrayUtils.objectifyInt(t);
        o = ArrayUtils.push(o, 10);
        System.out.println(Arrays.toString(o));

        o = ArrayUtils.delete(o, 5);
        o = ArrayUtils.append(o, 100);
        o = ArrayUtils.reverse(o);
        o = ArrayUtils.resize(o, 10);
        System.out.println(Arrays.toString(o));
        System.out.println(ArrayUtils.contains(o, 100));

        int x = 10;
        IntFunction mult2 = r -> r * 2;
        int y = (int) mult2.apply(x);*/

        //System.out.println(pythagoreanTriple(new int[] { 2, 3, 4, 5 , 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}));

        System.out.println(String.format("%.2f", futureValue(1200, 3.0, 30, CompoundType.YEARLY)));

    }
}
