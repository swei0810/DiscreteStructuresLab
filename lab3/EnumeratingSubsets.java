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
        if(s.size()==0){
          return new HashSet<>(new HashSet<>());
        }else{
          Iterator<String> itr = s.iterator();
          String firstElement = itr.next();
          Set<String> s2 = new HashSet<>();
          while(itr.hasNext()){
            s2.add(itr.next());
          }
          System.out.println("First Element: "+firstElement+" S2 : "+s2);
          Set<Set<String>> powerSet = unionSetElements(firstElement,allSubsets(s2));
          return powerSet;
        }
    }


    public static Set<Set<String>> unionSetElements(String element, Set<Set<String>> subsets) {

        Set<Set<String>> copySubsets = new HashSet<>();
        copySubsets.addAll(subsets);
        Iterator<Set<String>> itr = copySubsets.iterator();
        while(itr.hasNext()){
          Set<String> innerSet = itr.next();
          Iterator<String> innerSetItr = innerSet.iterator();
          Set<String> newInnerSet = new HashSet<>();
            while(innerSetItr.hasNext()){
              String innerElement = innerSetItr.next();
              if(innerElement!=null){
                newInnerSet.add(innerElement);
                newInnerSet.add(element);
                subsets.add(newInnerSet);
              }
            }
        }

        // add first element set
        Set<String> s = new HashSet<>();
        s.add(element);
        System.out.println("s: " + s);
        System.out.println(subsets);
        subsets.add(s);
        System.out.println(subsets);

        return subsets;
    }

    /**
     * Given a set s, containing elements of type String, return set of all subsets of s such that each
     * subset contains k elements
     * @param s a set of elements
     * @param k the desired number of elements in each subset
     * @return all subsets of size k from set s
     */
    // public static Set<Set<String>> allSubsetsOfSize(Set<String> s, int k) {
    //   // Set<Set<String>> validSubSets = new HashSet<>();
    //   // Set<Set<String>> powerSets = allSubsets(s);
    //   // Iterator<Set<String>> itr = s.iterator();
    //   // while () {
    //   //
    //   // }
    //
    //   return ;
    // }

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
        Set<String> S = EnumeratingSubsets.makeSet("a", "b", "c");
        Set<Set<String>> S2 = new HashSet<>(new HashSet<>());
        // System.out.println("S = " + S);
        // Set<Set<String>> s = allSubsets(S);
        System.out.println(unionSetElements("a",S2));
        // System.out.println("allSubsets method returns " + s +"Size: "+s.size());

    }

}
