/*
 * Given the preorder and postorder traversals of a binary tree, construct 
the original binary tree and print its level order traversal.

Input Format:
---------------
Space separated integers, pre order data
Space separated integers, post order data

Output Format:
-----------------
Print the level-order data of the tree.


Sample Input:
----------------
1 2 4 5 3 6 7

4 5 2 6 7 3 1

1-> 0,6
left -> 2 idx=2 -> left subtree= 0,1 -> left -> 4 -> left subtree=0,0 return
                                        right -> 5 -> right subtree=1,1 return
right -> 3 idx=5 -> right subtree= 3,4 -> left -> 6 -> left subtree=3,3 return
                                        right -> 7 -> right subtree=4,4 return
Sample Output:
----------------
[[1], [2, 3], [4, 5, 6, 7]]

Explanation:
--------------
        1
       / \
      2   3
     / \  / \
    4   5 6  7
    
    


Sample Input:
----------------
1 2 3
2 3 1

Sample Output:
----------------
[[1], [2, 3]]

Explanation:
--------------
    1
   / \
  2  3
  
 
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
    public static TreeNode constructTree(int[] pre,HashMap<Integer,Integer> postMap,int l,int r,int[] idx){
        if(l>r || idx[0]>=pre.length){
            return null;
        }
        int cur=pre[idx[0]];
        TreeNode root=new TreeNode(cur);
        idx[0]++;
        if(l==r || idx[0]>=pre.length){
            return root;
        }
        int id=postMap.get(pre[idx[0]]);
        // System.out.println("root "+cur);
        // System.out.println(" left "+l+" mid "+id);
        // System.out.println(" mid+1 "+(id+1)+" right "+(r-1));
        root.left=constructTree(pre,postMap,l,id,idx);
        root.right=constructTree(pre,postMap,id+1,r-1,idx);
        return root;
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        String[] preorder,postorder;
        
        preorder=sc.nextLine().split(" ");
        postorder=sc.nextLine().split(" ");
        // System.out.println(Arrays.toString(preorder));
        int[] pre=new int[preorder.length];
        int[] post=new int[postorder.length];
        int n=preorder.length;
        HashMap<Integer,Integer> postMap=new HashMap<>();
        for(int i=0;i<n;i++){
            pre[i]=Integer.parseInt(preorder[i]);
        }
        for(int i=0;i<n;i++){
            post[i]=Integer.parseInt(postorder[i]);
            postMap.put(post[i],i);
        }
        int[] idx={0};
        TreeNode root=constructTree(pre,postMap,0,pre.length,idx);
        
        LinkedHashMap<Integer,List<Integer>> levelOrder=new LinkedHashMap<>();
        
        preordertrav(root,0,levelOrder);
        List<List<Integer>> answer=new ArrayList<>();
        for(int i:levelOrder.keySet()){
            answer.add(levelOrder.get(i));
        }
        System.out.println(answer);
    }
    public static void preordertrav(TreeNode root,int level,LinkedHashMap<Integer,List<Integer>> lO){
        if(root==null){
            return;
        }
        List<Integer> l=lO.getOrDefault(level,new ArrayList<>());
        l.add(root.data);
        lO.put(level,l);
        preordertrav(root.left,level+1,lO);
        preordertrav(root.right,level+1,lO);
    }
}