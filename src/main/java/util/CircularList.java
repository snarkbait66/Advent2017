package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * A fixed-size circular array-backed list. This list
 * allows access by indices that go beyond the bounds
 * of the list, wrapping back around to the beginning,
 * and even allows for negative indexing.
 * i.e. <code>get(-1)</code> will return the last value in the list
 * and so on.
 * @param <T>
 */
public class CircularList<T> implements Iterable<T> {

    protected T[] array;

    /**
     * Constructor that creates a new, empty circular list of given size
     * Not recommended, as class has no method to insure all
     * elements have values
     * @param size integer fixed size to make array
     */
    @SuppressWarnings("unchecked")
    public CircularList(int size) {
        array = (T[]) new Object[size];
    }

    /**
     * Constructor that creates circular list out of given array
     * @param input array
     */
    @SuppressWarnings("unchecked")
    public CircularList(T[] input) {
        array = (T[]) new Object[input.length];
        System.arraycopy(input, 0, array, 0, input.length);
    }

    /**
     * Constructor that creates circular list out of given List
     * @param input list
     */
    @SuppressWarnings("unchecked")
    public CircularList(List<T> input) {
        array = (T[]) new Object[input.size()];
        for (int i = 0; i < input.size(); i++) {
            array[i] = input.get(i);
        }
    }

    /**
     * array size - note some elements may be null if the
     * first constructor is used
     * @return length of array
     */
    public int length() {
        return array.length;
    }

    /**
     * get the element at given position in array
     * wrapping with modulus as necessary in
     * both positive and negative directions
     * @param index index of element
     * @return element
     */
    public T get(int index) {
        index = negativeCheck(index);
        return array[index % array.length];
    }

    private int negativeCheck(int index) {
        if (index < 0) return array.length - (Math.abs(index) % array.length);
        return index;
    }

    /**
     * sets the given index (adjusted to fit within bounds)
     * with supplied element t
     * overwriting current value for that position
     * @param t element to put
     * @param index index to set
     * @return current value at index, or null
     */
    public T put(T t, int index) {
        T temp = get(index);
        array[negativeCheck(index) % array.length] = t;
        return temp;
    }

    /**
     * Gets a sublist of the circular list as a List
     * from position start (inclusive), of size length
     * wrapping around as necessary
     * @param start index position to start
     * @param length length to copy, must be <= overall array size
     * @return List of type T
     */
    public List<T> subList(int start, int length) {
        if (length > array.length)
            throw new IllegalArgumentException("Length must be less than or equal to array size.");
        List<T> temp = new ArrayList<>();
        for (int i = start; i < start + length; i++) {
            temp.add(get(i));
        }
        return temp;
    }

    /**
     * Insert a sublist into array, starting at position start
     * and wrapping as necessary. Overwrites existing value.
     * @param sub List of type T to insert
     * @param start position to start insert
     */
    public void insert(List<T> sub, int start) {
        if (sub.size() > length())
            throw new IllegalArgumentException("Inserted list must be less than or equal to array size");
        for (int i = start; i < start + sub.size(); i++) {
            put(sub.get(i - start), i);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < array.length;
            }

            @Override
            public T next() {
                return get(pos++);
            }
        };
    }

    /**
     * Find index of matching element, or -1 if not found
     * generic type t must having valid equals method
     * @param t element to find
     * @return index found or -1
     */
    public int indexOf(T t) {
        for (int i = 0; i < array.length; i++) {
            if (t.equals(get(i))) return i;
        }
        return -1;
    }

    public T[] toArray() {
        return array;
    }

    public List<T> toList() {
        return new ArrayList<T>(Arrays.asList(array));
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CircularList<?> that = (CircularList<?>) o;

        if (that.length() != this.length()) return false;

        for (int i = 0; i < array.length; i++) {
            if (!array[i].equals(that.get(i))) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    public static void main(String[] args) {
        CircularList<Integer> c = new CircularList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        System.out.println(c.put(200, -10));
        System.out.println(c.get(0));
    }
}
