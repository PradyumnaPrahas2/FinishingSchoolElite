/*
 * Imagine you are the curator of a historic library, where books are arranged in a 
unique catalog system based on their publication years. The library’s archive is 
structured like a hierarchical tree, with each book’s publication year stored at 
a node. You are given the nodes of this catalog tree starting with main node
and a list of query years.

For each query year, you need to find two publication years:
- The first is the latest year in the archive that is less than or equal to the 
  query year. If no such book exists, use -1.
- The second is the earliest year in the archive that is greater than or equal 
  to the query year. If no such book exists, use -1.

Display the results as an list of pairs, where each pair corresponds to a query.

Example 1:
----------
Input: 
2006 2002 2013 2001 2004 2009 2015 2014
2002 2005 2016

Output:
[[2002, 2002], [2004, 2006], [2015, -1]] 


Archive Structure:
          2006
         /    \
     2002     2013
     /   \     /   \
  2001  2004  2009  2015
                     /
                  2014
                  
Explanation:  
- For the query 2002, the latest publication year that is ≤ 2002 is 2002, and 
  the earliest publication year that is ≥ 2002 is also 2002.  
- For the query 2005, the latest publication year that is ≤ 2005 is 2004, and 
  the earliest publication year that is ≥ 2005 is 2006.  
- For the query 2016, the latest publication year that is ≤ 2016 is 2015, but 
  there is no publication year ≥ 2016, so we output -1 for the second value.

Example 2:
----------
Input:  
2004 2009
2003

Output:
[[-1, 2004]]

Explanation:  
- For the query 2003, there is no publication year ≤ 2003, while the earliest 
  publication year that is ≥ 2003 is 2004.

Constraints:
- The total number of books in the archive is in the range [2, 10^5].
- Each publication year is between 1 and 10^6.
- The number of queries n is in the range [1, 10^5].
- Each query year is between 1 and 10^6.

 */
import java.util.*;
class Tree{
    int data;
    Tree left,right;
    public Tree(int v){
        this.data=v;
        this.left=null;
        this.right=null;
    }
}
public class prog4{
    public static void addNode(Tree root,int newval){
        if(root==null) return;
        
        if(root.data>newval){
            if(root.left==null){
                root.left=new Tree(newval);
                return;
            }
            addNode(root.left,newval);
        }
        if(root.data<newval){
            if(root.right==null){
                root.right=new Tree(newval);
                return;
            }
            addNode(root.right,newval);
        }
    }
    public static void lowerbound(Tree root,int target,int[] ans){
        if(root==null) return;
        
        int curVal=root.data;
    
        if(root.data>target){
            lowerbound(root.left,target,ans);
        }
        else{
            int diff=Math.abs(target-curVal);
            if(diff<ans[0]){
                ans[0]=diff;
                ans[1]=curVal;
            }
            lowerbound(root.right,target,ans);
        }
    }
    public static void upperbound(Tree root,int target,int[] ans){
        if(root==null) return;
        
        int curVal=root.data;
        
        if(root.data>=target){
            int diff=Math.abs(target-curVal);
            if(diff<ans[0]){
                ans[0]=diff;
                ans[1]=curVal;
            }
            upperbound(root.left,target,ans);
        }
        else{
            upperbound(root.right,target,ans);
        }
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        String[] arr=sc.nextLine().split(" ");
        
        Tree root=new Tree(Integer.parseInt(arr[0]));
        
        for(int i=1;i<arr.length;i++){
            
            addNode(root,Integer.parseInt(arr[i]));
        }
        
        String[] queries=sc.nextLine().split(" ");
        
        List<List<Integer>> ans=new ArrayList<>();
        
        for(int i=0;i<queries.length;i++){
            int[] lower=new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE};
            int[] upper=new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE};
            
            lowerbound(root,Integer.parseInt(queries[i]),lower);
            upperbound(root,Integer.parseInt(queries[i]),upper);
            
            if(lower[0]==Integer.MAX_VALUE) lower[1]=-1;
            if(upper[0]==Integer.MAX_VALUE) upper[1]=-1;
            ans.add(new ArrayList<>(Arrays.asList(lower[1],upper[1])));
        }
        System.out.println(ans);
    }
}