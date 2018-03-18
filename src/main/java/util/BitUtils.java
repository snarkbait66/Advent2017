package util;

import java.util.Arrays;

/**
 * @author /u/Philboyd_Studge on 1/15/2017.
 */
public class BitUtils {

    public static final boolean ONE = true;
    public static final boolean ZERO = false;
    private static final int[] POSITION_MASKS = new int[32];

    static {
        for (int i = 0; i < 32; i++) {
            POSITION_MASKS[i] = 1 << i;
        }
    }

    private BitUtils() {}

    /**
     * Get the high (Most Significant) 4 bits of the byte.
     * @param b byte
     * @return int 4-bit value
     */
    public static int getHighNibble(byte b) {
        return (b >> 4) & 0xf;
    }

    /**
     * get low (Least significant) 4 bits of the byte
     * @param b byte
     * @return int 4-bit value
     */
    public static int getLowNibble(byte b) {
        return b & 0xf;
    }

    /**
     * get proper hexadecimal character for value 0 - 15
     * @param n 4-bit value n
     * @return character 0 - 9 or lowercase a - f
     */
    private static char toHexDigit(int n) {
        if (n < 0 || n > 15) {
            throw new IllegalArgumentException("Digit out of range for hex" + n);
        }
        char c;
        if (n < 10) {
            c = (char)(n | 0x30);
        } else {
            c = (char)((n - 9) | 0x60);
        }
        return c;
    }

    /**
     * convert byte array to hex string
     * with in[0] being most significant byte
     * @param in byte array
     * @return hex string
     */
    public static String bytesToHex(byte[] in) {
        StringBuilder sb = new StringBuilder();
        for (byte each : in) {
            sb.append(toHexString(each & 0xff, 8));
        }
        return sb.toString();
    }

    /**
     * Takes a hexadecimal string and converts it to an array of bytes
     * with byte[0] being the Most Significant Byte
     * This can be useful for example feeding SHA digest data back into the digest
     * using the actual data not the ascii values of the hex string
     * @param input
     * @return
     */
    public static byte[] hexStringToBytes(String input) {
        input = input.replaceAll("[^a-fA-F0-9]", "");
        byte[] b = new byte[input.length()/2];
        for (int i = 0, j = 0; i < input.length() - 1; i+= 2, j++) {
            int c = 0;
            if (Character.isDigit(input.charAt(i))) {
                c = (input.charAt(i) ^ 0x30) << 4;
            } else {
                c = ((input.charAt(i) ^ 0x60) + 9) << 4;
            }
            if (Character.isDigit(input.charAt(i + 1))) {
                c |= input.charAt(i + 1) ^ 0x30;
            } else {
                c |= (input.charAt(i + 1) ^ 0x60) + 9;
            }
            b[j] = (byte) c;
        }
        return b;
    }

    /**
     * mask an integer to byte value
     * @param n integer
     * @return byte
     */
    public static byte maskToByte(int n) {
        return (byte) (n & 0xff);
    }

    /**
     * mask an integer to given bit depth
     * depth must be in range 0 - 31
     * @param n
     * @param maskToBitDepth
     * @return
     */
    public static int mask(int n, int maskToBitDepth) {
        checkRange(maskToBitDepth);
        return n & (POSITION_MASKS[maskToBitDepth] - 1);
    }

    /**
     * returns the number of set bits in the integer
     * @param n integer
     * @return integer number of set bits
     */
    public static int countBits(int n) {
        int count = 0;
        while (n != 0) {
            n &= n - 1;
            count++;
        }
        return count;
    }

    /**
     * get the integer value of the highest one bit
     * This will be the nearest power of two below the
     * current value of n
     * @param n integer
     * @return integer value of the highest one bit
     */
    public static int highestOneBit(int n) {
        int last = n;
        while (n != 0) {
            last = n;
            n &= n - 1;
        }
        return last;
    }

    /**
     * Return the index value of the highest one bit
     * with index based at zero from the leftmost bit
     * @param n integer
     * @return index of highest one bit
     */
    public static int highestOneBitIndex(int n) {
        int last = 0;
        for (int i = 0; i < 32; i++) {
            if (getBit(n, i)) last = i;
        }
        return last;
    }

    /**
     * Rotate bits to the left for given number of bits
     * and masking to given bit depth
     * masks any bit information beyond <code>depth</code>
     * @param n integer to rotate
     * @param bits number of bits to rotate
     * @param depth bit depth to mask to
     * @return rotated and masked integer
     */
    public static int rotLeft(int n, int bits, int depth) {
        int high = (n >>> depth - bits) & (POSITION_MASKS[bits] - 1);
        if (depth < 32) {
            n = (n << bits) & (POSITION_MASKS[depth] - 1);
        } else {
            n = (n << bits);
        }
        n |= high;
        return n;
    }

