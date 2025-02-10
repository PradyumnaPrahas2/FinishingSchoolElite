package Day2;
/*
 * You are a treasure hunter exploring an ancient vault filled with 
treasure boxes. The vault is represented as an array treasures of 
n integers, where each integer corresponds to the value of a treasure. 
You have a special key that allows you to scan and select treasures 
from a sub-vault (a segment of the array) of size k. Additionally, 
you have a magical power factor f and a priority filter x.

The priority-weighted treasure sum of a sub-vault is calculated as follows:
	1. Count the occurrences of each treasure value in the sub-vault.
	2. Assign a priority score to each treasure based on its frequency 
	multiplied by the treasure's value raised to the power of f 
	(i.e., priority_score[treasure] = frequency[treasure] * (value^f)).
	3. Select only the top x treasures based on their priority scores. 
	If two treasures have the same priority score, the treasure with 
	the higher value is prioritized.
	4. Calculate the total value of the selected treasures.

Your task is to return an integer array priority_sums of length n - k + 1, 
where priority_sums[i] represents the priority-weighted treasure sum for 
the sub-vault corresponding to treasures[i..i + k - 1].

Input Format:
---------------
Line-1: Four space separated integers, N, K, X, F
Line-2: N space separated integers, boxes[].

Output Format:
-----------------
An integer array, priority_sums[], of length n - k + 1


Sample Input-1:
-----------------
8 5 2 2
1 2 3 1 2 2 3 4

Sample Output-1:
--------------------
[7, 9, 10, 7]

Explanation:
We calculate the priority-weighted treasure sum for each sub-vault:

1. Sub-vault 1: [1, 2, 3, 1, 2]
   - Frequencies: {1: 2, 2: 2, 3: 1}
   - Priority scores:
     - 1 → 2 * (1^2) = 2
     - 2 → 2 * (2^2) = 8
     - 3 → 1 * (3^2) = 9
   - Top 2 treasures by priority: 3 (score 9) and 2 (score 8).
   - Total value: 2 + 3 + 2  = 7.

2. Sub-vault 2: [2, 3, 1, 2, 2]
   - Frequencies: {2: 3, 3: 1, 1: 1}
   - Priority scores:
     - 2 → 3 * (2^2) = 12
     - 3 → 1 * (3^2) = 9
     - 1 → 1 * (1^2) = 1
   - Top 2 treasures by priority: 2 (score 12) and 3 (score 9).
   - Total value: 2 + 2 + 2 + 3 = 9.

3. Sub-vault 3: [3, 1, 2, 2, 3]
   - Frequencies: {3: 2, 2: 2, 1: 1}
   - Priority scores:
     - 3 → 2 * (3^2) = 18
     - 2 → 2 * (2^2) = 8
     - 1 → 1 * (1^2) = 1
   - Top 2 treasures by priority: 3 (score 18) and 2 (score 8).
   - Total value: 3 + 2 + 2 + 3 = 10.

4. Sub-vault 4: [1, 2, 2, 3, 4]
   - Frequencies: {1: 1, 2: 2, 3: 1, 4: 1}
   - Priority scores:
     - 2 → 2 * (2^2) = 8
     - 3 → 1 * (3^2) = 9
     - 4 → 1 * (4^2) = 16
     - 1 → 1 * (1^2) = 1
   - Top 2 treasures by priority: 4 (score 16) and 3 (score 9).
   - Total value: 3 + 4  = 7.

Sample Input-2:
-----------------
6 3 2 1
5 5 6 7 5 6

Sample Output-1:
--------------------
[16, 13, 13, 13]

Constraints:
1. 1 <= n == treasures.length <= 50
2. 1 <= treasures[i] <= 50
3. 1 <= x <= k <= treasures.length
4. 1 <= f <= 10


 */
import java.util.*;
public class prog2{
    public static int calculatePriority(HashMap<Integer,Integer> freqMap,int x,int f){
        // System.out.println(freqMap);
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->(b[1]*((int)Math.pow(b[0],f))-a[1]*((int)Math.pow(a[0],f))));
        for(int k:freqMap.keySet()){
            pq.add(new int[]{k,freqMap.get(k)});
        }
        int score=0;
        while(x>0){
            x--;
            int[] top=pq.poll();
            score+=(top[0]*top[1]);
        }
        return score;
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        int n=sc.nextInt();
        int k=sc.nextInt();
        int x=sc.nextInt();
        int frequency=sc.nextInt();
        
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        ArrayList<Integer> ans=new ArrayList<>();
        ArrayDeque<Integer> dq=new ArrayDeque<>();
        
        HashMap<Integer,Integer> freqMap=new HashMap<>();
        
        for(int i=0;i<k;i++){
            dq.add(i);
            int ele=arr[i];
            int f=freqMap.getOrDefault(ele,0);
            freqMap.put(ele,f+1);
        }
        ans.add(calculatePriority(freqMap,x,frequency));
        
        for(int i=k;i<n;i++){
            int remId=dq.getFirst();
            dq.removeFirst();
            
            int remEle=arr[remId];
            int f=freqMap.getOrDefault(remEle,0);
            if(f==1){
                freqMap.remove(remEle);
            }
            else{
                freqMap.put(remEle,f-1);
            }
            
            dq.addLast(i);
            freqMap.put(arr[i],freqMap.getOrDefault(arr[i],0)+1);
            
            ans.add(calculatePriority(freqMap,x,frequency));
        }
        System.out.print(ans);
    }
}