import java.util.*;
import java.io.*;


public class SudokuBoard {
   private char[][] board;
   
   public SudokuBoard(String file){
      board = new char[9][9];
      try {
         Scanner console = new Scanner(new File(file));
         for(int r = 0; r < board.length; r++) {
            if(console.hasNextLine()){
               String line = console.nextLine();
               for(int c = 0; c < board[0].length; c++) {
                  board[r][c] = line.charAt(c);
               }
            }
         }
         console.close();
      } catch(FileNotFoundException e) {
         System.out.println("File not found!");
      }
   }
   
   public boolean isValid() {
      return checkData() && checkRow() && checkColumn() && checkMiniSquare();
   }
   
   private boolean checkData() {
      Set<Character> set = new HashSet<Character>();
      //create valid set of 1-9, space and .
      for(int i = 1; i <= 9; i++) {
         char j = (char)(i + '0');
         set.add(j);
      }
      set.add(' ');
      set.add('.');
      for(int r = 0; r < board.length; r++) {
         for(int c = 0; c < board[r].length; c++) {
            if(!set.contains(board[r][c])) {
               return false;
            }
         }
      }
      return true;  
   }
   
   private boolean checkRow() {
      for(int r = 0; r < board.length; r++) {
         Set<Character> set = new HashSet<Character>();
         for(int c = 0; c < board[r].length; c++) {
           if(set.contains(board[r][c]) && board[r][c] != '.') {
               return false;
            } else {
               set.add(board[r][c]);            
            }
         }
       }
       return true;
   }
   
   private boolean checkColumn() {
      for(int c = 0; c < board[0].length; c++) {
         Set<Character> set = new HashSet<Character>();
         for(int r = 0; r < board.length; r++) {
           if(set.contains(board[r][c]) && board[r][c] != '.') {
               return false;
            } else {
               set.add(board[r][c]);            
              }
         }
       }
       return true;
   }
   
   private char[][] miniSquare(int spot) {
      char[][] mini = new char[3][3];
      for(int r = 0; r < 3; r++) {
         for(int c = 0; c < 3; c++) {
            mini[r][c] = board[(spot-1) / 3 * 3 + r][(spot - 1) % 3 * 3 + c];
         }   
      }
      return mini; 
   }
   private boolean checkMiniSquare() {
      for(int spot = 1; spot <= 9; spot++) {
         Set<Character> set = new HashSet<Character>();
         char[][] mini = miniSquare(spot);
         for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
               if(set.contains(mini[r][c]) && mini[r][c] != '.') {
                  return false;
               } else {
                  set.add(mini[r][c]);            
              }
            }
         }
     }        
      return true;
   }
   
   public boolean isSolved() {  
      if (!isValid()) {
        return false;
      }
      Map<Character, Integer> counts = new HashMap<>();
      for (int r = 0; r < board.length; r++) {
        for (int c = 0; c < board[0].length; c++) {
            char val = board[r][c];
            int num = val - '0';          
            if (num >= 1 && num <= 9) {
                if (counts.containsKey(val)) {
                    counts.put(val, counts.get(val) + 1);
                } else {
                    counts.put(val, 1);
                }
            }
        }
    }
    return checkMap(counts);
   }

   private boolean checkMap(Map<Character, Integer> map) {
      for(int i = 1; i <= 9; i++) {
         char result = (char)(i + '0');
         if (!map.containsKey(result) || map.get(result) != 9) {
            return false;
        }
      }
      return true;
   }
   
   public String toString() {
      String divider = "----------------------";
      StringBuilder make = new StringBuilder();
      
      for(int r = 0; r < board.length; r++) {
         if(r % 3 == 0) {
            make.append(divider).append("\n");
         }
         for(int c = 0; c < board[0].length; c++) {
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
}   
/* Change log
SudokuChecker.java
------------------Topic------------------|------------------Detail------------------
1. Add  import java.util.*,              |
       import java.io.*;                 |
2. Change MySudokuBoard to               | It should be the same name as OOP class.
   SudokuBoard

SudokuBoard.java
---------------------Topic---------------------|------------------Detail------------------
1. A position of                               |- Change a position from after checkRow(), checkColumn() and checkMiniSquare()
Set<Character> set = new HashSet<Character>(); |to in the first for loop of method because I want to use new set every end of
                                               |the first loop.
2. private int[][] miniSquare(int spot),       |- Change int[][] to char[][] to the same data type as private char[][] board;                                        
   int[][] mini = new int[3][3];               | 

/* Program Output
 Checking empty board...passed.
 Checking incomplete, valid board...passed.
 Checking complete, valid board...passed.
 Checking dirty data board...passed.
 Checking row violating board...passed.
 Checking col violating board...passed.
 Checking row&col violating board...passed.
 Checking mini-square violating board...passed.
 **** HORRAY: ALL TESTS PASSED ****
*/