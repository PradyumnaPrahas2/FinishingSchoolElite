
/*
 * Construct Tree from the given Level-order and In-order.
Compute the Depth and Sum of the Deepest nodes in the Binary tree

Input Format:
-------------
An integer N representing the number of nodes.
A space-separated list of N integers representing the in-order traversal.
A space-separated list of N integers representing the level-order traversal.

Output Format:
--------------
Print two values:
->The maximum number of levels.
->The sum of all node values at the deepest level.

Example:
-------------
Input:
11
7 8 4 2 5 9 11 10 1 6 3
1 2 3 4 5 6 7 9 8 10 11

Output:
6 11

Explanation:
The binary tree structure:
           1
         /   \
       2       3
      / \     /
     4   5   6
    /     \   
   7       9
    \       \
     8      10
            /
          11
Maximum Depth: 6
nodes at the Deepest Level (6): 11
Sum of nodes at Level 6: 11

 */
import java.util.*;

class TreeNode {
    int data;
    TreeNode left, right;

    public TreeNode(int v) {
        this.data = v;
        this.left = null;
        this.right = null;
    }
}

public class prog4 {
    public static PriorityQueue<Integer> partition(int[] inorder, int l, int r, HashMap<Integer, Integer> levelMap) {

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int idx1 = levelMap.get(a);
            int idx2 = levelMap.get(b);
            return idx1 - idx2;
        });

        for (int i = l; i <= r; i++) {
            pq.add(inorder[i]);
        }
        return pq;
    }

    public static TreeNode constructtree(int[] inorder, HashMap<Integer, Integer> inorderMap,
            HashMap<Integer, Integer> levelMap, int curEle, int l, int r) {
        TreeNode root = new TreeNode(curEle);
        int curId = inorderMap.get(curEle);

        PriorityQueue<Integer> leftSubtree = partition(inorder, l, curId - 1, levelMap);
        PriorityQueue<Integer> rightSubtree = partition(inorder, curId + 1, r, levelMap);

        // System.out.println("Left partition of " + curEle + " is " + leftSubtree);
        // System.out.println("Right partition of " + curEle + " is " + rightSubtree);

        if (leftSubtree.isEmpty()) {
            root.left = null;
        } else {
            int nextEle = leftSubtree.poll();
            root.left = constructtree(inorder, inorderMap, levelMap, nextEle, l, curId-1);
        }
        if (rightSubtree.isEmpty()) {
            root.right = null;
        } else {
            int nextEle = rightSubtree.poll();
            root.right = constructtree(inorder, inorderMap, levelMap, nextEle, curId+1, r);
        }

        return root;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] inorder = new int[n];
        int[] levelorder = new int[n];

        HashMap<Integer, Integer> inorderMap = new HashMap<>();
        HashMap<Integer, Integer> levelMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            inorder[i] = sc.nextInt();
            inorderMap.put(inorder[i], i);
        }

        for (int i = 0; i < n; i++) {
            levelorder[i] = sc.nextInt();
            levelMap.put(levelorder[i], i);
        }

        TreeNode root = constructtree(inorder, inorderMap, levelMap, levelorder[0], 0, n - 1);

        int[] ans=new int[2];

        ans[0]=-1;
        ans[1]=0;
        pre(root,ans,1);
        System.out.print(ans[0]+" "+ans[1]);
    }

    public static void pre(TreeNode root,int[] ans,int level) {
        if(root==null) return;
        if(level>ans[0]){
            ans[0]=level;
            ans[1]=root.data;
        }
        else if(level==ans[0]){
            ans[1]+=root.data;
        }
        pre(root.left,ans,level+1);
        pre(root.right,ans,level+1);
    }
}