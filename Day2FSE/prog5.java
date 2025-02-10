package Day2;
/*
 * Write a program to construct a binary tree from level-order input, while treating -1 
as a placeholder for missing nodes. The program reads input, constructs the tree, 
and provides an in-order traversal to verify correctness.

Input Format:
---------------
Space separated integers, level order data (where -1 indiactes null node).

Output Format:
-----------------
Print the in-order data of the tree.


Sample Input:
----------------
1 2 3 -1 -1 4 5

Sample Output:
----------------
2 1 4 3 5

Explanation:
--------------
    1
   / \
  2   3
     / \
    4   5


Sample Input:
----------------
1 2 3 4 5 6 7

Sample Output:
----------------
4 2 5 1 6 3 7

Explanation:
--------------
        1
       / \
      2   3
     / \  / \
    4  5 6  7

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
public class prog5{
    public static void inorder(TreeNode root){
        if(root==null || root.data==-1) return;
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        String[] s=sc.nextLine().split(" ");
        
        Queue<TreeNode> q=new LinkedList<>();
        
        TreeNode root=new TreeNode(Integer.parseInt(s[0]));
        int idx=0;
        q.add(root);
        while(!q.isEmpty()){
            TreeNode top=q.poll();
            if(idx+1<s.length){
                int v=Integer.parseInt(s[idx+1]);
                if(v!=-1){
                    top.left=new TreeNode(Integer.parseInt(s[idx+1]));
                    q.add(top.left);
                }
            }
            idx++;
            if(idx+1<s.length){
                int v=Integer.parseInt(s[idx+1]);
                if(v!=-1){
                    top.right=new TreeNode(Integer.parseInt(s[idx+1]));
                    q.add(top.right);
                }
            }
            idx++;
        }
        inorder(root);
    }
}