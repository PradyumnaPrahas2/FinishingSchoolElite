package Day2;
/*
 * You are developing an application for a garden management system where each tree 
in the garden is represented as a binary tree structure. The system needs to 
allow users to plant new trees in a systematic way, ensuring that each tree is 
filled level by level.

A gardener wants to:
 - Plant trees based on user input.
 - Ensure trees grow in a balanced way by filling nodes level by level.
 - Inspect the garden layout by performing an in-order traversal, which helps 
   analyze the natural arrangement of trees.

Your task is to implement a program that:
    - Accepts a list of N tree species (as integers).
    - Builds a binary tree using level-order insertion.
    - Displays the in-order traversal of the tree.

Input Format:
-------------
- An integer N representing the number of tree plants.
- A space-separated list of N integers representing tree species.

Output Format:
--------------
A list of integers, in-order traversal of tree.


Sample Input:
-------------
7
1 2 3 4 5 6 7

Sample Output:
--------------
4 2 5 1 6 3 7


Explanation:
------------
The tree looks like this:

        1
       / \
      2   3
     / \  / \
    4   5 6  7
The in order is : 4 2 5 1 6 3 7

 */
import java.util.*;
class TreeNode{
    TreeNode left,right;
    int data;
    public TreeNode(int val){
        this.data=val;
        this.left=null;
        this.right=null;
    }
}
public class prog4{
    public static void inorder(TreeNode root){
        if(root==null) return;
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        int n=sc.nextInt();
        
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        Queue<TreeNode> q=new LinkedList<>();
        int idx=0;
        TreeNode parent=new TreeNode(arr[0]);
        q.add(parent);
        while(!q.isEmpty()){
            TreeNode root=q.poll();
            if(idx+1<n){
                root.left=new TreeNode(arr[idx+1]);
                q.add(root.left);
            }
            idx++;
            if(idx+1<n){
                root.right=new TreeNode(arr[idx+1]);
                q.add(root.right);
            }
            idx++;
        }
        inorder(parent);
    }
}