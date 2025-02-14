
/*
 * Mr. Rakesh is interested in working with Data Structures.

He has constructed a Binary Tree (BT) and asked his friend 
Anil to check whether the BT is a self-mirror tree or not.

Can you help Rakesh determine whether the given BT is a self-mirror tree?
Return true if it is a self-mirror tree; otherwise, return false.

Note:
------
In the tree, '-1' indicates an empty (null) node.

Input Format:
-------------
A single line of space separated integers, values at the treenode

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
2 1 1 2 3 3 2

Sample Output-1:
----------------
true


Sample Input-2:
---------------
2 1 1 -1 3 -1 3

Sample Output-2:
----------------
false

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

public class prog3 {

    public static boolean isSame(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return true;
        if (t1 == null && t2 != null)
            return false;
        if (t1 != null && t2 == null)
            return false;
        if (t1.data != t2.data)
            return false;

        return isSame(t1.left, t2.right) && isSame(t1.right, t2.left);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] a = sc.nextLine().split(" ");

        int n = a.length;

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(a[i]);
        }

        TreeNode root = new TreeNode(arr[0]);
        int idx = 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode p = q.poll();
            if (idx + 1 < n && arr[idx + 1] != -1) {
                p.left = new TreeNode(arr[idx + 1]);
                q.add(p.left);
            }
            idx++;

            if (idx + 1 < n && arr[idx + 1] != -1) {
                p.right = new TreeNode(arr[idx + 1]);
                q.add(p.right);
            }
            idx++;
        }
        System.out.println(isSame(root.left, root.right));
    }
}