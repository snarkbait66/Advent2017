package util;

/**
 * @author /u/Philboyd_Studge on 2/5/2017.
 */
public class Linked<T> {
    Node head;

    Linked() {}

    Linked(T[] input) {
        for (T each : input) {
            push(each);
        }
    }

    void push(T t) {
        head = new Node(t, head);
    }

    T pop() {
        T t = head.value;
        head = head.next;
        return t;
    }

    public void display() {
        Node current = head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.value);
            if (current.next != null) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println("]");
    }

    Linked<T> reverse() {
        Linked<T> temp = new Linked<>();
        while (head != null) {
            temp.push(this.pop());
        }
        return temp;
    }

    class Node {
        T value;
        Node next;
        Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Linked<Integer> list = new Linked<>(new Integer[] { 1, 2, 3, 4});
        list.display();
        list = list.reverse();
        list.display();
        for (int i = 0; i < 100; i++) {
            System.out.println(i + " : " + (-i&i));
        }
    }
}
