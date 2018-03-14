package util;

import java.util.Arrays;

public class DequeArray<T> {
    private T[] data;
    int head;
    int tail;
    int items;

    @SuppressWarnings("unchecked")
    public DequeArray(int size) {
        data = (T[]) new Object[size];
        tail = size;
    }

    public void addFirst(T item) {
        data[head] = item;
        head = (head + 1) % data.length;
        items++;
        if (head == tail) grow();
    }

    public void addLast(T item) {
        tail = ((tail - 1) + data.length) % data.length;
        data[tail] = item;
        items++;
        if (head == tail) grow();
    }

    public T removeFirst() {
        head = ((head - 1) + data.length) % data.length;
        if (data[head] == null) return null;
        T item = data[head];
        items--;
        data[head] = null;
        return item;
    }

    public T removeLast() {
        if (data[tail] == null) return null;
        T item = data[tail];
        items--;
        data[tail] = null;
        tail = (tail + 1) % data.length;
        return item;
    }

    public String toString() {
        return Arrays.toString(data);
    }

    private void grow() {
        int newLength = data.length * 2;
        int numToMove = data.length - tail;
        T[] temp = (T[]) new Object[newLength];
        System.arraycopy(data, 0, temp, 0, head + 1);
        System.arraycopy(data, tail, temp, newLength - numToMove, numToMove);
        data = temp;
        tail = newLength - numToMove;
    }

    public static void main(String[] args) {
        DequeArray<Integer> d = new DequeArray<>(6);
        d.addFirst(1);
        d.addFirst(2);
        System.out.println(d);
        d.addLast(4);
        d.addFirst(3);
        //System.out.println(d);
        d.addLast(5);
        //d.addFirst(7);
       // d.addLast(9);
        System.out.println(d);
        Integer i = -1;
        while (i != null) {
            i = d.removeFirst();
            System.out.println(i);
        }
        System.out.println(d);

    }
}
