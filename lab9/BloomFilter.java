import java.util.*;
/**
 * Colgate University COSC 290 Labs
 * Version 0.2,  2019
 *
 * @author Michael Hay
 */


public class BloomFilter<E> {

    private final int k;  // number of hash functions
    private final int m;  // size of hash array
    private HashFamily<E> hf;  // hash family from which hash functions are obtained
    private boolean[] bloomFilterArray;

    /**
     * Makes a new bloom filter with m slots and k hash functions from the specified hash family.
     * @param m number of slots in array
     * @param k number of hash functions to use
     * @param hf hash family from which to draw hash functions
     */
    public BloomFilter(int m, int k, HashFamily<E> hf) {
        this.k = k;
        this.m = m;
        // you may want to initialize some private fields here
        this.hf = hf;
        bloomFilterArray = new boolean[m];
    }

    public boolean lookUp(E item) {
        for (int i =0; i<k; i++) {
          // System.out.println(hf.hash(item,i));
          // System.out.println(bloomFilterArray.length);
          if (!(bloomFilterArray[hf.hash(item, i)])) {
            return false;
          }
        }
        return true;
    }

    public void insert(E item) {
        for (int i = 0; i < k; i++ ) {
          if ((bloomFilterArray[hf.hash(item, i)]) != true) {
            bloomFilterArray[hf.hash(item, i)] = true;
          }
        }
    }

    /**
     * Tries values for k from 1 to 100 and returns the k that has the lowest fp rate
     * according to the analytical formula (see DLN 10.99-10.103).
     * @param m number of slots in bloom filter
     * @param n number of objects inserted
     * @return the k that minimizes the false positive rate
     */
    public static int selectBestKParameter(int m, int n) {
        double doubleM = (double) m;
        double doubleN = (double) n;
        double minFp = 999999999999.0;
        int minFpKval = 0;
        for (int k = 1; k<=100; k++) {
          double probOneZero = Math.pow(Math.pow((doubleM-1)/doubleM,k),doubleN);
          double fp = Math.pow((1-probOneZero), k);
          // System.out.println(fp);
          if (fp < minFp) {
            minFp = fp;
            minFpKval = k;
          }
        }
        return minFpKval;
    }

    /**
     * Runs experiment described in lab description
     * @return the false positive rate
     */
    public static double runExperiment() {

        double rate;
        double numTrue  = 0.0;
        double numAdded = 0.0;

        HashFamily<String> hf = new HashFamilySmart<String>(1000000, 7);

        BloomFilter<String> bf = new BloomFilter<String>(1000000, 7, hf);

        for(int i =0;i<200000;i+=2){
          bf.insert(Integer.toString(i));
        }

        for(int i =1;i<200000;i+=2){
            if (bf.lookUp(Integer.toString(i)) == true) {
              numTrue ++;
            }
            numAdded ++;
        }

        rate = (numTrue/numAdded);
        return rate;

    }

    public static void main(String[] args) {
        // feel free to test your code here
        int m = 5;
        int k = 2;
        HashFamily<Integer> hf = new HashFamilyDumb<>(m, k);
        BloomFilter<Integer> bf = new BloomFilter<>(m, k, hf);
        System.out.println("Lookup 1: " + bf.lookUp(1));  // false because it checks slots 1 and 2, both unmarked
        bf.insert(0);  // slots 0 and 1 will be marked
        System.out.println("Lookup 0: " + bf.lookUp(0));
        bf.insert(2);  // slots 2 and 3 will be marked
        System.out.println("Lookup 2: " + bf.lookUp(2));
        System.out.println("Lookup 1: " + bf.lookUp(1));  // true because it checks slots 1 and 2, both marked

        System.out.println("False positive rate: " + runExperiment());
        System.out.println(selectBestKParameter(1000000, 100000));
    }

}
