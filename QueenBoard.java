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

  private boolean addQueen(int r, int c) {
    if (board[r][c]==0 && safe(r,c)) {
      board[r][c] = -1;
      return true;
    }
    return false;
  }

  private boolean removeQueen(int r, int c) {
    board[r][c] = 0;
    return true;
  }

  private boolean safe(int r, int c){
    //checks left
    for (int x = 0; x<c; x++) {
      if (board[r][x]==-1) return false;
    }

    //checks right
    for (int x = c; x < board.length[0];x++) {
      if (board[r][x]==-1) return false;
    }

    //checks up
    for (int x = 0; x<r; x++) {
      if (board[x][c]==-1) return false;
    }

    //checks down
    for (int x = r; x < board.length; x++) {
      if (board[x][c]==-1) return false;
    }

    //checks upperleft diagonal
    for (int x = r, y = c; x >= 0 && y >= 0; x--, y--) {
      if (board[x][y]==-1) return false;
    }

    //checks upperright diagonal
    for (int x = r, y = c; x >=0 && y < board.length[0]; x--, y++) {
      if (board[x][y]==-1) return false;
    }

    //checks lowerleft diagonal
    for (int x = r, y = c; x < board.length && y >= 0; x++, y--) {
      if (board[x][y]==-1) return false;
    }

    //checks lowerright diagonal
    for (int x = r, y = c; x < board.length, y < board.length[0]; x++, y++) {
      if (board[x][y]==-1) return false;
    }

  }

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
          if (y == board[0].length-1) {
            ret = ret + "_"; //for the last column in each row, there is no space after it
          } else {
            ret = ret + "_ ";
          }
        }
        if (board[x][y]==-1){
          if (y == board[0].length-1) {
            ret = ret + "Q";
          } else {
            ret = ret + "Q ";
          }
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
 public boolean solve(){
   for (int x = 0; x < board.length; x++) {
     for (int y = 0; y < board[0].length; y++) {
       if (board[x][y] != 0) {
         throw new IllegalStateException();
        }
      }
    }
   return sHelp(0);
 }

 /**
 *@return the number of solutions found, and leaves the board filled with only 0's
 *@throws IllegalStateException when the board starts with any non-zero value
 */
 public int countSolutions(){}


//--------------------------------
//Helper Methods for various tasks

private boolean sHelp(int c) {
  for (int r = 0; r < board.length; r++) {
    if (safe(r,c)) {
      addQueen(r,c);
      sHelp(c+1);
    } else {
      removeQueen(r,c);
    }
  }
}


private int cSHelp(){

}

 public static void main(String[] args) {
     QueenBoard A = new QueenBoard(10);
     A.addQueen(1,1);
     A.addQueen(1,9);
     A.removeQueen(1,9);
     A.addQueen(3,9);
     System.out.println(A);
 }

}


//countSolutions backtracks and counts all the solutions
//solve solves just one state
