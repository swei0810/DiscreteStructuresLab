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
    }

    public boolean lookUp(E item) {
        throw new UnsupportedOperationException("implement me!");
    }

    public void insert(E item) {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Tries values for k from 1 to 100 and returns the k that has the lowest fp rate
     * according to the analytical formula (see DLN 10.99-10.103).
     * @param m number of slots in bloom filter
     * @param n number of objects inserted
     * @return the k that minimizes the false positive rate
     */
    public static int selectBestKParameter(int m, int n) {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Runs experiment described in lab description
     * @return the false positive rate
     */
    public static double runExperiment() {
        throw new UnsupportedOperationException("implement me!");
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
    }

}
