/*
 * VishnuVardan is working with Decision Trees for AI-based predictions.
To analyze alternative outcomes, Kishore has planned to flip the decision 
tree horizontally to simulate a reverse processing approach.

Rules for Flipping the Decision Tree:
	- The original root node becomes the new rightmost node.
	- The original left child becomes the new root node.
	- The original right child becomes the new left child.
This transformation is applied level by level recursively.

Note:
------
- Each node in the given tree has either 0 or 2 children.
- Every right node in the tree has a left sibling sharing the same parent.
- Every right node has no further children (i.e., they are leaf nodes).

Your task is to help VishnuVardan flip the Decision Tree while following 
the given transformation rules.

Input Format:
-------------
Space separated integers, nodes of the tree.

Output Format:
--------------
Print the list of nodes of flipped tree as described below.


Sample Input-1:
---------------
4 2 3 5 1

Sample Output-1:
----------------
5 1 2 3 4


Sample Input-2:
---------------
4 2 5

Sample Output-2:
----------------
2 5 4


 */
import java.util.*;

class TreeNode {
    Integer val;
    TreeNode left, right;
    
    TreeNode(Integer val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class prog1 {
    public static TreeNode finaltree = null;
    public static void addToRight(TreeNode root,int newVal){
        if(root==null){
            return;
        }
        if(root.right==null){
            root.right=new TreeNode(newVal);
            return;
        }
        addToRight(root.right,newVal);
    }
    public static void addSubtree(TreeNode root,TreeNode newNode){
        if(root==null){
            return;
        }
        if(root.left==null){
            root.left = newNode;
            return;
        }
        addSubtree(root.right,newNode);
    }
    
    public static void transformtree(TreeNode root){
        if(root==null){
            return;
        }
        if(root.left==null && root.right==null){
            return;
        }
        transformtree(root.left);
        
        TreeNode leftSubtree= finaltree;
        
        TreeNode rightNode= root.right;
        
        int curRootval= root.val;
        if(leftSubtree==null){
            leftSubtree = root.left;
        }
        
        addToRight(leftSubtree,curRootval);
        
        addSubtree(leftSubtree,rightNode);
        
        finaltree = leftSubtree;
        
        
    }
	public static TreeNode upsideDownBinaryTree(TreeNode root) {
        //Write your logic here
        if(root.left==null && root.right==null) return root;

        transformtree(root);

        return finaltree;
	}
    public static void lo(TreeNode root,HashMap<Integer,ArrayList<Integer>> lor,int l){
        if(root==null) return;
        ArrayList<Integer> a=lor.getOrDefault(l,new ArrayList<>());
        a.add(root.val);
        lor.put(l,a);
        lo(root.left,lor,l+1);
        lo(root.right,lor,l+1);
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        String[] a=sc.nextLine().split(" ");
        
        int n=a.length;
        
        int[] arr=new int[n];
        
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(a[i]);
        }
        
        TreeNode root=new TreeNode(arr[0]);
        int idx=0;
        
        Queue<TreeNode> q=new LinkedList<>();
        q.add(root);
        
        while(!q.isEmpty()){
            TreeNode p=q.poll();
            if(idx+1<n && arr[idx+1]!=-1){
                p.left=new TreeNode(arr[idx+1]);
                q.add(p.left);
            }
            idx++;
            
            if(idx+1<n && arr[idx+1]!=-1){
                p.right=new TreeNode(arr[idx+1]);
                q.add(p.right);
            }
            idx++;
        }
        HashMap<Integer,ArrayList<Integer>> l=new HashMap<>();
        TreeNode result=upsideDownBinaryTree(root);
        lo(result,l,0);
        for(int k:l.keySet()){
            for(int v:l.get(k)){
                System.out.print(v+" ");
            }
        }
    }
}
