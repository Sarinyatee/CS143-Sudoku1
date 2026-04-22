import java.util.*;
import java.io.*;


public class SudokuBoard {
   private char[][] board;
   private Set<Char> set = new HashSet<Char>();
   
   public SudokuBoard(String file){
      board = new char[9][9];
      try {
         Scanner console = new Scanner(new File(file));
         for(int r = 0; r < 9; r++) {
            if(console.hasNextLine()){
               String line = console.nextLine();
               for(int c = 0; c < 9; c++) {
                  board[r][c] = line.charAt(c);
               }
            }
         }
         console.close();
      } catch(FileNotFoundException e) {
         System.out.println("File not found!");
      }
   }
   
   public boolean isValid(char[][] board) {
      if(noData() == true){ return false; }
      else if {checkRow == true)
      
      
      checkColumn();
      
      

   }
   
   private boolean checkRow(char[][] board) {
      Set<Charcater> set = new HashSet<Character>();
      //check row
      for(int r = 0; r < board[r].length; r++) {
         for(int c = 0; c < length[0].length; c++) {
           if(set.contains(board[r][c]) && board[r][c] != '.') {
            return false;
            } else {
               set.add(board[r][c]);            
            }
       }
   }
   
   private boolean checkColumn(char[][] board) {
      Set<Char> set = new HashSet<Char>();
      //check column
      for(int c = 0; c < length[0].length; c++) {
         for(int r = 0; r < board[r].length; r++) {
           if(set.contains(board[r][c]) && board[r][c] != '.') {
            return false;
            } else {
               set.add(board[r][c]);            
            }
       }
   }
   
   private int[][] miniSquare(int spot) {
      int[][] mini = new int[3][3];
      for(int r = 0; r < 3; r++) {
         for(int c = 0; c <3; c++) {
            mini[r][c] = board[(spot-1) / 3 * 3 + r][(spot - 1) % 3 * 3 + c];
         }   
      }
      return mini; 
   }
   private boolean checkSquare(char[][] board) {
       Set<Char> set = new HashSet<Char>();
       // There is 9 spots
       for( int i = 1; i <= 9; i++) {
         miniSquare(i);
           
       }

   }
    
   public String toString() {
      String divider = "----------------------";
      StringBuilder make = new StringBuilder();
      
      for(int r = 0; r < 9; r++) {
         if(r % 3 == 0) {
            make.append(divider).append("\n");
         }
         for(int c = 0; c < 9; c++) {
            if(c % 3 == 0) {
               make.append("|");
            }
            make.append(board[r][c]).append(" ");
         }
         make.append("|\n");
      }
      make.append(divider);
      return make.toString();
   }
   

   
 //     SudokuBoard board = new SudokuBoard("data1.sdk");
 //     System.out.println(board);
}
/*----------------------
 |2 . . |1 . 5 |. . 3 |
 |. 5 4 |. . . |7 1 . |
 |. 1 . |2 . 3 |. 8 . |
 ----------------------
 |6 . 2 |8 . 7 |3 . 4 |
 |. . . |. . . |. . . |
 |1 . 5 |3 . 9 |8 . 6 |
 ----------------------
 |. 2 . |7 . 1 |. 6 . |
 |. 8 1 |. . . |2 4 . |
 |7 . . |4 . 2 |. . 1 |
 ----------------------