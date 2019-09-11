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
      // if(elementsToAdd.length == 0 ) {
      //   add("element");
      // }
      this();
      // elements = new String[1];
      // cardinality = 0;

        for(int i=0; i< elementsToAdd.length; i++) {
          add(elementsToAdd[i]);
        }


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
      // if (capacity() == 0) {
      //   return false;
      // }

      for (int i=0; i<elements.length; i++) {
        if (element.equals(elements[i])) {
          return true;
        }
      }
      return false;
    }

    /**
     * Adds one element to set, modifying the set.
     * @param element the element to add
     */
    public void add(String element) {
      if (!in(element)) { //check if the element is already in the set
        if (cardinality == capacity()) {   //only double when you reach the capacity
          String[] copy = Arrays.copyOf(elements, elements.length*2);
          copy[elements.length] = element;
          elements = copy;
        } else {
          elements[cardinality] = element;
        }

        cardinality += 1;
      }
    }

    /**
     * Removes one element from the set, modifying the set.
     * @param element the element to remove
     * @throws java.util.NoSuchElementException if element is not in set
     */
    public void remove(String element) {
      String[] removed = new String[elements.length];

 // When the array is 1/4 full (cardinality = capacity / 4), then the capacity should be cut in half. You may use the built-in method
      //cut the size in half?
      for (int i=0; i<elements.length; i++) {
        if (element != elements[i]) {
          removed[i] = elements[i];
        } else {
          removed[i] = null;
        }
      }
      elements = removed;
      cardinality -=1;
    }

    /**
     * Size (aka cardinality) of the set.
     * @return the size of the set (number of distinct elements)
     */
    public int cardinality() {
      return cardinality;
    }

    /**
     * Perform set union, producing a new set.
     * @param otherSet some other set
     * @return a new set equal to the union of this set with otherSet
     */
    public ArraySet union(ArraySet otherSet) {
      ArraySet newSet = new ArraySet();
      for(int i=0; i<elements.length; i++) {
        newSet.add(elements[i]);
      }

      for(int i=0; i<otherSet.capacity(); i++) {
        newSet.add(otherSet.elements[i]);
      }
      return newSet;
    }

    /**
     * Perform set intersection, producing a new set.
     * @param otherSet some other set
     * @return a new set equal to the intersection of this set with otherSet
     */
    public ArraySet intersection(ArraySet otherSet) {
      ArraySet newSet = new ArraySet();
      for (int i=0; i<otherSet.capacity(); i++) {
        if(in(otherSet.elements[i])) {
          newSet.add(otherSet.elements[i]);
        }
      }
      return newSet;
    }

    /**
     * Perform set difference, producing a new set.
     * @param otherSet some other set
     * @return a new set equal to the difference of this set with otherSet
     */
    public ArraySet difference(ArraySet otherSet) {
      ArraySet newSet = new ArraySet(elements);
      for (int i=0; i<otherSet.capacity(); i++) {
        if(in(otherSet.elements[i])) {
          newSet.remove(otherSet.elements[i]);
        }
      }
      return newSet;
    }

    /**
     * @return the current storage consumed by this implementation
     */
    public int capacity() {
         // you don't need to edit this method
        return elements.length;
    }

    public static void main(String[] args) {
      ArraySet S = new ArraySet(new String[] {});
      S.add("dog");
      boolean hasDog = S.in("dog");
      System.out.println(hasDog? "My array has 'dog'":"My array does not have 'dog'");

      // ArraySet S = new ArraySet();
      // S.add("h");
      // S.add("h"); //should not add this
      // S.add("a");
      // boolean hasH = S.in("h");
      // System.out.println(hasH? "My array has 'h'":"My array does not have 'h'");
      // boolean hasA = S.in("a");
      // System.out.println(hasA? "My array has 'a'":"My array does not have 'a'");
      // S.remove("h");
      // hasH = S.in("h");
      // System.out.println(hasH? "My array has 'h'":"My array does not have 'h'");



        // ArraySet S = new ArraySet(new String[] {"a", "b", "c", "a", "e"});
        // ArraySet B = new ArraySet(new String[] {"d", "e"});
        // boolean hasA = S.in("a");
        // System.out.println(hasA? "My array has 'a'": "My array does not have 'a'");

        // ArraySet union = S.union(B);
        // boolean hasD = union.in("d");
        // System.out.println(hasD? "My array has 'd'": "My array does not have 'd'");
        // int card = union.cardinality();
        // System.out.println(card);
        //
        // ArraySet intersection = S.intersection(B);
        // boolean hasE = intersection.in("e");
        // System.out.println(hasE? "My array has 'e'": "My array does not have 'e'");
        //
        // ArraySet difference = S.difference(B);
        // hasE = difference.in("e");
        // System.out.println(hasE? "My array has 'e'": "My array does not have 'e'");




         // modify this method however you see fit: add code that will test the correctness of your implementation!
    }

}
