// Colgate University COSC 290 Labs
// Version 0.1,  2017
// Author: Michael Hay

/**
 * A class for various static methods on relations (represented as boolean double arrays)
 */
public class Relations {

    /**
     * Returns composition of R and S, denoted S ° R.  Returns relation T such that if (i, j) in R and (j, k) in S then
     * (i, k) in T.
     * @param S relation, represented as a matrix (double array)
     * @param R relation, represented as a matrix (double array)
     * @return the composition of R and S
     */
    public static boolean[][] compose(boolean[][] S, boolean[][] R) {
        // compare matrix sizes and make sure they agree: if R is n1 x n2 and S is n3 x n4, then n2 = n3.
        int n1 = R.length;
        int n3 = S.length;
        if (n1 == 0 || n3 == 0) {
            throw new UnsupportedOperationException("expecting non-empty arrays!");
        }
        int n2 = R[0].length;
        int n4 = S[0].length;
        if (n2 == 0 || n4 == 0) {
            throw new UnsupportedOperationException("expecting non-empty arrays!");
        }
        if (n2 != n3) {
            throw new UnsupportedOperationException("Number of columns of R must match number of rows of S");
        }
        boolean[][] compose = new boolean[n1][n2];
        for(int i =0; i<n1; i++){
          for(int j=0; j<n2; j++){
            for(int k =0; k<n4; k++){
              //System.out.println("i: "+i+" j: "+j+" k: "+k+" R: "+R[i][j]+" S: " + S[j][k]);
              if(R[i][j]==true && S[j][k]== true){
                compose[i][k] = true;
              }
            }
          }
        }

        return compose;
    }

    /**
     * Returns union of R and S.  Return relation T such that if (i, j) in R or (i, j) in S, then (i, j) in T.
     * @param R relation, represented as a matrix (double array)
     * @param S relation, represented as a matrix (double array)
     * @return the union of R and S
     */
    public static boolean[][] union(boolean[][] R, boolean[][] S) {
        // compare matrix sizes and make sure they agree: if R is n1 x n2 then S should be n1 x n2.
        int n1 = R.length;
        int n3 = S.length;
        if (n1 == 0 || n3 == 0) {
            throw new UnsupportedOperationException("expecting non-empty arrays!");
        }
        int n2 = R[0].length;
        int n4 = S[0].length;
        if (n1 != n3 || n2 != n4) {
            throw new UnsupportedOperationException("array dimensions must match!");
        }
        boolean[][] union = new boolean[n1][n2];

        for(int i=0; i<R.length; i++) {
          for(int j=0; j<R[i].length; j++) {
            if(R[i][j] == true) {
              union[i][j] = true;
            }
          }
        }

        for(int i=0; i<S.length; i++) {
          for(int j=0; j<S[i].length; j++) {
            if(S[i][j]==true){
              union[i][j] = true;
            }
          }
        }

        return union;
    }

    /**
     * Returns transitive closure of R.  This method uses a less efficient algorithm than Warshall's algorithm.
     * @param R relation, represented as a matrix (double array)
     * @return the transitive closure of R
     */
    public static boolean[][] transitiveClosure(boolean[][] R) {
        // compare matrix and make sure it's square: if R is n1 x n2 then n1 = n2.
        int n = R.length;
        if (R[0].length != n) {
            throw new UnsupportedOperationException("expecting an n by n boolean double array!");
        }
        boolean[][] T = R;
        for(int i=0;i<n;i++){
          T=composeAndUnion(T,R);
        }

        return T;

    }
    public static boolean[][] composeAndUnion(boolean[][] T, boolean[][]R){
      boolean[][] compose = compose(R,T);
      boolean[][] union = union(T, compose);
      return union;
    }

    // --- useful tools for debugging are provided below  ---

    /**
     * Returns string representation of R as a double array of T/F values.
     * @param R a relation, double array of booleans
     */
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

    /**
     * Returns string representation of R as a set of pairs.
     * @param R a relation, double array of booleans
     */
    public static String relationToString(boolean[][] R) {
        // please do not modify
        String descriptor = "{1.." + R.length + "} x {1.." + R[0].length + "}";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R.length; i++) {
            for (int j = 0; j < R[i].length; j++) {
                String s = "(" + (i+1) + "," + (j+1) + ")";
                if (R[i][j]) {
                    sb.append("(" + (i+1) + "," + (j+1) + ")");
                    sb.append(", ");
                }
            }
        }
        return "{" + sb.substring(0, Math.max(sb.length()-2,0)) + "}, a subset of " + descriptor;
    }

    /**
     * Returns a relation R where adjacent pairs are related.  In other words, for all i between 0 and n-1, we have (
     * i, i+1) in R.
     * @param n size of relation
     * @return relation R
     */
    public static boolean[][] makeChain(int n) {
        // please do not modify
        boolean[][] R = new boolean[n][n];
        for (int i = 0; i < R.length-1; i++) {
            R[i][i+1] = true;
        }
        return R;
    }

    public static void main(String[] args) {
         // test your implementation here!
        boolean[][] R = makeChain(8);
        // R[0][0] = true;
        boolean[][] S = makeChain(8);
        boolean[][] union = union(R, S);
        boolean[][] compose = compose(S, R);
        boolean[][] transitiveClosure = transitiveClosure(R);
        System.out.println(doubleArrayToString(R));
        System.out.println(doubleArrayToString(S));
        System.out.println(doubleArrayToString(union));
        System.out.println(doubleArrayToString(compose));
        System.out.println(doubleArrayToString(transitiveClosure));


        // throw new UnsupportedOperationException("implement me!");
    }
}
