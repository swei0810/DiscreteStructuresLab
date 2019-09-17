import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Colgate University COSC 290 Labs
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class SetsPractice {

    /**
     * Given string x and a set of sets, {S1, S2, ..., Sk} return a new set of
     * sets such that it contains the same sets as the input, but with x removed.
     * Note: the original inputs should NOT be modified.
     * @param sets a set of sets
     * @param x element to remove
     * @return the set of all subsets of input set s
     */
    public static Set<Set<String>> removeX(Set<Set<String>> sets, String x) {
        throw new UnsupportedOperationException("implement me!");
    }

    public static void main(String[] args) {
        // a little demonstration of the method...  please add your own test cases here
        Set<String> S1 = new HashSet<>();
        Collections.addAll(S1, "a", "b");
        System.out.println("S1 = " + S1);

        Set<String> S2 = new HashSet<>();
        Collections.addAll(S2, "a", "b", "c");
        System.out.println("S2 = " + S2);

        Set<Set<String>> sets = new HashSet<>();
        Collections.addAll(sets, S1, S2);
        System.out.println("sets = " + sets);

        Set<Set<String>> setsAfterRemove = removeX(sets, "a");
        System.out.println("setsAfterRemove = " + setsAfterRemove);

    }

}
