/*
 * Imagine you’re managing a busy cafe where every order is logged. You have a 
record—a list of dish names ordered throughout the day—and you want to determine
which dishes are the most popular. Given an list of strings representing the dish
names and an integer P, your task is to return the P most frequently ordered dishes.

The results must be sorted by the number of orders, from the most frequent to 
the least. If two dishes have been ordered the same number of times, they should
be listed in alphabetical order.

Input Format:
-------------
Line-1: comma separated line of words, list of words.
Line-2: An integer P, number of words to display. 

Output Format:
--------------
Print P number of most common used words.

Example 1:
----------
Input=
coffee,sandwich,coffee,tea,sandwich,muffin
2
Output=
[coffee, sandwich]

Explanation: "coffee" and "sandwich" are the two most frequently ordered items. 
Although both appear frequently, "coffee" is placed before "sandwich" because 
it comes earlier alphabetically.

Example 2:
----------
Input=
bagel,muffin,scone,bagel,bagel,scone,scone,muffin,muffin
3
Output=
[bagel, muffin, scone] 

Explanation: "bagel", "muffin", and "scone" are the three most popular dishes 
with order frequencies of 3, 3, and 2 respectively. Since "bagel" and "muffin" 
have the same frequency, they are ordered alphabetically.

Constraints:

- 1 ≤ orders.length ≤ 500  
- 1 ≤ orders[i].length ≤ 10  
- Each orders[i] consists of lowercase English letters.  
- P is in the range [1, The number of unique dish names in orders].
 */
import java.util.*;
class Trie{
    Trie[] children;
    boolean last;
    int freq;
    public Trie(){
        this.children=new Trie[26];
        this.last=false;
        this.freq=0;
    }
}
public class prog2{
    public static void insert(Trie root,String word){
        Trie node=root;
        
        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            int idx=c-'a';
            
            if(node.children[idx]==null) node.children[idx]=new Trie();
            
            node=node.children[idx];
        }
        
        node.last=true;
        node.freq++;
        
        
    }
    public static int search(Trie root,String word){
        Trie node=root;
        
        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            int idx=c-'a';
            
            if(node.children[idx]==null) return -1;
            
            node=node.children[idx];
        }
        
        node.last=true;
        // System.out.println(word+" "+node.freq);
        return node.freq;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        
        String[] words=sc.nextLine().split(",");
        
        TreeSet<String> set=new TreeSet<>();
        int k=sc.nextInt();
        
        Trie root=new Trie();
        
        for(String word:words){
            insert(root,word);
            set.add(word);
        }
        
        List<String[]> result=new ArrayList<>();
        
        for(String word:set){
            int count=search(root,word);
            if(count!=-1){
                result.add(new String[]{word,""+count});
            }
        }
        
        Collections.sort(result,(a,b)->{
            return Integer.parseInt(b[1])-Integer.parseInt(a[1]);
        });
        
        List<String> ans=new ArrayList<>();
        
        for(int i=0;i<k;i++){
            ans.add(result.get(i)[0]);
        }
        System.out.print(ans);
    
    }
}