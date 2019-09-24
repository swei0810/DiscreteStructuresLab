import java.util.*;

/**
 * Colgate University COSC 290 Labs
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class EnumeratingSubsets {

    /**
     * Given a set s, containing elements of type String, return the powerset of s
     * @param s a set of elements
     * @return the set of all subsets of input set s
     */
    public static Set<Set<String>> allSubsets(Set<String> s) {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Given a set s, containing elements of type String, return set of all subsets of s such that each
     * subset contains k elements
     * @param s a set of elements
     * @param k the desired number of elements in each subset
     * @return all subsets of size k from set s
     */
    public static Set<Set<String>> allSubsetsOfSize(Set<String> s, int k) {
        throw new UnsupportedOperationException("implement me!");
    }

    /**
     * Utility for making a new set: see usage in main method below.
     * @param elements elements to add to the set
     * @return Set of strings from the elements
     */
    public static Set<String> makeSet(String... elements) {
        Set<String> set = new HashSet<>();
        Collections.addAll(set, elements);
        return set;
    }

    public static void main(String[] args) {
        // a little demonstration of the methods
        Set<String> S = EnumeratingSubsets.makeSet("a", "b");
        System.out.println("S = " + S);
        System.out.println("allSubsets method returns " + allSubsets(S));

    }

}
