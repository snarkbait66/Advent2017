package util;

public class CircularQueue<T> extends CircularList<T> {
    int read;
    int write;
    boolean overwrite;

    public CircularQueue(int size) {
        super(size + 1);
    }

    public CircularQueue(int size, boolean overwrite) {
        super(size + 1);
        this.overwrite = overwrite;
    }

    public int size() {
        return (array.length + write - read) % array.length;
    }

    public boolean isFull() {
        return read == (write + 1) % array.length;
    }

    public boolean isEmpty() {
        return read == write;
    }

    public void put(T t) {
        if (!overwrite && isFull()) {
            throw new IllegalArgumentException("Queue is full");
        }
        put(t, write++);
        write %= array.length;
    }

    public T get() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty");
        }
        T temp = get(read++);
        read %= array.length;
        return temp;
    }

    public static void main(String[] args) {
        CircularQueue<Integer> q = new CircularQueue<>(10, true);
        for (int i = 0; i < 20; i++) {
            q.put(i);
        }
        System.out.println(q.size());
        System.out.println(q.get());
        System.out.println(q.size());
        System.out.println(q.get());
        q.put(11);
        System.out.println(q.size());
    }
}
