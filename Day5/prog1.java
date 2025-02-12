
/*
 * Given the in-order and post-order traversals of a binary tree, construct 
the original binary tree. For the given Q number of queries, 
each query consists of a lower level and an upper level. 
The output should list the nodes in the order they appear in a level-wise.

Input Format:
-------------
An integer N representing the number nodes.
A space-separated list of N integers representing the similar to in-order traversal.
A space-separated list of N integers representing the similar to post-order traversal.
An integer Q representing the number of queries.
Q pairs of integers, each representing a query in the form:
Lower level (L)
Upper level (U)

Output Format:
For each query, print the nodes in order within the given depth range

Example
Input:
7
4 2 5 1 6 3 7
            p
4 5 2 6 7 3 1
        p
2
1 2
2 3
Output:
[1, 2, 3]
[2, 3, 4, 5, 6, 7]

Explanation:
        1
       / \
      2   3
     / \  / \
    4   5 6  7
Query 1 (Levels 1 to 2): 1 2 3
Query 2 (Levels 2 to 3): 2 3 4 5 6 7


      3
   4     6
    5   8
     7 9
     
     inorder= 4 5 7 3 9 8 6
     post = 7 5 4 9 8 6 3
     
     
 

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

    public static TreeNode constructtree(int[] inorder,int[] postorder,int l,int r,HashMap<Integer,Integer> keys,Stack<Integer> st){
        if(l>r || st.isEmpty()){
            return null;
        }
        int valVal=st.peek();
        int mid=keys.get(valVal);
        int root=inorder[mid];
        st.pop();
        TreeNode parentNode=new TreeNode(root);
        parentNode.right = constructtree(inorder,postorder,mid+1,r,keys,st);
        parentNode.left = constructtree(inorder,postorder,l,mid-1,keys,st);
        return parentNode;
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        HashMap<Integer,Integer> keys=new HashMap<>();
        Stack<Integer> postVals=new Stack<>();
        int n=sc.nextInt();
        
        int[] inorder=new int[n];
        int[] postorder=new int[n];
        
        for(int i=0;i<n;i++){
            inorder[i]=sc.nextInt();
            keys.put(inorder[i],i);
        }
        for(int i=0;i<n;i++){
            postorder[i]=sc.nextInt();
            postVals.add(postorder[i]);
        }
        int count=sc.nextInt();
        List<int[]> required=new ArrayList<>();
        for(int i=0;i<count;i++){
            int low=sc.nextInt();
            int right=sc.nextInt();
            required.add(new int[]{low,right});
        }

        TreeNode answer=constructtree(inorder,postorder,0,n-1,keys,postVals);
        
        HashMap<Integer,List<Integer>> level=new HashMap<>();
        preorder(answer,level,1);
        for(int[] comb:required){
            int left=comb[0];
            int right=comb[1];
            List<Integer> combination=new ArrayList<>();
            for(int i=left;i<=right;i++){
                combination.addAll(level.get(i));
            }
            System.out.println(combination);
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