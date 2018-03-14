package util;

/**
 * @author /u/Philboyd_Studge on 2/15/2017.
 */
public class QuickStack<T> {
    Node head;
    int size;

    class Node {
        T data;
        Node next;
        Node(T data, Node next) {
            this.next = next;
            this.data = data;
        }
    }

    int getSize() { return size; }

    void push(T data) {
        head = new Node(data, head);
        size++;
    }

    T pop() {
        if (size == 0) return null;
        T t = head.data;
        head = head.next;
        size--;
        return t;
    }

    T peek() {
        return head.data;
    }



    public static void main(String[] args) {
        QuickStack<Integer> stack = new QuickStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(300);

    }
}
