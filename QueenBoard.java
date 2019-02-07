public class QueenBoard{
  private int[][] board;

  public QueenBoard(int size){
    board = new int[size][size];
    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        board[x][y] = 0;
      }
    }
  }

  //private boolean addQueen(int r, int c) {}

  //private boolean removeQueen(int r, int c) {}

  /**
 *@return The output string formatted as follows:
 *All numbers that represent queens are replaced with 'Q'
 *all others are displayed as underscores '_'
 *There are spaces between each symbol:
 *"""_ _ Q _
 *Q _ _ _
 *_ _ _ Q
 *_ Q _ _"""
 *(pythonic string notation for clarity,
 *excludes the character up to the *)
 */

 public String toString(){
   String ret = "";
   for (int x = 0; x < board.length; x++) {
     for (int y = 0; y < board[0].length; y++) {
       if (board[x][y]==0){
         ret = ret + "_";
       }
     }
     ret = ret + "\n";
   }
   return ret;
 }


 /**
 *@return false when the board is not solveable and leaves the board filled with zeros;
 *        true when the board is solveable, and leaves the board in a solved state
 *@throws IllegalStateException when the board starts with any non-zero value
 */
 //public boolean solve(){}

 /**
 *@return the number of solutions found, and leaves the board filled with only 0's
 *@throws IllegalStateException when the board starts with any non-zero value
 */
 //public int countSolutions(){}


public static void main(String[] args) {
  QueenBoard A = new QueenBoard(10);
  System.out.println(A);
}
}


//countSolutions backtracks and counts all the solutions
//solve solves just one state
