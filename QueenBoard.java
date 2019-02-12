public class QueenBoard{
  private int[][] board;

  //constructor
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

  //checks if a Queen can be placed on a certain tile
  //achieved by checking all 8 directions for another Queen
  //if another Queen is found, that Queen is attacking the square, and a new Queen cannot be placed
  //otherwise that tile is good
  private boolean safe(int r, int c){
    //checks left
    for (int x = 0; x<c; x++) {
      if (board[r][x]==-1) return false;
    }

    //checks right
    for (int x = c; x < board[0].length;x++) {
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
    for (int x = r, y = c; x >=0 && y < board[0].length; x--, y++) {
      if (board[x][y]==-1) return false;
    }

    //checks lowerleft diagonal
    for (int x = r, y = c; x < board.length && y >= 0; x++, y--) {
      if (board[x][y]==-1) return false;
    }

    //checks lowerright diagonal
    for (int x = r, y = c; x < board.length && y < board[0].length; x++, y++) {
      if (board[x][y]==-1) return false;
    }
    return true;
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
   if (board.length == 0) return true; //special case: 0*0 is solvable
   for (int x = 0; x < board.length; x++) { //if board starts with any non-zero val, throws exception
     for (int y = 0; y < board[0].length; y++) {
       if (board[x][y] != 0) {
         throw new IllegalStateException();
        }
      }
    }
    if (sHelp(0)) {
      return true;
    }
    clear(); //if not solvable, clears, and returns false
    return false;
 }

 /**
 *@return the number of solutions found, and leaves the board filled with only 0's
 *@throws IllegalStateException when the board starts with any non-zero value
 */
 public int countSolutions(){
   if (board.length == 0) return 1; //special case: 0*0 has exactly 1 solution
   for (int x = 0; x < board.length; x++) { //if board starts with any non-zero val, throws exception
     for (int y = 0; y < board[0].length; y++) {
       if (board[x][y] != 0) {
         throw new IllegalStateException();
        }
      }
    }
    int ret = cSHelp(0);
    clear();
    return ret;
  }

//--------------------------------
//Helper Methods for the two main methods solve() and countSolutions(), and clear() to clear board

private boolean sHelp(int c) {
  if (c >= board[0].length) return true; //base case
  for (int r = 0; r < board.length; r++) {
    if (safe(r,c)) {
      addQueen(r,c);
      if (sHelp(c+1)) return true;
      removeQueen(r,c);
    }
  }
  return false;
}


private int cSHelp(int c){
  int count = 0; //keeps count of the amount of solutions
  if (c >= board.length) return 1; //base case
  for (int r = 0; r < board.length; r++) {
    if (addQueen(r,c) == true) count = count + cSHelp(c + 1);
    removeQueen(r,c);
  }
  return count;
}

//clears board, everything becomes 0
 private void clear() {
   for (int x = 0; x < board.length; x++) {
     for (int y = 0; y < board[0].length; y++) {
       board[x][y]=0;
     }
   }
 }

/*
 public static void main(String[] args) {
 //testing 0,1,2,3,4,5,8,10 & comparing to actual solutions from wikipedia
 //these do match up
     QueenBoard A = new QueenBoard(0);
     System.out.println(A.countSolutions());
     System.out.println(A.solve());
     System.out.println(A);

     QueenBoard B = new QueenBoard(1);
     System.out.println(B.countSolutions());
     System.out.println(B.solve());
     System.out.println(B);

     QueenBoard C = new QueenBoard(2);
     System.out.println(C.countSolutions());
     System.out.println(C.solve());
     System.out.println(C);

     QueenBoard D = new QueenBoard(3);
     System.out.println(D.countSolutions());
     System.out.println(D.solve());
     System.out.println(D);

     QueenBoard E = new QueenBoard(4);
     System.out.println(E.countSolutions());
     System.out.println(E.solve());
     System.out.println(E);

     QueenBoard F = new QueenBoard(5);
     System.out.println(F.countSolutions());
     System.out.println(F.solve());
     System.out.println(F);

     QueenBoard G = new QueenBoard(8);
     System.out.println(G.countSolutions());
     System.out.println(G.solve());
     System.out.println(G);

     QueenBoard H = new QueenBoard(10);
     System.out.println(H.countSolutions());
     System.out.println(H.solve());
     System.out.println(H);
 }
*/

}
