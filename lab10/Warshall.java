// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay

import java.util.NoSuchElementException;

/**
 * A class for computing transitive closure using Warshall's algorithm
 */
public class Warshall {

    /**
     * Returns transitive closure of R.  This method uses Warshall's algorithm.
     * @param R relation, represented as a matrix (double array)
     * @return the transitive closure of R
     */
    public static boolean[][] transitiveClosure(boolean[][] R) {
      int n = R.length;
      if (R[0].length != n) {
          throw new UnsupportedOperationException("expecting an n by n boolean double array!");
      }
      boolean[][] T = R;
      for(int i=0;i<n;i++){
        for(int j=0; j<n; j++){
          for (int k=0; k<n; k++){
            T[j][k]= T[j][k] || (T[j][i] && T[i][k]);
          }
        }
      }

      return T;

    }

  
    public static String doubleArrayToString(boolean[][] R) {
        // please do not modify
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R.length; i++) {
            for (int j = 0; j < R[i].length; j++) {
                sb.append((R[i][j]? "T":"F") + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    public static boolean[][] makeChain(int n) {
        // please do not modify
        boolean[][] R = new boolean[n][n];
        for (int i = 0; i < R.length-1; i++) {
            R[i][i+1] = true;
        }
        return R;
    }
    /**
     * Compare the runtimes of the two methods for transitive closure.  {@see System#currentTimeMillis()}
     * @param args
     */
    public static void main(String[] args) {
         // test your implementation here!
         boolean[][] R = makeChain(8);
         boolean[][] transitiveClosure = transitiveClosure(R);
         System.out.println(doubleArrayToString(transitiveClosure));
    }

}
