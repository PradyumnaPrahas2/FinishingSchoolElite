/*
 * You are a bird living in a vast forest. Every day, you fly across different locations 
to collect food and store it in various nests. However, you must return to your home 
nest before sunset to rest safely.  

Your objective is to collect as much food as possible within a given time limit 
while following the forest rules:
1. Each food location contains only one unit of food.  
2. The bird can carry only one unit of food at a time.  
3. The bird must deposit food into a nest before collecting more.  
4. Distance Calculation: The time taken to fly between two locations using 
the Euclidean distance formula:  d = sqrt{(x_2 - x_1)^2 + (y_2 - y_1)^2}
5. The total time spent is the sum of:  
   - Travel time between locations.  
   - Fixed time to deposit food in a nest (each nest has a different deposit 
   time).  
6. The bird must return to the home nest before the total time limit.  

Your goal is to determine the maximum number of food units that the bird
can collect and store in different nests before sunset.  

Input Format:
-------------
An integer representing the number of food locations.  
An integer representing the number of nests.  
A 2D array containing the coordinates of each food location as pairs (x, y) 
A 2D array containing the coordinates of each nest as pairs (x, y).  
A 1D array containing the starting coordinates (home nest) (x, y).  
A floating-point number representing the total time available (before sunset).  

Output Format:
---------------  
The function must return an integer, representing the maximum number of 
food units that can be collected and stored in nests within the given time.  

Sample Input:
--------------
2
2
3 3
4 6
5 5
6 1
1 4
13

Sample Output:
---------------
2

Explanation:
---------------
The bird starts at (1,4).
Moves to food location (3,3) (distance = sqrt(5)).
Deposits food at a nest (5,5) (distance = sqrt(8)).
Moves to food location (4,6) (distance = sqrt(2))
and collects another unit of food.
Deposits it at a nest (5,5) (distance = sqrt(2)). 
Returns to the starting point (1,4) (distance = sqrt(17)).
Total distance is: sqrt(5) + sqrt(8) + sqrt(2) + sqrt(2)+ sqrt(17) = 12.0160278526
Since the total distance is within the allowed time (13), 
the maximum food units collected are 2.


Sample Input:
--------------
4
1
3 3
5 7
7 8
8 4
7 7
1 5
22

Sample Output:
---------------
3
 */
import java.util.*;
public class prog2{
    public static int ans=0;
    public static void backtrack(List<int[]> l1,List<int[]> l2,int curX,int curY,int deposit_count,boolean take,boolean deposit,double dist,boolean[] visited,double limit,boolean end,int startX,int startY){
        if(dist>limit){
            return;
        }
        if(end==true){
            ans=Math.max(ans,deposit_count);
            return;
        }
        if(deposit){
            for(int i=0;i<l1.size();i++){
                int[] newcor=l1.get(i);
                if(visited[i]==false){
                    int newX=newcor[0];
                    int newY=newcor[1];
                    
                    double nextDist=Math.pow(Math.pow(curX-newX,2)+Math.pow(curY-newY,2),0.5);
                    visited[i]=true;
                    backtrack(l1,l2,newX,newY,deposit_count,true,false,dist+nextDist,visited,limit,end,startX,startY);
                    visited[i]=false;
                }
            }
        }
        else{
            for(int i=0;i<l2.size();i++){
                int[] newcor=l2.get(i);
                int newX=newcor[0];
                int newY=newcor[1];
                
                double nextDist=Math.pow(Math.pow(curX-newX,2)+Math.pow(curY-newY,2),0.5);
                
                backtrack(l1,l2,newX,newY,deposit_count+1,false,true,dist+nextDist,visited,limit,false,startX,startY);
                
                double newNew=Math.pow(Math.pow(newX-startX,2)+Math.pow(newY-startY,2),0.5);
                backtrack(l1,l2,newX,newY,deposit_count+1,false,true,dist+nextDist+newNew,visited,limit,true,startX,startY);
            }
        }
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        int n1=sc.nextInt();
        int n2=sc.nextInt();
        
        List<int[]> l1=new ArrayList<>();
        for(int i=0;i<n1;i++){
            int x=sc.nextInt();
            int y=sc.nextInt();
            l1.add(new int[]{x,y});
        }
        List<int[]> l2=new ArrayList<>();
        
        for(int i=0;i<n2;i++){
            int x=sc.nextInt();
            int y=sc.nextInt();
            l2.add(new int[]{x,y});
        }
        int x=sc.nextInt();
        int y=sc.nextInt();
        double limit=sc.nextDouble();
        
        boolean[] visited=new boolean[n1];
        
        backtrack(l1,l2,x,y,0,false,true,0,visited,limit,false,x,y);
        
        System.out.print(ans);
    }
}