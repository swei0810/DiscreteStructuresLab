/**
 * Colgate University COSC 290 Labs
 * Version 0.2,  2019
 *
 * @author Michael Hay
 */
public interface HashFamily<E> {

    /**
     * Hashes item using the ith hash function in the hash family
     * @param item the to be hashed
     * @param i which hash function to use
     * @return hash value
     */
    int hash(E item, int i);
}
