/*
 * Imagine you're the chief curator at a renowned museum that houses a rare collection 
of ancient artifacts. These artifacts are arranged in a special display that 
follows a strict rule: any artifact positioned to the left of another has a lower 
value, and any artifact on the right has a higher value. 

Your task is to transform this display into what is known as a "Greater Artifact 
Display." In this new arrangement, each artifact’s new value will be its original 
value plus the sum of the values of all artifacts that are more valuable than it.

Example 1:
----------
input=
4 2 6 1 3 5 7
output=
22 27 13 28 25 18 7

Explanation:
Input structure 
       4
      / \
     2   6
    / \ / \
   1  3 5  7

Output structure:
        22
      /   \
     27   13
    / \   / \
   28 25 18  7

Reverse updates:
- Artifact 7: 7
- Artifact 6: 6 + 7 = 13
- Artifact 5: 5 + 13 = 18
- Artifact 4: 4 + 18 = 22
- Artifact 3: 3 + 22 = 25
- Artifact 2: 2 + 25 = 27
- Artifact 1: 1 + 27 = 28

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
    public static int variable=0;
    public static void modifytree(TreeNode root,TreeNode parent){
        if(root==null) return ;
        
        modifytree(root.right,root);
        
        variable+=root.data;
        
        root.data=variable;
        
        modifytree(root.left,root);
        
        
        
    }
    public static void pre(TreeNode root,int i,Map<Integer,List<Integer>> lo){
        if(root==null) return ;
        
        lo.putIfAbsent(i,new ArrayList<>());
        
        lo.get(i).add(root.data);
        
        pre(root.left,i+1,lo);
        pre(root.right,i+1,lo);
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        String[] arr=sc.nextLine().split(" ");
        
        Queue<TreeNode> q = new LinkedList<>();
        
        TreeNode root=new TreeNode(Integer.parseInt(arr[0]));
        
        q.add(root);
        int idx=0;
        while(!q.isEmpty()){
            TreeNode p=q.poll();
            
            if(idx+1<arr.length && Integer.parseInt(arr[idx+1])!=-1){
                p.left=new TreeNode(Integer.parseInt(arr[idx+1]));
                q.add(p.left);
            }
            idx++;
            if(idx+1<arr.length && Integer.parseInt(arr[idx+1])!=-1){
                p.right=new TreeNode(Integer.parseInt(arr[idx+1]));
                q.add(p.right);
            }
            idx++;
        }
        modifytree(root,null);
        
        Map<Integer,List<Integer>> lo=new HashMap<>();
        
        pre(root,0,lo);
        
        List<Integer> ans=new ArrayList<>();
        for(int k:lo.keySet()){
            ans.addAll(lo.get(k));
        }
        System.out.println(ans);
    }
}