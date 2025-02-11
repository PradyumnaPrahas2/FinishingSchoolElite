/*
 * You are given an integer array nums and two integers l and r. Your task is to
 * analyze the volatility of a sequence of values. The volatility of a sequence
 * is
 * defined as the difference between the maximum and minimum values in that
 * sequence.
 * 
 * You need to determine the sequence with the highest volatility among all
 * sequences of length between l and r (inclusive).
 * 
 * Return the highest volatility. If no such sequence exists, return -1.
 * 
 * Input Format:
 * -------------
 * Line-1: 3 space separated integers, n, l, r
 * Line-2: n space separated integers, nums[].
 * 
 * Output Format:
 * -------------
 * An integer, the highest volatility.
 * 
 * 
 * Sample Input-1:
 * ---------------
 * 5 2 3
 * 8 3 1 6 2
 * 
 * Sample Output-1:
 * ----------------
 * 7
 * 
 * Explanation:
 * ------------
 * The possible sequences of length between l = 2 and r = 3 are:
 * 
 * [8, 3] with a volatility of 8−3=5
 * [3, 1] with a volatility of 3−1=2
 * [1, 6] with a volatility of 6−1=5
 * [8, 3, 1] with a volatility of 8−1=7
 * The sequence [8, 3, 1] has the highest volatility of 7.
 * 
 * Sample Input-2:
 * ---------------
 * 4 2 4
 * 5 5 5 5
 * 
 * Sample Output-2:
 * ----------------
 * 0
 * 
 * Explanation:
 * ------------
 * All possible sequences have no volatility as the maximum and minimum values
 * are the same, resulting in a difference of 0.
 */
import java.util.*;
public class prog3{
    public static int ans=-1;
    public static void check(int[] arr,int k){
        TreeMap<Integer,Integer> tree=new TreeMap<>();
        for(int i=0;i<k;i++){
            int f=tree.getOrDefault(arr[i],0);
            tree.put(arr[i],f+1);
        }
        ans=Math.max(ans,tree.lastKey()-tree.firstKey());
        for(int i=k;i<arr.length;i++){
            int ele=arr[i-k];
            int f=tree.getOrDefault(ele,0);
            if(f==1){
                tree.remove(ele);
            }
            else{
                tree.put(ele,f-1);
            }
            tree.put(arr[i],tree.getOrDefault(arr[i],0)+1);
            ans=Math.max(ans,tree.lastKey()-tree.firstKey());
        }
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();
        int l=sc.nextInt();
        int r=sc.nextInt();

        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        check(arr,r);
        
        System.out.print(ans);
    }
}
