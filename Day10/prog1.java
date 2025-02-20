/*
 * You are a gardener designing a beautiful floral pathway in a vast botanical 
garden. The garden is currently overgrown with plants, trees, and bushes 
arranged in a complex branching structure, much like a binary tree. Your task 
is to carefully prune and rearrange the plants to form a single-file walking 
path that visitors can follow effortlessly.

To accomplish this, you must flatten the garden’s layout into a linear sequence 
while following these rules:
    1. The garden path should maintain the same PlantNode structure, 
       where the right branch connects to the next plant in the sequence, 
       and the left branch is always trimmed (set to null).
    2. The plants in the final garden path should follow the same arrangement 
       as a pre-order traversal of the original garden layout. 

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print the list.


Sample Input:
-------------
1 2 5 3 4 -1 6

Sample Output:
--------------
1 2 3 4 5 6


Explanation:
------------
input structure:
       1
      / \
     2   5
    / \    \
   3   4    6
   
output structure:
	1
	 \
	  2
	   \
		3
		 \
		  4
		   \
			5
			 \
			  6

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
public class prog1{
    public static void addsubtree(TreeNode root,TreeNode s){
        if(root==null){
            return;
        }
        if(root.right==null){
            root.right=s;
            return;
        }
        addsubtree(root.right,s);
    }
    public static void transformtree(TreeNode root){
        if(root==null || (root.left==null && root.right==null)){
            return;
        }
        transformtree(root.left);
        TreeNode righttree=root.right;
        
        root.right=root.left;
        
        root.left=null;
        
        addsubtree(root,righttree);
        
        transformtree(root.right);
        
    }
    public static void in(TreeNode root,int i,HashMap<Integer,List<Integer>> map){
        if(root==null) return;
        List<Integer> l=map.getOrDefault(i,new ArrayList<>());
        l.add(root.data);
        map.put(i,l);
        in(root.left,i+1,map);
        in(root.right,i+1,map);
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        String[] a=sc.nextLine().split(" ");
        
        int[] arr=new int[a.length];
        for(int i=0;i<a.length;i++){
            arr[i]=Integer.parseInt(a[i]);
        }
        
        TreeNode root=new TreeNode(arr[0]);
        int idx=0;
        
        Queue<TreeNode> q=new LinkedList<>();
        q.add(root);
        
        while(!q.isEmpty()){
            TreeNode p=q.poll();
            
            if(idx+1<a.length && arr[idx+1]!=-1){
                p.left=new TreeNode(arr[idx+1]);
                q.add(p.left);
            }
            idx++;
            if(idx+1<a.length && arr[idx+1]!=-1){
                p.right=new TreeNode(arr[idx+1]);
                q.add(p.right);
            }
            idx++;
        }
        
        transformtree(root);
        HashMap<Integer,List<Integer>> map=new HashMap<>();
        in(root,0,map);
        // System.out.println(map);
        for(int i:map.keySet()){
            System.out.print(map.get(i).get(0)+" ");
        }
    }
}