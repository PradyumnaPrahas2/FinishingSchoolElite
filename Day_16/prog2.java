/*
 * There are N people in a private party. Initially all are strangers to each other,
and the people are identified by unique ID from 0 to N-1.

In the party, whenever two persons (person-A and person-B) become friends, they 
took a photo. Each of the photo has some information, photos[i]=[T-i, P-j,P-k],
here T-i indicates time of the photo taken, P-j person with ID 'j', and 
P-k indicates person with ID 'k'.

Friendship is symmetric[i.e., If P-j is friend of P-k, then P-k is a friend of P-j].
Additionally, if person-A is "a friend of person-B OR a friend of someone who is 
friend of person-B", then person-A is friend of person-B.

You are given L photos information, Your task is to find the earliest time 
for which every person became friend with every other person in the party.
If there is no such earliest time, return -1.


Input Format:
-------------
Line-1: Two space separated integers, N and L.
Next L lines: Three space separated integers, log[i], 0<=i<L.

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
6 8
5 0 1
7 3 4
12 2 3
21 1 5
34 2 4
37 0 3
42 1 2
93 4 5

Sample Output-1:
----------------
37


Sample Input-2:
---------------
7 6
2 0 3
5 1 5
8 2 5
7 3 6
9 4 6
6 4 5

Sample Output-2:
----------------
9

 */
import java.util.*;
public class prog2{
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
    public static boolean isAllSame(int[] p){
        HashSet<Integer> set=new HashSet<>();
        for(int i=0;i<p.length;i++){
            set.add(find(i,p));
        }
        return set.size()==1;
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        int n = sc.nextInt();
        int l = sc.nextInt();
        
        int[] parent = new int[n];
        int[] rank = new int[n];
        
        for(int i=0;i<n;i++){
            parent[i]=i;
            rank[i]=1;
        }
        int ans=Integer.MAX_VALUE;
        int[][] arr = new int[l][3];
        
        for(int i=0;i<l;i++){
            int t=sc.nextInt();
            int s=sc.nextInt();
            int d=sc.nextInt();
            
            arr[i][0]=t;
            arr[i][1]=s;
            arr[i][2]=d;
        }
        Arrays.sort(arr,(a,b)->a[0]-b[0]);
        for(int i=0;i<l;i++){
            int t=arr[i][0];
            int s=arr[i][1];
            int d=arr[i][2];
            
            union(s,d,parent,rank);
            if(isAllSame(parent)){
                ans=Math.min(ans,t);
            }
        }
        if(ans==Integer.MAX_VALUE){
            ans=-1;
        }
        System.out.println(ans);
    }
}