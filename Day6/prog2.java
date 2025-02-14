/*
 * Balbir Singh is working with Binary Trees.
The elements of the tree are given in level-order format.

Balbir is observing the tree from the right side, meaning he 
can only see the rightmost nodes (one node per level).

You are given the root of a binary tree. Your task is to determine 
the nodes visible from the right side and return them in top-to-bottom order.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
A list of integers representing the node values visible from the right side


Sample Input-1:
---------------
1 2 3 5 -1 -1 5

Sample Output-1:
----------------
[1, 3, 5]



Sample Input-2:
---------------
3 2 4 3 2

Sample Output-2:
----------------
[3, 4, 2]

 */
import java.util.*;
class TreeNode{
    int data;
    TreeNode left,right;
    public TreeNode(int v){
        this.data=v;
        this.left=null;
        this.right=null;
    }
}
public class prog2{
    public static void preorder(TreeNode root,HashMap<Integer,Integer> map,int i){
        if(root==null) return;
        map.put(i,root.data);
        preorder(root.left,map,i+1);
        preorder(root.right,map,i+1);
    }
    public static void rightView(TreeNode root){
        HashMap<Integer,Integer> levelOrder=new HashMap<>();
        
        preorder(root,levelOrder,0);
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i:levelOrder.keySet()){

            ans.add(levelOrder.get(i));
        }
        System.out.print(ans);
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        String[] s=sc.nextLine().split(" ");
        
        int n=s.length;
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(s[i]);
        }
        
        TreeNode root=new TreeNode(arr[0]);
        int idx=0;
        
        Queue<TreeNode> q=new LinkedList<>();
        q.add(root);
        
        while(!q.isEmpty()){
            TreeNode parent=q.poll();
            
            if(idx+1<n && arr[idx+1]!=-1){
                parent.left=new TreeNode(arr[idx+1]);
                q.add(parent.left);
            }
            idx++;
            if(idx+1<n && arr[idx+1]!=-1){
                parent.right=new TreeNode(arr[idx+1]);
                q.add(parent.right);
            }
            idx++;
        }
        
        rightView(root);
    }
}