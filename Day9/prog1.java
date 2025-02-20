/*
 * Balbir Singh is working with networked systems, where servers are connected 
in a hierarchical structure, represented as a Binary Tree. Each server (node) has 
a certain processing load (integer value).

Balbir has been given a critical task: split the network into two balanced 
sub-networks by removing only one connection (edge). The total processing 
load in both resulting sub-networks must be equal after the split.

Network Structure:
- The network of servers follows a binary tree structure.
- Each server is represented by an integer value, indicating its processing load.
- If a server does not exist at a particular position, it is represented as '-1' (NULL).
	
Help Balbir Singh determine if it is possible to split the network into two equal 
processing load sub-networks by removing exactly one connection.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
1 2 3 5 -1 -1 5

        1  
    2       3
5     N   N   5

Sample Output-1:
----------------
true


Sample Input-2:
---------------
3 2 4 3 2 -1 7

Sample Output-2:
----------------
false

 */
import java.util.*;
class TreeNode{
    int val;
    TreeNode left,right;
    public TreeNode(int v){
        this.val=v;
        this.left=null;
        this.right=null;
    }
}
public class prog1{
    public static boolean check(TreeNode root,TreeNode parent){
        if(root==null || (root.left==null && root.right==null)){
            return false;
        }
        int ts=root.val;
        int ls=0,rs=0;
        if(root.left!=null) ls+=root.left.val;
        if(root.right!=null) rs+=root.right.val;
        
        if(parent!=null){
            ts+=parent.val-root.val;
        }
        
        if(ts-rs==rs || ts-ls==ls){
            return true;
        }
        return check(root.left,root)||check(root.right,root);
    }
    public static int modifytree(TreeNode root){
        if(root==null){
            return 0;
        }
        int ls=modifytree(root.left);
        int rs=modifytree(root.right);
        
        root.val+=ls+rs;
        return root.val;
    }
    public static boolean isbalanced(TreeNode root){
        modifytree(root);
        // pre(root);
        // System.out.println();
        if(root.val%2!=0) return false;
        return check(root,null);
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        String[] s=sc.nextLine().split(" ");
        
        int[] arr=new int[s.length];
        for(int i=0;i<arr.length;i++){
            arr[i]=Integer.parseInt(s[i]);
        }
        
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root=new TreeNode(arr[0]);
        int idx=0;
        q.add(root);
        
        while(!q.isEmpty()){
            TreeNode par=q.poll();
            
            if(idx+1<arr.length && arr[idx+1]!=-1){
                par.left=new TreeNode(arr[idx+1]);
                q.add(par.left);
            }
            idx++;
            if(idx+1<arr.length && arr[idx+1]!=-1){
                par.right=new TreeNode(arr[idx+1]);
                q.add(par.right);
            }
            idx++;
        }
        
        System.out.print(isbalanced(root));
    }
}