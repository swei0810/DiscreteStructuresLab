import java.util.*;

/**
 * Colgate University COSC 290 Labs
 * Version 0.1,  2017
 *
 * @author Michael Hay
 */
public class BoardCounting {

    public static int numRecursiveCalls = 0;
    public static int numRecursiveCallsDistinct = 0;

    /**
     * DLN 9.100
     * @return number of leaves in game tree (when you play til board is completely filled in)
     */
    public static int countFilledIn() {
        return countFilledIn(new Board());
    }

    /**
     * Variation on DLN 9.100
     * @param b current board
     * @return number of leaves in game subtree whose root is board b
     */
    public static int countFilledIn(Board b) {
      if (b.isFull()) {
        return 1;
      }
      // System.out.println(b);
      int sum =0;
      for (int i=0; i<b.size(); i++ ) {
        for (int j=0; j<b.size(); j++) {
          if (b.isOpen(i,j)) {

            b.placeMark(i,j,b.nextPlayer());
            sum += countFilledIn(b);
            b.removeMark(i,j);
          }
        }
      }
      return sum;
        // throw new UnsupportedOperationException("implement me!");
    }

    /**
     * DLN 9.101
     * @return number of distinct leaves in game tree (when you play til board is completely filled in)
     */
    public static int countDistinctFilledIn() {
        return countDistinctFilledIn(new Board());
    }

    /**
     * Variation on DLN 9.101
     * @param b current board
     * @return number of distinct leaves in game subtree whose root is board b
     */
    public static int countDistinctFilledIn(Board b) {
      // System.out.println(distinctBoards(b, new HashSet<Board>()));
        return distinctBoards(b, new HashSet<Board>()).size();

    }

    public static Set distinctBoards(Board b, Set<Board> boards) {
          if (b.isFull()) {
            Board newBoard = b.copy();
            boards.add(newBoard);
            return boards;
          }

          for (int i=0; i<b.size(); i++ ) {
            for (int j=0; j<b.size(); j++) {
              if (b.isOpen(i,j)) {
                b.placeMark(i,j,b.nextPlayer());
                distinctBoards(b, boards);
                b.removeMark(i,j);
              }
            }
          }
          return boards;
    }
    /**
     * DLN 9.102
     * @return number of boards in game tree (when you play til board is completely filled in)
     */
    public static int countBoardsInTree() {
        return countBoardsInTree(new Board());
    }

    /**
     * Variation on DLN 9.102
     * @param b current board
     * @return number of boards in game subtree whose root is board b
     */
    public static int countBoardsInTree(Board b) {
      // int numRecursiveCalls = 0;
      if (b.isFull()) {
        return 1;
      }
      // System.out.println(b);
      int sum =0;
      for (int i=0; i<b.size(); i++ ) {
        for (int j=0; j<b.size(); j++) {
          if (b.isOpen(i,j)) {

            b.placeMark(i,j,b.nextPlayer());
            numRecursiveCalls += countBoardsInTree(b);
            numRecursiveCalls++;
            b.removeMark(i,j);
          }
        }
      }
      return numRecursiveCalls;
    }


    /**
     * DLN 9.103
     * @return number of distinct boards in game tree (when you play til board is completely filled in)
     */
    public static int countDistinctBoardsInTree() {
        return countDistinctBoardsInTree(new Board());
    }

    /**
     * Variation on DLN 9.103
     * @param b current board
     * @return number of distinct boards in game subtree whose root is board b
     */
    public static int countDistinctBoardsInTree(Board b) {
      //throw new UnsupportedOperationException("implement me!");
      // System.out.println(distinctBoardsInTree(b,new HashSet<Board>()));
      return distinctBoardsInTree(b,new HashSet<Board>()).size();
    }


    public static Set distinctBoardsInTree(Board b, Set<Board> boards) {
          if (b.isFull()) {
            Board newBoard = b.copy();
            boards.add(newBoard);
            return boards;
          }


          for (int i=0; i<b.size(); i++ ) {
            for (int j=0; j<b.size(); j++) {
              if (b.isOpen(i,j)) {
                b.placeMark(i,j,b.nextPlayer());
                Board board = b.copy();
                boards.add(board);
                // Board board = b.copy();
                // boards.add(board);
                // System.out.println(boards.contains(board));

                // System.out.println(board);
                // System.out.println(boards);
                distinctBoardsInTree(b, boards);
                b.removeMark(i,j);
              }
            }
          }
          return boards;
    }
    /**
     * DLN 9.104
     * @return number of unique boards, accounting for symmetry, in game tree (when you play til board is completely filled in)
     */
    public static int countWithSymmetry() {
        return countWithSymmetry(new Board());
    }

    /**
     * Variation on DLN 9.104
     * @param b current board
     * @return number of number of unique boards, accounting for symmetry, in game subtree whose root is board b
     */
    public static int countWithSymmetry(Board b) {
        throw new UnsupportedOperationException("implement me!");
    }


    /**
     * DLN 9.105
     * @return number of unique boards, accounting for symmetry and wins, in game tree (when you play til board is completely filled in)
     */
    public static int countWithSymmetryWins() {
        return countWithSymmetryWins(new Board());
    }

    /**
     * Variation on DLN 9.105
     * @param b current board
     * @return number of number of unique boards, accounting for symmetry and wins, in game subtree whose root is board b
     */
    public static int countWithSymmetryWins(Board b) {
        throw new UnsupportedOperationException("implement me!");
    }


    public static void main(String[] args) {
        // example:
        System.out.println("Problem 9.100: the number of filled-in boards is: " + countFilledIn());
        System.out.println("Problem 9.101: the number of distinct filled-in boards is: " + countDistinctFilledIn());
        System.out.println("Problem 9.102: the number of total boards: " + countBoardsInTree());
        System.out.println("Problem 9.103: the number of total boards for distinct: " + countDistinctBoardsInTree());


        // feel free to test your methods here
    }
}
