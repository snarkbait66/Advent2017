package util;

import java.math.BigInteger;
import java.util.*;

/**
 * @author /u/Philboyd_Studge on 3/11/2017.
 */
public class HammingNumbers {
    static BigInteger TWO = BigInteger.valueOf(2);
    static BigInteger THREE = BigInteger.valueOf(3);
    static BigInteger FIVE = BigInteger.valueOf(5);

    static long hammingPQ(int n) {
        long h = 1;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < n - 1; i++) {
            //System.out.println(h);
            pq.add(h * 2);
            pq.add(h * 3);
            pq.add(h * 5);
            h = pq.poll();
            while (pq.peek() == h) {
                pq.poll();
            }
        }
        return h;
    }

    static BigInteger hammingTree(int n) {
        BigInteger h = BigInteger.ONE;
        TreeSet<BigInteger> queue = new TreeSet<>();
        for (int i = 0; i < n - 1; i++) {
            queue.add(h.multiply(TWO));
            queue.add(h.multiply(THREE));
            queue.add(h.multiply(FIVE));
            h = queue.pollFirst();
        }
        return h;
    }

    static BigInteger hamming2(int n) {
        BigInteger h = BigInteger.ONE;

        PriorityQueue<BigInteger> queue = new PriorityQueue<>();
        for (int i = 0; i < n - 1; i++) {
            queue.add(h.multiply(TWO));
            queue.add(h.multiply(THREE));
            queue.add(h.multiply(FIVE));
            h = queue.poll();
            while (queue.peek().equals(h)) {
                queue.poll();
            }
        }
        return h;
    }
    static BigInteger hamming(int n) {
        BigInteger h = BigInteger.ONE;
        Queue<BigInteger> q2 = new LinkedList<>();
        Queue<BigInteger> q3 = new LinkedList<>();
        Queue<BigInteger> q5 = new LinkedList<>();
        for (int i = 0; i < n - 1; i++) {
            q2.add(h.multiply(TWO));
            q3.add(h.multiply(THREE));
            q5.add(h.multiply(FIVE));
            h = q2.peek().min(q3.peek().min(q5.peek()));
            if (q2.peek().equals(h)) {
                q2.poll();
            }
            if (q3.peek().equals(h)) {
                q3.poll();
            }
            if (q5.peek().equals(h)) {
                q5.poll();
            }
        }
        return h;
    }

    public static void main(String[] args) {
        Timer.startTimer();
        System.out.println(hamming(1000000));
        System.out.println(hammingTree(1000000));
        System.out.println(Timer.endTimer());
    }
}
