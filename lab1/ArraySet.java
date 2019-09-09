import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A Set that holds a set of strings, backed by an array.
 *
 * Colgate University COSC 290 Labs
 * Version 0.1,  2019
 *
 * @author Michael Hay
 */
public class ArraySet {

    private String[] elements;             // where elements should be stored (order does not matter)
    private int cardinality;               // the number of elements in the set

    /**
     * Create a set via enumeration.  Each element in elementsToAdd should be added to the set.  Note that
     * elementsToAdd may contain duplicates but the created set should not have any duplicates.
     * @param elementsToAdd elements to add to the set
     */
    public ArraySet(String[] elementsToAdd) {
         // initialize the set by adding all of the elementsToAdd to it
    }

    /**
     * Create an empty set.
     */
    public ArraySet() {
        elements = new String[1];   // initialize with a capacity of 1
        cardinality = 0;
    }

    /**
     * Check for membership in set.
     * @param element element to be checked
     * @return true if element is in the set, false otherwise
     */
    public boolean in(String element) {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Adds one element to set, modifying the set.
     * @param element the element to add
     */
    public void add(String element) {
        throw new UnsupportedOperationException("implement me!");

    }

    /**
     * Removes one element from the set, modifying the set.
     * @param element the element to add
     * @throws java.util.NoSuchElementException if element is not in set
     */
    public void remove(String element) {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Size (aka cardinality) of the set.
     * @return the size of the set (number of distinct elements)
     */
    public int cardinality() {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Perform set union, producing a new set.
     * @param otherSet some other set
     * @return a new set equal to the union of this set with otherSet
     */
    public ArraySet union(ArraySet otherSet) {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Perform set intersection, producing a new set.
     * @param otherSet some other set
     * @return a new set equal to the intersection of this set with otherSet
     */
    public ArraySet intersection(ArraySet otherSet) {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Perform set difference, producing a new set.
     * @param otherSet some other set
     * @return a new set equal to the difference of this set with otherSet
     */
    public ArraySet difference(ArraySet otherSet) {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * @return the current storage consumed by this implementation
     */
    public int capacity() {
         // you don't need to edit this method
        return elements.length;
    }

    public static void main(String[] args) {
        ArraySet S = new ArraySet(new String[] {"a", "b", "c"});
        boolean hasA = S.in("a");
        System.out.println(hasA? "My array has 'a'": "My array does not have 'a'");
         // modify this method however you see fit: add code that will test the correctness of your implementation!
    }

}
