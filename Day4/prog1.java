/*
 * You are an architect designing a street with houses represented as a 0-indexed array 
house_heights of n integers, where each element represents the height of a house. 
Additionally, a binary array visibility_mask of length n is provided, where 1 indicates 
a house that contributes to the neighborhood's skyline, and 0 indicates a house that does not.

For any house located at index i, you are tasked with determining the average 
skyline height within a k-radius neighborhood centered at i. The average skyline 
height is the average of all house heights between indices i - k and i + k (inclusive) 
that have a corresponding visibility value of 1 in the visibility_mask. 

If no houses with a visibility of 1 exist in the range, or if the range extends 
beyond the boundaries of the array, the average skyline height for that house is -1.

Return an array skyline_avgs of length n, where skyline_avgs[i] is the average 
skyline height for the neighborhood centered at index i.

Example 1:
Input:
house_heights = [10, 15, 20, 5, 30, 25, 40]
visibility_mask = [1, 0, 1,   1, 0, 1,  1]
                  [0, 1, 1, 2, 3, 3, 4] 
                  [ 4,4, 3,  2, 2, 1, 0]
k = 2

Output:
skyline_avgs = [-1, -1, 11, 16, 22, -1, -1]


Explanation:
- For index 0, there are less than k houses in the left neighborhood, 
  so skyline_avgs[0] = -1.
- For index 1, there are less than k houses in the left neighborhood, 
  so skyline_avgs[1] = -1.
- For index 2, the neighborhood is [10, 15, 20, 5, 30]. From the visibility_mask, 
  only the houses with heights [10, 20, 5] contribute to the skyline. The average 
  is (10 + 20 + 5) / 3 = 11.
- For index 3, the neighborhood is [15, 207, 5, 30, 25]. From the visibility_mask, 
  only the houses with heights [20, 5, 25] contribute. 
  The average is (20 + 5 + 25) / 3 = 16.
- For index 4, the neighborhood is [20, 5, 30, 25, 40]. From the visibility_mask, 
  only the houses with heights [20, 5, 25, 40] contribute. The average 
  is (20 + 5 + 25 + 40) / 4 = 22.
- For index 5, there are less than k houses in the right neighborhood, 
  so skyline_avgs[5] = -1.
- For index 6, there are less than k houses in the right neighborhood, 
  so skyline_avgs[6] = -1.

Sample Input:
3
50 60 70
1 1 1
1

Sample Output:
[-1, 60, -1]

Constraints:
1. n == house_heights.length == visibility_mask.length
2. 1 <= n <= 10^5
3. 0 <= house_heights[i] <= 10^5
4. visibility_mask[i] is either 0 or 1
5. 0 <= k <= n

 */
import java.util.*;
public class prog1{
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        int n=sc.nextInt();
        int k=sc.nextInt();
        
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int[] vis=new int[n];
        for(int i=0;i<n;i++){
            vis[i]=sc.nextInt();
        }
    
        int[] prevsum=new int[n];
        int[] aftersum=new int[n];
        int[] ans=new int[n];
        
        int[] cl=new int[n];
        int[] cr=new int[n];

        for(int i=1;i<n;i++){
            if(vis[i-1]==1){
                cl[i]++;
            }
            cl[i]+=cl[i-1];
        }
        
        for(int i=n-2;i>=0;i--){
            if(vis[i+1]==1){
                cr[i]++;
            }
            cr[i]+=cr[i+1];
        }
        
        int ps=0;
        for(int i=0;i<k;i++){
            prevsum[i]=-1;
            if(vis[i]!=0){
                ps+=arr[i];
            }
        }
        
        for(int i=k;i<n;i++){
            prevsum[i]=ps;
            if(vis[i-k]!=0){
                ps-=arr[i-k];
            }
            if(vis[i]==1){
                ps+=arr[i];
            }
        }
        ps=0;
        for(int i=n-1;i>n-1-k;i--){
            aftersum[i]=-1;
            if(vis[i]!=0){
                ps+=arr[i];
            }
        }
        
        for(int i=n-1-k;i>=0;i--){
            aftersum[i]=ps;
            if(vis[i+k]!=0){
                ps-=arr[i+k];
            }
            if(vis[i]==1){
                ps+=arr[i];
            }
        }
        
        for(int i=0;i<n;i++){
            if(prevsum[i]==-1 || aftersum[i]==-1 || i-k<0 || i+k>=n ){
                ans[i]=-1;
            }
            else{
                int total= cl[i]-cl[i-k]+cr[i]-cr[i+k];
                if(vis[i]==1){
                    // System.out.println(" left "+prevsum[i]+" , right "+aftersum[i]+" cur ele "+arr[i]+" total numbers "+(1+total)+" denom "+(prevsum[i]+aftersum[i]+arr[i])/total+1);
                    ans[i]=(prevsum[i]+aftersum[i]+arr[i])/(total+1);
                }
                else{
                    // System.out.println(" left "+prevsum[i]+" , right "+aftersum[i]+" total numbers "+total+" denom "+(prevsum[i]+aftersum[i])/total);
                    if(total==0){
                        ans[i]=-1;
                    }
                    else{
                        ans[i]=(prevsum[i]+aftersum[i])/total;
                    }
                }
            }
        }
        // System.out.println(Arrays.toString(prevsum));
        // System.out.println(Arrays.toString(cl));
        // System.out.println(Arrays.toString(aftersum));
        
        // System.out.println(Arrays.toString(cr));
        System.out.println(Arrays.toString(ans));
    }
}