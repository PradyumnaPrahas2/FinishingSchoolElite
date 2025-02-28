/*
 * Two brothers want to play a game, 
The rules of the game are: one player gives two sorted lists of 
numerical elements and a number (sum). 
The opponent has to find the closest pair of elements 
to the given sum.
-> pair consists of elements from each list

Please help those brothers to develop a program, that takes 
two sorted lists as input and return a pair as output.

Input Format:
-------------
size of list_1
list_1 values
size of list_2
list_2 values
closest number

Output Format:
--------------
comma-separated pair

Sample Input-1:
---------------
4
1 4 5 7
4
10 20 30 40
32
Sample Output-1
---------------
1, 30

Sample Input-2
---------------
3
2 4 6
4
5 7 11 13
15
sample output-2
---------------
2, 13

 */
import java.util.*;
public class prog4{
    public static String closestpair(int[] arr1,int[] arr2,int n1,int n2,int target){
        int l=0,r=n2-1;
        int first=-1,second=-1;
        int prev=Integer.MAX_VALUE;
        while(l>=0 && l<n1 && r>=0 && r<n2){
            int cursum = arr1[l]+arr2[r];
            int diff = Math.abs(target-cursum);
            if(diff<prev){
                prev=diff;
                first=arr1[l];
                second=arr2[r];
            }
            if(cursum>target){
                r--;
            }
            else if(cursum<target){
                l++;
            }
            else{
                return arr1[l]+", "+arr2[r];
            }
        }
        
        return first+", "+second;
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n1=sc.nextInt();
        int[] arr1=new int[n1];
        for(int i=0;i<n1;i++) arr1[i]=sc.nextInt();
        int n2=sc.nextInt();
        int[] arr2=new int[n2];
        for(int i=0;i<n2;i++) arr2[i]=sc.nextInt();
        int target=sc.nextInt();
        System.out.print(closestpair(arr1,arr2,n1,n2,target));
    }
}