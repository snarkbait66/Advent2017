package util;

import java.util.Arrays;

/**
 * @author /u/Philboyd_Studge on 2/12/2017.
 */
public class ArrayUtils {

    // prevent instantiation of class
    private ArrayUtils() {}

    /**
     * Inserts an element T data at index position index
     * into given T array, resizing array and moving elements
     * of index > given index up one
     * @param array array to insert into
     * @param data element to add, must be same type as array
     * @param index integer index to insert into, elements higher will be moved one
     * @param <T> generic type T
     * @return resized array
     */
    public static <T> T[] insert(T[] array, T data, int index) {
        if (array == null || array.length == 0 || index > array.length || index < 0) {
            throw new IllegalArgumentException();
        }
        array = Arrays.copyOf(array, array.length + 1);
        System.arraycopy(array, index, array, index + 1, array.length - index - 1);
        array[index] = data;
        return array;
    }

    /**
     * delete array element at given index
     * moving remaining elements down and resizing array
     * @param array array to delete from
     * @param index index to delete
     * @param <T> generic type T
     * @return altered array
     */
    public static <T> T[] delete(T[] array, int index) {
        if (array == null || array.length == 0 || index >= array.length || index < 0) {
            throw new IllegalArgumentException();
        }
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        return Arrays.copyOf(array, array.length - 1);
    }

    /**
     * Insert element into beginning of array, resizing
     * array by one and moving remaining elements up one
     * @param array array to insert into
     * @param data element to insert
     * @param <T> generic type T
     * @return altered array
     */
    public static <T> T[] push(T[] array, T data) {
        return insert(array, data, 0);
    }

    /**
     * Append element at the end of array
     * resizing array
     * @param array array to append to
     * @param data element to append
     * @param <T> generic type T
     * @return altered array
     */
    public static <T> T[] append(T[] array, T data) {
        return insert(array, data, array.length);
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

    /**
     * convert double array to Double object array
     * @param array double array to convert
     * @return array of Double objects
     */
    public static Double[] objectifyDouble(double[] array) {
        Double[] result = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * convert float array to Float object array
     * @param array float array to convert
     * @return array of Float objects
     */
    public static Float[] objectifyFloat(float[] array) {
        Float[] result = new Float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * convert long array to Long object array
     * @param array long array to convert
     * @return array of Long objects
     */
    public static Long[] objectifyLong(long[] array) {
        Long[] result = new Long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * Reverses the order of array
     * @param array array to reverse
     * @param <T> generic type T
     * @return reversed array
     */
    public static <T> T[] reverse(T[] array) {
        for (int i = 0; i < array.length/2; i++) {
            T temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }

    /**
     * Reverses the order of the primitive integer array
     * @param array int array
     * @return int array reversed
     */
    public static int[] reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }

    /**
     * resize array
     * if newSize > current.length new elements will be filled
     * with default value for type
     * if newSize < current.length any elements after will be lost
     * yes, this is just a wrapper for Arrays.copyOf();
     * @param array array to resize
     * @param newSize new size
     * @param <T> generic type T
     * @return re-sized array
     */
    public static <T> T[] resize(T[] array, int newSize) {
        if (newSize <= 0) {
            throw new IllegalArgumentException();
        }
        return Arrays.copyOf(array, newSize);
    }

    /**
     * Search for element in array of same type
     * @param array array to be searched
     * @param find element to find
     * @param <T> generic type T
     * @return index of found element, or -1 if not found
     */
    public static <T> int contains(T[] array, T find) {
        for (int i = 0; i < array.length; i++) {
            if (find.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * get element in array with the maximum value, according to
     * compareTo method
     * @param array array of type that implements Comparable
     * @param <T> generic type T
     * @return element with max value
     */
    public static <T extends Comparable<T>> T max(T[] array) {
        // since we don't know what type it is or what the absolute max
        // value would be, simply start with the first element
        T max = array[0];


        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * get element in array with the minimum value, according to
     * compareTo method
     * @param array array of type that implements Comparable
     * @param <T> generic type T
     * @return element with min value
     */
    public static <T extends Comparable<T>> T min(T[] array) {
        // set min to first element
        T min = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(min) < 0) {
                min = array[i];
            }
        }
        return min;
    }

}
