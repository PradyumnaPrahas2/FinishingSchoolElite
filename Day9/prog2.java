/*
 * Balbir Singh is working with Binary Trees.
The elements of the tree is given in the level order format.
Balbir has a task to split the tree into two parts by removing only one edge
in the tree, such that the product of sums of both the splitted-trees should be maximum.

You will be given the root of the binary tree.
Your task is to help the Balbir Singh to split the binary tree as specified.
print the product value, as the product may be large, print the (product % 1000000007)
	
NOTE: 
Please do consider the node with data as '-1' as null in the given trees.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print an integer value.


Sample Input-1:
---------------
1 2 4 3 5 6

Sample Output-1:
----------------
110

Explanation:
------------
if you split the tree by removing edge between 1 and 4, 
then the sums of two trees are 11 and 10. So, the max product is 110.


Sample Input-2:
---------------
3 2 4 3 2 -1 6

Sample Output-2:
----------------
100
 */
import java.util.*;
class TreeNode
{
    int val;
    TreeNode left,right;
    public TreeNode(int v){
        this.val=v;
        this.left=null;
        this.right=null;
    }
}
public class prog2{
    public static void check(TreeNode root,TreeNode parent,int[] top){
        if(root==null || (root.left==null && root.right==null)){
            return ;
        }
        int ts=root.val;
        int ls=0,rs=0;
        if(root.left!=null) ls+=root.left.val;
        if(root.right!=null) rs+=root.right.val;
        
        if(parent!=null){
            ts+=parent.val-root.val;
        }
        int r1=(ts-ls)*ls;
        int r2=(ts-rs)*rs;
        top[0]=Math.max(top[0],Math.max(r1,r2));
        check(root.left,root,top);
        check(root.right,root,top);
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
    public static int maxprod(TreeNode root){
        modifytree(root);
        // pre(root);
        // System.out.println();
        int[] top={Integer.MIN_VALUE};
        check(root,null,top);
        return top[0];
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
        
        System.out.print(maxprod(root));
    }

}