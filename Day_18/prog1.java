/*
Budget Padmanabham planned to visit the tourist places. There are N tourist 
places in the city. The tourist places are numbered from 1 to N.

You are given the routes[] to travel between the tourist places in the city.
where routes[i]=[place-1, place-2, price], A route is a bi-directional route.
You can travel from place-1 to place-2 or place-2 to place-1 with the given price.
 
Your task is to help Budget Padmanabham to find the cheapest route plan, to 
visit all the tourist places in the city. If you are not able to find such plan, 
print -1.
 
Input Format:
-------------
Line-1: Two space separated integers N and R,number of places and routes.
Next R lines: Three space separated integers, start, end and price.
  
Output Format:
--------------
Print an integer, minimum cost to visit all the tourist places.
 
 
Sample Input-1:
---------------
4 5
1 2 3
1 3 5
2 3 4
3 4 1
2 4 5
 
Sample Output-1:
----------------
8
 
Explanation:
------------
The cheapest route plan is as follows:
1-2, 2-3, 3-4 and cost is 3 + 4 + 1 = 8
 
 
Sample Input-2:
---------------
4 3
1 2 3
1 3 5
2 3 4
 
Sample Output-2:
----------------
-1

 */
import java.util.*;
public class prog1{
    public static int find(int s,int[] p){
        if(p[s]==s) return s;
        return p[s]=find(p[s],p);
    }
    public static void union(int a,int b,int[] p,int[] r){
        int pa=find(a,p);
        int pb=find(b,p);
        
        if(pa==pb) return;
        
        if(r[pa]>r[pb]){
            p[pb]=pa;
        }
        else if(r[pa]<r[pb]){
            p[pa]=pb;
        }
        else{
            p[pb]=pa;
            r[pa]++;
        }
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        
        int n=sc.nextInt();
        int r=sc.nextInt();
        
        int[] parent=new int[n+1];
        int[] rank=new int[n+1];
        for(int i=0;i<=n;i++){
            parent[i]=i;
            rank[i]++;
        }
        List<int[]> arr=new ArrayList<>();
        for(int i=0;i<r;i++){
            int src=sc.nextInt();
            int dstn=sc.nextInt();
            int wt=sc.nextInt();
            arr.add(new int[]{src,dstn,wt});
        }
        Collections.sort(arr,(a,b)->a[2]-b[2]);
        int sum=0;
        
        for(int[] ar:arr){
            int src=ar[0];
            int dstn=ar[1];
            int wt=ar[2];
            if(find(src,parent)!=find(dstn,parent)){
                sum+=wt;
                union(src,dstn,parent,rank);
            }
        }
        HashSet<Integer> set=new HashSet<>();
        for(int i=1;i<=n;i++){
            set.add(find(i,parent));
        }
        if(set.size()!=1) sum=-1;
        System.out.print(sum);
    }
}