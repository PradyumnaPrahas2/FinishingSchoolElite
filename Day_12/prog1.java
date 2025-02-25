/*
 * Imagine you’re decoding a secret message that outlines the hierarchical structure 
of a covert spy network. The message is a string composed of numbers and parentheses. 
Here’s how the code works:

- Th40lways starts with an agent’s identification number, this is the 
  leader of the network.
- After the leader’s ID, there can be zero, one, or two segments enclosed in 
  parentheses. Each segment represents the complete structure of one subordinate 
  network.
- If two subordinate networks are present, the one enclosed in the first (leftmost) 
  pair of parentheses represents the left branch, and the second (rightmost) 
  represents the right branch.

Your mission is to reconstruct the entire spy network hierarchy based on this 
coded message.

Example 1:
Input: 4(2(3)(1))(6(5))

Output: [4, 2, 6, 3, 1, 5]

Spy network:
        4
       / \
      2   6
     / \  /
    3   1 5

Explanation:
Agent 4 is the leader.
Agent 2 (with its own subordinates 3 and 1) is the left branch.
Agent 6 (with subordinate 5) is the right branch.

Example 2:
Input: 4(2(3)(1))(6(5)(7))
Output: [4, 2, 6, 3, 1, 5, 7]

Spy network:
         4
       /   \
      2     6
     / \   / \
    3   1 5   7

Explanation:
Agent 4 leads the network.
Agent 2 with subordinates 3 and 1 forms the left branch.
Agent 6 with subordinates 5 and 7 forms the right branch.

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
    public static int findMid(String s,int l,int r){
        Stack<Character> st=new Stack<>();
        st.add('(');
        for(int i=l+2;i<=r;i++){
            if(st.isEmpty()){
                return i;
            }
            if(s.charAt(i)=='('){
                st.add('(');
            }
            else if(s.charAt(i)==')'){
                st.pop();
            }
        }
        return -1;
    }
    public static int counter=0;
    public static int getValIdx(String s,int l){
        for(int i=l;i<s.length();i++){
            if(s.charAt(i)=='-' ||(s.charAt(i)>='0' && s.charAt(i)<='9')){
                continue;
            }
            return i;
        }
        return s.length();
    }
    public static TreeNode buildtree(String s,int l,int r){
        // TreeNode root=new TreeNode(s.charAt(l)-'0');
        if(l>=r) return null;
        
        int left_idx=l;
        int right_idx=getValIdx(s,l);
        TreeNode root=new TreeNode(Integer.parseInt(s.substring(left_idx,right_idx)));
        // System.out.println("cur_idx , "+l);
        
        int mid=findMid(s,right_idx,r);
        if(mid==-1){
            return root;
        }
        // System.out.println("left_tree , "+(l+2)+" , "+(mid-1));
        // System.out.println("right_subtree , "+(mid+2)+" "+r);
        
        root.left=buildtree(s,right_idx+1,mid-1);
        root.right=buildtree(s,mid+1,r-1);
        return root;
    }
    public static void pre(TreeNode root,HashMap<Integer,List<Integer>> level,int i){
        if(root==null) return;
        List<Integer> a=level.getOrDefault(i,new ArrayList<>());
        a.add(root.data);
        level.put(i,a);
        pre(root.left,level,i+1);
        pre(root.right,level,i+1);
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        String s=sc.nextLine();
        
        TreeNode root=buildtree(s,0,s.length());
        
        HashMap<Integer,List<Integer>> level=new HashMap<>();
        
        pre(root,level,0);
        List<Integer> ans=new ArrayList<>();
        for(int k:level.keySet()){
            ans.addAll(level.get(k));
        }
        System.out.print(ans);
    }
}