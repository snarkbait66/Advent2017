package util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author /u/Philboyd_Studge on 12/26/2016.
 */
public class Permutations {

    /**
     * Returns all permutations of given int array
     * as a List of Lists
     * Not recommended to use for arrays larger than 9 or 10
     * as resulting list will be of size <code>n!</code>
     * @param nums int array
     * @return List of Integer Lists of permutations
     */
    public static List<List<Integer>> permuteIntArray(int[] nums) {

        // convert int[] to Integer[]
        Integer[] array = objectifyInt(nums);
        return permute(array);
    }

    /**
     * Returns all permutations of given array of generic type T
     * as a List of Lists
     * Not recommended to use for arrays larger than 9 or 10
     * as resulting list will be size <code>n!</code>
     * @param array array of type T
     * @param <T> Generic type declaration
     * @return List of Lists of permutations
     */
    public static <T> List<List<T>> permute(T[] array) {
        List<List<T>> results = new ArrayList<>();
        if (array == null || array.length == 0) return results;
        List<T> result = new ArrayList<>();
        permute(array, results, result);
        return results;

    }

    private static <T> void permute(T[] array, List<List<T>> results, List<T> result) {
        if (array.length == result.size()) {
            List<T> temp = new ArrayList<>(result);
            results.add(temp);
        }
        for (int i=0; i<array.length; i++) {
            if (!result.contains(array[i])) {
                result.add(array[i]);
                permute(array, results, result);
                result.remove(result.size() - 1);
            }
        }
    }

    /**
     * convert int array to Integer object array
     * @param array int array to convert
     * @return array of Integer objects
     */
    public static Integer[] objectifyInt(int[] array) {
        Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[] array = { 1 , 2, 3 };
        List<List<Integer>> permutations = Permutations.permute(array);
        System.out.println(permutations);
    }
}