    /**
     * Rotate bits to the left for given number of bits
     * based on 32-bit depth
     * @param n integer to rotate
     * @param bits number of bits to rotate
     * @return integer
     */
    public static int rotLeft(int n, int bits) {
        return rotLeft(n, bits, 32);
    }

    /**
     * Push either a one or zero bit into
     * the rightmost position of the integer,
     * moving existing bits over one to the right,
     * dropping least significant bit
     * static constants BitUtils.ONE and BitUtils.ZERO
     * can be used instead of true or false.
     * @param n integer
     * @param bit true or false bit to push
     * @return integer
     */
    public static int pushRight(int n, boolean bit) {
        n >>= 1;
        if (bit) n = setBit(n, 31);
        return n;
    }

    /**
     * Push either a one or zero bit into
     * the leftmost position of integer n
     * moving existing bits over to the left
     * bits that move beyond the 32nd position are dropped
     * static constants BitUtils.ONE and BitUtils.ZERO
     * can be used instead of true or false
     * @param n integer
     * @param bit true or false bit to push
     * @return integer
     */
    public static int pushLeft(int n, boolean bit) {
        return (n << 1) | (bit ? 1 : 0);
    }

    private static void checkRange(int index) {
        if (index < 0 || index > 31) {
            throw new IllegalArgumentException("Bit Index out of range for Integer");
        }
    }

    /**
     * Set bit at given index
     * zero-based, from left to right
     * @param n integer
     * @param index index of bit to set 0 - 31
     * @return integer
     */
    public static int setBit(int n, int index) {
        checkRange(index);
        return n | POSITION_MASKS[index];
    }

    /**
     * Get status of bit at given index
     * zero-based, from left to right
     * @param n integer
     * @param index index of bit to set 0 - 31
     * @return true if bit is 1 or false if 0
     */
    public static boolean getBit(int n, int index) {
        checkRange(index);
        return (n & POSITION_MASKS[index]) == POSITION_MASKS[index];
    }

    /**
     * Clear bit at given index (set to zero)
     * zero-based, from left to right
     * @param n integer
     * @param index index of bit to clear 0 - 31
     * @return integer
     */
    public static int clearBit(int n, int index) {
        checkRange(index);
        return n & ~POSITION_MASKS[index];
    }

    /**
     * toggle bit at given index
     * zero-based, from left to right
     * @param n integer
     * @param index index of bit to toggle 0 - 31
     * @return integer
     */
    public static int toggleBit(int n, int index) {
        checkRange(index);
        return n ^ POSITION_MASKS[index];
    }

    /**
     * Convert integer n to a binary string
     * padding with extra zeroes on the right
     * as specified
     * @param n integer to convert
     * @param pad bit length to pad with zeroes if necessary
     * @return string
     */
    public static String toBinaryString(int n, int pad) {
        StringBuilder result = new StringBuilder();
        while (n != 0) {
            result.insert(0, n & 1);
            n >>>= 1;
        }

        pad = pad - result.length();
        while (pad-- > 0) {
            result.insert(0, "0");
        }
        return result.toString();
    }

    /**
     * Convert integer n to binary string
     * @param n integer to convert
     * @return string
     */
    public static String toBinaryString(int n) {
        return toBinaryString(n, 0);
    }

    /**
     * XOR two byte arrays with each other
     * byte arrays must be of equal length
     * @param a first byte array
     * @param b second byte array
     * @return XORed array
     */
    public static byte[] xorBytes(byte[] a, byte[] b) {
        if (a.length < b.length) throw new IllegalArgumentException("invalid input");
        for (int i = 0; i < a.length; i++) {
            a[i] = (byte) (a[i] ^ b[i]);
        }
        return a;
    }


    /**
     * Returns a padded hex string of integer n
     * @param n integer
     * @param pad bit depth to pad to, i.e. 8 for 2 hex digits
     *            will be padded with leading zeroes
     * @return hex string
     */
    public static String toHexString(int n, int pad) {
        StringBuilder result = new StringBuilder();
        while (n != 0) {
            result.insert(0, Character.forDigit(n & 0xf, 16));
            n >>>= 4;
        }
        pad -= result.length() * 4;
        while (pad > 0) {
            result.insert(0, "0");
            pad -= 4;
        }
        return result.toString();
    }

    /**
     * Returns hexadecimal string representation of integer n
     * without any leading zeroes
     * @param n integer
     * @return hex string
     */
    public static String toHexString(int n) {
        return toHexString(n, 0);
    }


    public static void main(String[] args) {
        int num = Integer.MAX_VALUE;
        System.out.println(toBinaryString(num));
        //num = rotLeft(num, 3);
        num = pushLeft(num, ONE);
        num = pushLeft(num, ZERO);
        System.out.println(toBinaryString(num));
        System.out.println(toHexString(num));
        byte[] b = hexStringToBytes(toHexString(num));
        System.out.println(Arrays.toString(b));
        System.out.println(countBits(num));
        System.out.println(highestOneBit(num));
        System.out.println(highestOneBitIndex(256));
    }

}
