/*
 * Imagine you're an adventurer with a mystical treasure map. This map is a grid of 
ancient runes, where each cell holds a single character. Legend has a 
powerful incantation—represented as a string—is hidden within these runes. 
To unlock the treasure, you must verify if the incantation exists on the map.

The incantation is formed by linking runes that are directly next to each other 
either horizontally or vertically. Each rune on the map can only be used once in
the incantation.

Your Task:  
Given an m x n grid representing the treasure map and a string representing the 
incantation, return true if the incantation can be traced on the map; 
otherwise, return false.


Example 1:
----------
Input:  
3 4
ABCD
SFCS
ADEE
ABCCED

Output:
ABCCED can be traced

Explanation (check hint)
Treasure Map Grid:  
[
  ["A", "B", "C", "E"],
  ["S", "F", "C", "S"],
  ["A", "D", "E", "E"]
]

Incantation: "ABCCED" exists in map


Example 2:
----------
Input:
3 4
ABCE
SFCS
ADEE
ABCB

Output: 
ABCB cannot be traced

Explanation:
Treasure Map Grid:  

[
  ["A", "B", "C", "E"],
  ["S", "F", "C", "S"],
  ["A", "D", "E", "E"]
]

Incantation: "ABCB" does not exist in map


Constraints:

- m == the number of rows in the grid  
- n == the number of columns in the grid  
- 1 <= m, n <= 6  
- 1 <= incantation length <= 15  
- The grid and incantation consist only of uppercase and lowercase English letters.

 */
import java.util.*;
public class Solution{
    public static boolean find(char[][] board,int i,int j,String word,int idx){
        if(idx==word.length()) return true;
        
        if(i<0 || j<0 || i>=board.length || j>=board[0].length || board[i][j]!=word.charAt(idx)){
            return false;
        }
        char temp=board[i][j];
        board[i][j]=',';
        boolean res=find(board,i+1,j,word,idx+1)||find(board,i-1,j,word,idx+1)||find(board,i,j+1,word,idx+1)||find(board,i,j-1,word,idx+1);
    
        board[i][j]=temp;
        return res;
    }
    public static boolean exist(char[][] board,String word){
        boolean ans=false;
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(word.charAt(0)==board[i][j])
                ans=ans||find(board,i,j,word,0);
            }
        }
        return ans;
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        int m=sc.nextInt();
        int n=sc.nextInt();
        
        char[][] board=new char[m][n];
        sc.nextLine();
        for(int i=0;i<m;i++){
            
            String s=sc.nextLine();
            // System.out.println(s);
            for(int j=0;j<s.length();j++){
                board[i][j]=s.charAt(j);
            }
        }
        // sc.nextLine();
        String word=sc.nextLine();
        // System.out.println(word);
        // System.out.println(Arrays.deepToString(board));
        boolean found=new Solution().exist(board,word);
        
        if(found){
            System.out.println(word+" can be traced");
        }
        else{
            System.out.println(word+" cannot be traced");
        }
    }
}