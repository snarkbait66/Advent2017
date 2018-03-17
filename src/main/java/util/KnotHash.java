package util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KnotHash {
    private int pos;
    private int skips;
    private int bufferSize = 256;
    private static final int[] SUFFIX = { 17, 31, 73, 47, 23 };

    private int[] lengths;
    private CircularList<Integer> buffer;

    public KnotHash(String input) {
        lengths = input.chars().toArray();
        reset();
    }

    public KnotHash(int[] array) {
        lengths = array;
        reset();
    }

    public KnotHash(int[] array, int bufferSize) {
        lengths = array;
        this.bufferSize = bufferSize;
        reset();
    }

    public String getHashed() {

        lengths = Arrays.copyOf(lengths, lengths.length + SUFFIX.length);
        System.arraycopy(SUFFIX, 0, lengths, lengths.length - SUFFIX.length, SUFFIX.length);

        for (int i = 0; i < 64; i++) {
            hash();
        }
        return getDense();
    }

    public int getNum(int i) {
        return buffer.get(i);
    }

    private void reset() {
        pos = 0;
        skips = 0;
        buffer = new CircularList<>(IntStream.range(0, bufferSize)
                                        .boxed()
                                        .toArray(Integer[]::new));
    }

    public void hash() {
        for (int len : lengths) {
            List<Integer> sub = buffer.subList(pos, len);
            Collections.reverse(sub);
            buffer.insert(sub, pos);

            pos += len + skips++;
            pos %= buffer.length();
        }
    }

    private String getDense() {
        int[] dense = new int[16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                dense[i] ^= buffer.get((i * 16) + j);
            }
        }
        return Arrays.stream(dense)
                .mapToObj(x -> BitUtils.toHexString(x, 8))
                .collect(Collectors.joining());

    }

}
