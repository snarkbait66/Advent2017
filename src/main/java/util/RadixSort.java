package util;

import java.util.Random;

public class RadixSort {

    public static void main(String arg[]){

        Random rand = new Random();


        //int a[] = {509,3,48,91,66,101,30,795};
        int a[] = new int[100];
        for (int i = 0; i < a.length; i++) {
            a[i] = rand.nextInt(1000);
        }

        Timer.startNanoTimer();

        //print unsorted array using Arrays.toString()
        //System.out.print("Unsorted array: ");
        //System.out.println(Arrays.toString(a));

        RadixSort rs= new RadixSort();
        rs.radixSortHex(a);
        //Arrays.sort(a);

        //System.out.print("Sorted array: ");
        //print sorted array

        System.out.println(Timer.endTimer());


        //System.out.println(Arrays.toString(a));
    }

    public void radixSort(int array[])
    {
        int digitPlace = 1;
        int n = array.length;
        int result[] = new int[n];

        int max = 0;


        // loop until we reach the largest digit place
        while ( digitPlace == 1 || max / digitPlace > 0) {
            int count[] = new int[10];


            // get frequency count of digits at current digit place
            for (int each : array) {

                // on first pass, find max
                if (digitPlace == 1) {
                    if (each > max) {
                        max = each;
                    }
                }

                count[(each / digitPlace) % 10]++;
            }

            // counting sort algorithm
            // make each element in the frequency array
            // store the sum of previous counts
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }


            // get index from count array and place original elements
            // into temp array, looping backwards
            for (int i = n - 1; i >= 0; i--) {

                // extract correct digit
                int current = (array[i] / digitPlace) % 10;

                result[count[current] - 1] = array[i];
                count[current]--;
            }

            // copy temp array back to original
            System.arraycopy(result, 0, array, 0, n);

            // Move to next digit place
            digitPlace *= 10;
        }
    }

    public void radixSortHex(int[] array) {

        int pos = 0;
        int n = array.length;
        int[] result = new int[n];
        int max = 1;

        while (max >> pos > 0) {
            int[] count = new int[16];

            for (int each : array) {
                if (pos == 0) {
                    if (each > max) max = each;
                }
                count[(each >> pos) & 0xf]++;
            }

            for (int i = 1; i < 16; i++) {
                count[i] += count[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                int current = (array[i] >> pos) & 0xf;
                result[count[current] - 1] = array[i];
                count[current]--;
            }

            System.arraycopy(result, 0, array, 0, n);
            pos += 4;
        }
    }



}