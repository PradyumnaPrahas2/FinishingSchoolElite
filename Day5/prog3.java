/*
 * Construct the binary tree from the given In-order and Pre-order. 
Find Nodes Between Two Levels in Spiral Order.
The spiral order is as follows:
-> Odd levels → Left to Right.
-> Even levels → Right to Left.

Input Format:
--------------
An integer N representing the number of nodes.
A space-separated list of N integers representing the in-order traversal.
A space-separated list of N integers representing the pre-order traversal.
Two integers:
Lower Level (L)
Upper Level (U)

Output Format:
Print all nodes within the specified levels, but in spiral order.

Example:
Input:
7
4 2 5 1 6 3 7
1 2 4 5 3 6 7
2 3

Output:
3 2 4 5 6 7

Explanation:
Binary tree structure:
        1
       / \
      2   3
     / \  / \
    4   5 6  7

Levels 2 to 3 in Regular Order:
Level 2 → 2 3
Level 3 → 4 5 6 7

Spiral Order:
Level 2 (Even) → 3 2 (Right to Left)
Level 3 (Odd) → 4 5 6 7 (Left to Right)

 */
import java.util.*;
class TreeNode
{
    int data;
    TreeNode left,right;
    public TreeNode(int v){
        this.data=v;
        this.left=null;
        this.right=null;
    }

}
public class prog3
{
    public static TreeNode constructtree(int[] inorder,int l,int r,HashMap<Integer,Integer> keys,Queue<Integer> queue){
        if(l>r || queue.isEmpty()){
            return null;
        }
        int valVal=queue.poll();
        int mid=keys.get(valVal);
        int root=inorder[mid];
        TreeNode parentNode=new TreeNode(root);
        parentNode.left = constructtree(inorder,l,mid-1,keys,queue);
        parentNode.right = constructtree(inorder,mid+1,r,keys,queue);
        return parentNode;
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        HashMap<Integer,Integer> keys=new HashMap<>();
        Queue<Integer> preVals=new LinkedList<>();
        
        int n=sc.nextInt();
        
        int[] inorder=new int[n];
        
        for(int i=0;i<n;i++){
            inorder[i]=sc.nextInt();
            keys.put(inorder[i],i);
        }
        for(int i=0;i<n;i++){
            preVals.add(sc.nextInt());
        }
        int low=sc.nextInt();
        int right=sc.nextInt();
        
        TreeNode answer=constructtree(inorder,0,n-1,keys,preVals);
        
        HashMap<Integer,List<Integer>> level=new HashMap<>();
        preorder(answer,level,1);
        // System.out.println(level);
        for(int i=low;i<=right;i++){
            List<Integer> ele=level.get(i);
            if(i%2==0){
                for(int j=ele.size()-1;j>=0;j--){
                    System.out.print(ele.get(j)+" ");
                }
            }
            else{
                for(int j=0;j<ele.size();j++){
                    System.out.print(ele.get(j)+" ");
                }
            }
        }
    }
    public static void preorder(TreeNode root,HashMap<Integer,List<Integer>> level,int i){
        if(root==null) return ;
        List<Integer> l=level.getOrDefault(i,new ArrayList<>());
        l.add(root.data);
        level.put(i,l);
        preorder(root.left,level,i+1);
        preorder(root.right,level,i+1);
    }
}