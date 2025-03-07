/*
 * Pranav has a puzzle board filled with square boxes in the form of a grid. Some 
cells in the grid may be empty. '0' - indicates empty, '1' - indicates a box. 

The puzzle board has some patterns formed with boxes in it, 
the patterns may be repeated. The patterns are formed with boxes (1's) only, 
that are connected horizontally and vertically but not diagonally.

Pranav wants to find out the number of unique patterns in the board.

You are given the board in the form of a grid M*N, filled wth 0's and 1's.
Your task is to help Pranav to find the number of unique patterns in 
the puzzle board.

Input Format:
-------------
Line-1: Two integers M and N, the number of rows and columns in the grid-land.
Next M lines: contains N space-separated integers [0, 1].

Output Format:
--------------
Print an integer, the number of unique patterns in the puzzle board.


Sample Input-1:
---------------
5 5
0 1 0 1 1
1 1 1 0 1
0 1 0 1 0
1 0 1 1 1
1 1 0 1 0

Sample Output-1:
----------------
3

Explanation-1:
------------
The unique patterns are as follows:
  1			1 1	    1 
1 1 1		  1 ,	1 1
  1	   ,	
   
   
Sample Input-2:
---------------
6 6
1 1 0 0 1 1
1 0 1 1 0 1
0 1 0 1 0 0
1 1 0 0 0 1
0 0 1 0 1 1
1 1 0 1 0 0

Sample Output-2:
----------------
5

Explanation-2:
------------
The unique patterns are as follows:
1 1		1 1		    1		1 1	,	1
1   ,     1 ,	    1 1 ,		


 */
import java.util.*;
public class prog2{
    public static StringBuilder sb=new StringBuilder();
    public static void dfs(int[][] grid,int i,int j,int m,int n,int p,int q){
        if(i>=m || j>=n || i<0 || j<0 || grid[i][j]==0) return;
        
        sb.append(p+":"+q+",");
        grid[i][j]=0;
        dfs(grid,i+1,j,m,n,p+1,q);
        dfs(grid,i,j+1,m,n,p,q+1);
        dfs(grid,i-1,j,m,n,p-1,q);
        dfs(grid,i,j-1,m,n,p,q-1);
    }
    public static int distinct(int[][] grid,int m,int n){
        HashSet<String> set=new HashSet<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    sb=new StringBuilder();
                    dfs(grid,i,j,m,n,0,0);
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int m=sc.nextInt();
        int n=sc.nextInt();
        
        int[][] grid=new int[m][n];
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                grid[i][j]=sc.nextInt();
            }
        }
        
        System.out.print(distinct(grid,m,n));
    }
}