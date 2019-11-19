/**
 * Colgate University COSC 290 Labs
 * Version 0.2,  2019
 *
 * @author Michael Hay
 */
public class HashFamilyDumb<E> implements HashFamily<E> {

    private final int m;
    private final int k;

    /**
     * Creates a family of k hash functions that has to the range 0..m-1
     * @param m range of hash (0 to m-1)
     * @param k number of hash functions in the family
     */
    public HashFamilyDumb(int m, int k) {
        this.m = m;
        this.k = k;
    }

    /**
     * Hashes item using the ith hash function in the hash family
     * @param item the to be hashed
     * @param i which hash function to use
     * @return hash value
     */
    public int hash(E item, int i) {
        // hashes item using its hash code and then simply adds i to the resulting value
        return ((item.hashCode() % m) + i) % m;
    }
}
