/*
 * In the world of secret codes and cryptography, you are entrusted with deciphering a hidden message. 
You have two encoded keys, key1 and key2, both of equal length. Each character 
in key1 is paired with the corresponding character in key2. 

This relationship follows the standard rules of an equivalence cipher:
• Self-Mapping: Every character inherently maps to itself.  
• Mutual Mapping: If a character from key1 maps to one in key2, then that 
  character in key2 maps back to the one in key1.  
• Chain Mapping: If character A maps to B, and B maps to C, then A, B, and C 
  are all interchangeable in this cipher.

Using this mapping, you must decode a cipherText, by replacing every character 
with the smallest equivalent character from its equivalence group. 
The result should be the relatively smallest possible decoded message.


Input Format:
-------------
Three space separated strings, key1 , key2 and cipherText

Output Format:
--------------
Print a string, decoded message which is relatively smallest string of cipherText.

Example 1: 
input=
attitude progress apriori
output=
aaogoog

graph -> a->a,p
         t->r,o
         i->g,i
         u->e,u
         d->s,d
         e->s,e
         
         aaogoog


Explanation: The mapping pairs form groups: [a, p], [o, r, t], [g, i], [e, u], 
[d, e, s]. By substituting each character in cipherText with the smallest from 
its group, you decode the message to "aaogoog".


Constraints:  
• 1 <= key1.length, key2.length, cipherText.length <= 1000  
• key1.length == key2.length  
• key1, key2, and cipherText consist solely of lowercase English letters.

 */
import java.util.*;
public class prog2{
    public static void dfs(char source,HashMap<Character,List<Character>> graph,HashSet<Character> visited,List<Character> pq,char[] min_pair){
        if(visited.contains(source)) return;
        if((min_pair[0]-'0')>(source-'0')){
            min_pair[0]=source;
        }
        pq.add(source);
        
        visited.add(source);
        
        for(char child:graph.getOrDefault(source,new ArrayList<>())){
            dfs(child,graph,visited,pq,min_pair);
        }
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String[] str = sc.nextLine().split(" ");
        
        HashMap<Character,List<Character>> graph = new HashMap<>();
        
        HashSet<Character> allchars = new HashSet<>();
        
        HashSet<Character> visited = new HashSet<>();
        
        for(int i=0;i<str[0].length();i++){
            
            
            char c1 = str[0].charAt(i);
            char c2 = str[1].charAt(i);
            
            allchars.add(c1);
            allchars.add(c2);
            
            List<Character> l1 = graph.getOrDefault(c1,new ArrayList<>());
            l1.add(c2);
            graph.put(c1,l1);
            
            List<Character> l2 = graph.getOrDefault(c2,new ArrayList<>());
            l2.add(c1);
            graph.put(c2,l2);
        }
        
        HashMap<Character,Character> allsets = new HashMap<>();
        
        for(char c:allchars){
            if(!visited.contains(c)){
                
                List<Character> pq = new ArrayList<>();
                char[] min_pair = {'z'};
                dfs(c,graph,visited,pq,min_pair);
                
                for(char chr:pq){
                    allsets.put(chr,min_pair[0]);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<str[2].length();i++){
            sb.append(allsets.getOrDefault(str[2].charAt(i),str[2].charAt(i)));
        }
        System.out.print(sb.toString());
    }
}