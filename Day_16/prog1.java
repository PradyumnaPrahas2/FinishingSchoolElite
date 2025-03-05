/*
 * There are N computers in a network, all the computers are connected as tree 
structure. And one new connection is added in the Network. The computers in 
the network are identified with their IDs, the IDs are numbered between 1 to N.

The connections in the network is given as coonection[i] = [comp-A, comp-B], 
there is a connection between comp-A and comp-B.

Your task is to remove a connection in the network and print it, so that 
all the computers are connected as tree structure. If there are multiple 
options to remove, remove the connection that occurs last in the input.


Input Format:
-------------
Line-1: Two space separated integers N, number of computers.
Next N lines: Two space separated integers, comp-A & comp-B.

Output Format:
--------------
Print the connection which is removed.


Sample Input-1:
---------------
6
1 2
3 4
3 6
4 5
5 6
2 3

Sample Output-1:
---------------
5 6


Sample Input-2:
---------------
4
1 2
2 3
3 4
2 4

Sample Output-2:
---------------
2 4




 */
import java.util.*;
public class prog1{
    public static int find(int s,int[] p){
        if(p[s]==s) return s;
        return p[s]=find(p[s],p);
    }
    public static void union(int a,int b,int[] parent,int[] rank){
        int pa=find(a,parent);
        int pb=find(b,parent);
        
        if(pa==pb) return;
        
        if(rank[pa]>rank[pb]){
            parent[pb]=pa;
        }
        else if(rank[pa]<rank[pb]){
            parent[pa]=pb;
        }
        else{
            parent[pb]=pa;
            rank[pa]++;
        }
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n=sc.nextInt();
        
        int[] parent = new int[n+1];
        int[] rank = new int[n+1];
        
        for(int i=0;i<n+1;i++){
            parent[i]=i;
            rank[i]++;
        }
        
        int[] ans=new int[2];
        
        for(int i=0;i<n;i++){
            int src=sc.nextInt();
            int dstn=sc.nextInt();
            
            if(find(src,parent)==find(dstn,parent)){
                ans[0]=src;
                ans[1]=dstn;
            }
            
            union(src,dstn,parent,rank);
        }
        
        System.out.print(ans[0]+" "+ans[1]);
    }
}