/*
 * Imagine you are a librarian organizing books on vertical shelves in a grand 
library. The books are currently scattered across a tree-like structure, where 
each book (node) has a position determined by its shelf number (column) and row 
number (level).

Your task is to arrange the books on shelves so that:
1. Books are placed column by column from left to right.
2. Within the same column, books are arranged from top to bottom (i.e., by row).
3. If multiple books belong to the same shelf and row, they should be arranged 
from left to right, just as they appear in the original scattered arrangement.

Sample Input:
-------------
3 9 20 -1 -1 15 7

Sample Output:
--------------
[[9],[3,15],[20],[7]]

Explanation:
------------
         3
       /   \
      9     20
          /    \
         15     7

Shelf 1: [9]
Shelf 2: [3, 15]
Shelf 3: [20]
Shelf 4: [7]


Sample Input-2:
---------------
3 9 8 4 0 1 7

Sample Output-2:
----------------
[[4],[9],[3,0,1],[8],[7]]

Explanation:
------------

          3
       /     \
      9       8
    /   \   /   \
   4     0 1     7

Shelf 1: [4]
Shelf 2: [9]
Shelf 3: [3, 0, 1]
Shelf 4: [8]
Shelf 5: [7]

 */
import java.util.*;
class TreeNode
{
    int val;
    TreeNode left,right;
    public TreeNode(int v){
        this.val=v;
        this.left=null;
        this.right=null;
    }
}
public class prog3{
    
    public static void pre(TreeNode root,int x,int y,TreeMap<Integer,ArrayList<int[]>> vertical){
        if(root==null) return;
        
        ArrayList<int[]> a=vertical.getOrDefault(y,new ArrayList<>());
        a.add(new int[]{root.val,x,y});
        vertical.put(y,a);
        
        pre(root.left,x+1,y-1,vertical);
        pre(root.right,x+1,y+1,vertical);
    }
    
    public static void verticaltraversal(TreeNode tree){
        
        Comparator<int[]> comp=new Comparator<>(){
            @Override
            public int compare(int[] a1,int[] a2){
                return a1[1]-a2[1];
            }
        };
        TreeMap<Integer,ArrayList<int[]>> vertical = new TreeMap<>();
        
        pre(tree,0,0,vertical);
        ArrayList<ArrayList<Integer>> ver=new ArrayList<>();
        for(int k:vertical.keySet()){
            ArrayList<int[]> arr=vertical.get(k);
            Collections.sort(arr,comp);
            ArrayList<Integer> ans=new ArrayList<>();
            for(int[] c:arr){
                ans.add(c[0]);
            }
            ver.add(ans);
        }
        System.out.print(ver);
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        String[] s=sc.nextLine().split(" ");
        
        int[] arr=new int[s.length];
        for(int i=0;i<arr.length;i++){
            arr[i]=Integer.parseInt(s[i]);
        }
        
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root=new TreeNode(arr[0]);
        int idx=0;
        q.add(root);
        
        while(!q.isEmpty()){
            TreeNode par=q.poll();
            
            if(idx+1<arr.length && arr[idx+1]!=-1){
                par.left=new TreeNode(arr[idx+1]);
                q.add(par.left);
            }
            idx++;
            if(idx+1<arr.length && arr[idx+1]!=-1){
                par.right=new TreeNode(arr[idx+1]);
                q.add(par.right);
            }
            idx++;
        }
        
        verticaltraversal(root);
    }
}