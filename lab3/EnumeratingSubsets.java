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
          // System.out.println("First Element: "+firstElement+" S2 : "+s2);
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
            newInnerSet.add(innerElement);
            newInnerSet.add(element);
            subsets.add(newInnerSet);
          }
        }
        // add first element set and empty set
        Set<String> s = new HashSet<>();
        s.add(element);
        subsets.add(s);
        subsets.add(new HashSet<>());
        return subsets;
    }

    /**
     * Given a set s, containing elements of type String, return set of all subsets of s such that each
     * subset contains k elements
     * @param s a set of elements
     * @param k the desired number of elements in each subset
     * @return all subsets of size k from set s
     */
    public static Set<Set<String>> allSubsetsOfSize(Set<String> s, int k) {
      if (k==0) {
        return new HashSet<>(new HashSet<>());
      }
      if (k==s.size()){
        Set<Set<String>> set = new HashSet<>();
        set.add(s);
        return set;
      }else{
        Iterator<String> itr = s.iterator();

        String firstElement = itr.next();
        Set<String> s2 = new HashSet<>();
        while(itr.hasNext()){
          s2.add(itr.next());
        }
        System.out.println("Set: "+ s2);
        Set<Set<String>> s3 = allSubsetsOfSize(s2,k-1);
        Set<Set<String>> sets = union(firstElement,s3, k-1);
        //sets.addAll(union(firstElement,allSubsetsOfSize(s2,k-1), k-1));
        sets.addAll(allSubsetsOfSize(s2,k));
        System.out.println("sets: " + sets);
        return sets;
      }
    }

    public static Set<Set<String>> union(String element, Set<Set<String>> subsets, int k) {

            // Set<Set<String>> copySubsets = new HashSet<>();
            //copySubsets.addAll(subsets);
            System.out.println("Element: "+element + "Subsets: "+subsets);
            Iterator<Set<String>> itr = subsets.iterator();
            while(itr.hasNext()){
              Set<String> innerSet = itr.next();
              innerSet.add(element);
            }
            System.out.println("Subsets: "+subsets);
            return subsets;
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
        Set<String> S = EnumeratingSubsets.makeSet("a", "b", "c", "d");
        Set<Set<String>> S2 = new HashSet<>(new HashSet<>());
        // System.out.println("S = " + S);
        Set<Set<String>> s = allSubsets(S);
        // System.out.println(unionSetElements("a",S2));
        System.out.println("allSubsets method returns " + s);

        int k = 3;
        Set<Set<String>> s3 = allSubsetsOfSize(S, k);
        System.out.println("All subsets of size" + k +"returns"  + s3);

    }

}
