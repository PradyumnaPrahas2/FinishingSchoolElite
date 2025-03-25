import java.util.*;

public class Fenwick {

    public static void update(int[] tree, int index, int value) {
        while (index < tree.length) {
            tree[index] += value;
            index += index & -index;
        }
    }

    public static int query(int[] tree, int index) {
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= index & -index;
        }
        return sum;
    }

    public static int rangesum(int[] tree, int left, int right) {
        return query(tree, right) - query(tree, left - 1);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] arr = new int[n];
        int[] tree = new int[n + 1];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            update(tree, i, arr[i - 1]);
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int op = sc.nextInt();
            int one = sc.nextInt();
            int two = sc.nextInt();

            if (op == 1) {
                ans.add(rangesum(tree, one + 1, two + 1));
            } else {
                update(tree, one + 1, two);
            }
        }
        for (int num : ans) {
            System.out.println(num);
        }
    }
}
