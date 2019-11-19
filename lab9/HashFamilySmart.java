import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * Colgate University COSC 290 Labs
 * Version 0.2,  2019
 *
 * @author Michael Hay
 */
public class HashFamilySmart<E> implements HashFamily<E> {

    private final int m;
    private final int k;

    /**
     * Creates a family of k hash functions that has to the range 0..m-1
     * @param m range of hash (0 to m-1)
     * @param k number of hash functions in the family
     */
    public HashFamilySmart(int m, int k) {
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
        int hash = item.hashCode();
        // divide hash into two numbers by keeping every other bit
        int h1 = hash & 0x5555555;  // 0101 = 5 in hexadecimal
        int h2 = hash & 0xaaaaaaa;  // 1010 = 10 = "a" in hexadecimal
        // use two hashes to make family of k hashes:
        // see Kirsch & Mitzenmacher, "Less Hashing, Same Performance: Building a Better Bloom Filter"
        int result = (h1 + (i * h2)) % m;
        return result;
    }
}
