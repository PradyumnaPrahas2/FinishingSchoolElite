/*
 * Imagine you are navigating a maze where each path you take has a section with a 
code. The maze is structured as a series of interconnected rooms, 
represented as a tree structure. Each room in the maze has a code (integral value)
associated with it, and you are trying to check if a given sequence of code 
matches a valid path from the entrance to an exit. 

You are provide with the maze structure, where you have to build the maze and then,
you are provided with a series of space seperated digits, representing a journey 
starting from the entrance and passing through the rooms along the way. 
The task is to verify whether the path corresponding to this array of codes 
exists in the maze.

Example 1:
Input:
0 1 0 0 1 0 -1 -1 1 0 0         //maze structure
0 1 0 1                         //path to verify

Output: true

Explanation:
               0
             /   \
            1     0
           / \    /
          0   1  0
           \  / \
            1 0  0

The given path 0 → 1 → 0 → 1 is a valid path in the maze. 
Other valid sequences in the maze include 0 → 1 → 1 → 0 and 0 → 0 → 0.


Example 2:
Input:
1 2 3
1 2 3

output: false

Explanation:
The proposed path 1 → 2 → 3 does not exist in the maze, 
so it cannot be a valid path.
 */
import java.util.*;
class TreeNode{
    int data;
    TreeNode left,right;
    public TreeNode(int b){
        this.data=b;
        this.left=null;
        this.right=null;
    }
}
public class prog1{
    public static boolean isValid(TreeNode root,String[] test,int idx){
        if(idx==test.length) return true;
        if(root==null || idx>test.length) return false;
        
        int curVal = root.data;
        if(Integer.parseInt(test[idx])==root.data){
            return isValid(root.left,test,idx+1) || isValid(root.right,test,idx+1);
        }
        else{
            return false;
        }
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        String[] arr=sc.nextLine().split(" ");
        
        Queue<TreeNode> q=new LinkedList<>();
        
        TreeNode root=new TreeNode(Integer.parseInt(arr[0]));
        
        q.add(root);
        int idx=0;
        while(!q.isEmpty()){
            TreeNode par=q.poll();
            
            if(idx+1<arr.length && Integer.parseInt(arr[idx+1])!=-1){
                par.left=new TreeNode(Integer.parseInt(arr[idx+1]));
                q.add(par.left);
            }
            idx++;
            
            if(idx+1<arr.length && Integer.parseInt(arr[idx+1])!=-1){
                par.right=new TreeNode(Integer.parseInt(arr[idx+1]));
                q.add(par.right);
            }
            idx++;
        }
        
        String[] test = sc.nextLine().split(" ");
        System.out.print(isValid(root,test,0));
    }
    
}