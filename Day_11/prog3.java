/*
 * Imagine you are designing a network of secret corridors in an ancient castle. 
Each chamber in the castle leads to at most two other chambers through 
hidden passageways, forming a branching layout. 
The castle’s "longest secret route" is defined as the maximum number of corridors 
you must traverse to get from one chamber to another (without repeating the corridor). 
This route may or may not pass through the main entry chamber.

Your task is to compute the length of longest secret route between 
two chambers which is represented by the number of corridors between them.

Example 1
input=
1 2 3 4 5 
output=
3

Structure:
       1
      / \
     2   3
    / \
   4   5

Explanation:
The longest secret route from chamber 4 to chamber 3 
(alternatively, from chamber 5 to chamber 3) along the route:
4 → 2 → 1 → 3
This path has 3 corridors (4–2, 2–1, 1–3), so the length is 3.

Example 2:
input=
1 -1 2 3 4
output=
2

Structure:
   1
    \
     2
    / \
   3   4

Explanation:
The longest secret route from chamber 3 to chamber 4 
(alternatively, from chamber 1 to chamber 4) along the route:
3 → 2 → 4
This path has 2 corridors (3–2, 2–4), so the length is 2.



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
    public static int ans=Integer.MIN_VALUE;
    public static int traversal(TreeNode root){
        if(root==null){
            return 0;
        }
        int l=traversal(root.left);
        int r=traversal(root.right);
        
        ans=Math.max(ans,l+r);
        // System.out.println(l+r+1);
        return Math.max(l,r)+1;
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        String[] arr=sc.nextLine().split(" ");
        
        TreeNode root=new TreeNode(Integer.parseInt(arr[0]));
        
        Queue<TreeNode> q=new LinkedList<>();
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
        traversal(root);
        // ans=Math.max(ans,traversal(root));
        System.out.print(ans);
    }

}