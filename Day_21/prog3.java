/*
 * Imagine you're the curator of an ancient manuscript archive. Each manuscript is
assigned a unique significance score, and the archive is arranged so that every 
manuscript on the left has a lower score and every manuscript on the right has a
higher score, forming a special ordered display. Now, for an upcoming exhibition,
scholars have decided that only manuscripts with significance scores between low 
and high (inclusive) are relevant. Your task is to update the archive by removing
any manuscripts whose scores fall outside the range [low, high]. Importantly, 
while you remove some manuscripts, you must preserve the relative order of the 
remaining ones—if a manuscript was originally displayed as a descendant of another, 
that relationship should remain intact. It can be proven that there is a unique 
way to update the archive.

Display the manuscript of the updated archive. Note that the main manuscript 
(the root) may change depending on the given score boundaries.

Input format:
Line 1: space separated scores to build the manuscript archive
Line 2: two space seperated integers, low and high.

Output format:
space separated scores of the updated archive

Example 1:
input=
1 0 2
1 2
output=
1 2

Explanation:
Initial archieve:
      1
     / \
    0   2


Updated archieve:
    1
     \
      2
After removing manuscripts scores below 1 (i.e. 0), only the manuscript with 1 
and its right manuscript 2 remain.

Example 2:
input=
3 0 4 2 1
1 3
output=
3 2 1

Explanation:
Initial archieve:
          3
         / \
        0   4
         \
          2
         /
        1

Updated archieve:
      3
     /
    2
   /
  1

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
public class prog3{
    public static void addNode(TreeNode root,int newval){
        if(root==null) return;
        
        if(root.data<newval){
            if(root.right==null){
                root.right=new TreeNode(newval);
                return;
            }
            addNode(root.right,newval);
        }
        else{
            if(root.left==null){
                root.left=new TreeNode(newval);
                return;
            }
            addNode(root.left,newval);
        }
    }
    public static void modifytree(TreeNode root,TreeNode parent,int low,int high,char dir){
        
        if(root==null) return;
        
        
        modifytree(root.left,root,low,high,'l');
        modifytree(root.right,root,low,high,'r');
        
        if(root.data<low){
            if(parent!=null){
                if(dir=='l'){
                    parent.left=root.right;
                }
                else{
                    parent.right=root.right;
                }
            }
        }
        else if(root.data>high){
            if(parent!=null){
                if(dir=='l'){
                    parent.left=root.left;
                }
                else{
                    parent.right=root.left;
                }
            }
        }
    }
    public static void pre(TreeNode r,int i,HashMap<Integer,List<Integer>> level){
        if(r==null) return;
        level.putIfAbsent(i,new ArrayList<>());
        level.get(i).add(r.data);
        pre(r.left,i+1,level);
        pre(r.right,i+1,level);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        
        String[] arr=sc.nextLine().split(" ");
        
        int low=sc.nextInt();
        int high=sc.nextInt();
        
        TreeNode root=new TreeNode(Integer.parseInt(arr[0]));
        
        for(int i=1;i<arr.length;i++){
            
            addNode(root,Integer.parseInt(arr[i]));
        }
        
        while(root!=null){
            boolean cond1=root.val<low;
            boolean cond2=root.val>high;
            if(cond1){
                root=root.right;
            }
            else if(cond2){
                root=root.left;
            }
            else{
                break;
            }
        }

        modifytree(root,null,low,high,'n');
        
        HashMap<Integer,List<Integer>> level=new HashMap<>();
        pre(root,0,level);
        
        for(int k:level.keySet()){
            for(int val:level.get(k)){
                System.out.print(val+" ");
            }
        }
    }
}