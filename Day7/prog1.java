/*
 * Bubloo is working with computer networks, where servers are connected 
in a hierarchical structure, represented as a Binary Tree. Each server (node) 
is uniquely identified by an integer value.

Bubloo has been assigned an important task: find the shortest communication 
path (in terms of network hops) between two specific servers in the network.

Network Structure:
------------------
The network of servers follows a binary tree topology.
Each server (node) has a unique identifier (integer).
If a server does not exist at a certain position, it is represented as '-1' (NULL).

Given the root of the network tree, and two specific server IDs (E1 & E2), 
determine the minimum number of network hops (edges) required to 
communicate between these two servers.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print an integer value.


Sample Input-1:
---------------
1 2 4 3 5 6 7 8 9 10 11 12
4 8

          1
    2             4
 3     5       6    7
8 9 10  11 12 n  n n

Sample Output-1:
----------------
4

Explanation:
------------
The edegs between 4 and 8 are: [4,1], [1,2], [2,3], [3,8]


Sample Input-2:
---------------
1 2 4 3 5 6 7 8 9 10 11 12
6 6

Sample Output-2:
----------------
0

Explanation:
------------
No edegs between 6 and 6.

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
public class prog1
{
    public static void buildGraph(TreeNode root,HashMap<Integer,List<Integer>> graph,HashMap<Integer,Integer> dist,int i){
        if(root==null){
            return;
        }
        List<Integer> l=graph.getOrDefault(root.data,new ArrayList<>());
        if(root.left!=null){
            l.add(root.left.data);
        }
        if(root.right!=null){
            l.add(root.right.data);
        }
        graph.put(root.data,l);
        
        dist.put(root.data,i);
        buildGraph(root.left,graph,dist,i+1);
        buildGraph(root.right,graph,dist,i+1);
    }
    public static boolean isInSameTree(HashMap<Integer,List<Integer>> graph,int curNode,int targetNode){
        if(curNode==targetNode){
            return true;
        }
        boolean res=false;
        for(int val:graph.getOrDefault(curNode,new ArrayList<>())){
            res=res||isInSameTree(graph,val,targetNode);
        }
        return res;
    }
    public static int minDistance(TreeNode root,int n1,int n2){
        HashMap<Integer,List<Integer>> graph=new HashMap<>();
        HashMap<Integer,Integer> dist=new HashMap<>();
        
        buildGraph(root,graph,dist,0);
        // System.out.println("Graph");
        // System.out.println(graph);
        // System.out.println("Distances");
        // System.out.println(dist);
        boolean issame = isInSameTree(graph,n1,n2) || isInSameTree(graph,n2,n1);
        if(issame){
            return Math.abs(dist.get(n1)-dist.get(n2));
        }
        return dist.get(n1)+dist.get(n2);
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
        int n1=sc.nextInt();
        int n2=sc.nextInt();
        System.out.println(minDistance(root,n1,n2));
    }

}