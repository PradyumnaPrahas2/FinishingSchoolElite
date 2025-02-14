/*
 * A software development company is designing a smart home automation 
system that uses sensor networks to monitor and control different devices 
in a house. The sensors are organized in a hierarchical structure, where each 
sensor node has a unique ID and can have up to two child nodes (left and right).

The company wants to analyze the left-most sensors in the system to determine
which ones are critical for detecting environmental changes. The hierarchy of 
the sensors is provided as a level-order input, where missing sensors are 
represented as -1.

Your task is to build the sensor network as a binary tree and then determine 
the left-most sensor IDs at each level.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
A list of integers representing the left-most sensor IDs at each level


Sample Input-1:
---------------
1 2 3 4 -1 -1 5

Sample Output-1:
----------------
[1, 2, 4]



Sample Input-2:
---------------
3 2 4 1 5

Sample Output-2:
----------------
[3, 2, 1]


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
    public static void postorder(TreeNode root,HashMap<Integer,Integer> lef,int i){
        if(root==null){
            return;
        }
        postorder(root.right,lef,i+1);
        postorder(root.left,lef,i+1);
        lef.put(i,root.data);
    }
    public static void leftView(TreeNode root){
        HashMap<Integer,Integer> lef=new HashMap<>();
        
        postorder(root,lef,0);
        
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i:lef.keySet()){
            ans.add(lef.get(i));
        }
        System.out.print(ans);
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
        
        leftView(root);
    }
}