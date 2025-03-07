/*
 * "Emphatic Pronunciation" of a given word is where we take the word and
replicate some of the letter to emphasize their impact.

Instead of saying 'oh my god', someone may say "ohhh myyy goddd", 
We define emphatic pronunciation of a word, which is derived by replicating 
a group (or single) of letters in the original word. 

So that the replicated group is atleast 3 characters or more and 
greater than or equal to size of original group. 
For example Good -> Goood is an emphatic pronunciation,
but Goodd is not because in Goodd the 'd' are only occuring twice consecutively.
 
In the question you are given the "Emphatic pronunciation" word, 
you have to findout how many words can legal result in the 
"emphatic pronunciation" word.

Input Format:
-------------
Line-1 -> A String contains a single word, Emphatic Pronunciation word
Line-2 -> Space seperated word/s

Output Format:
--------------
Print an integer as your result


Sample Input-1:
---------------
goood
good goodd

Sample Output-1:
----------------
1


Sample Input-2:
---------------
heeelllooo
hello hi helo

Sample Output-2:
----------------
2


 */
import java.util.*;
public class prog3{
    public static List<int[]> findAbbr(String s1){
        List<int[]> ans=new ArrayList<>();
        for(int i=0;i<s1.length();i++){
            char c=s1.charAt(i);
            int count=0;
            for(int j=i;j<s1.length();j++){
                if(c==s1.charAt(j)){
                    count++;
                }
            }
            i+=count-1;
            ans.add(new int[]{(c-'a'),count});
        }
        return ans;
    }
    public static boolean check(String s1,String s2){
        List<int[]> abbr=findAbbr(s1);
        List<int[]> abbr2=findAbbr(s2);
        
        if(abbr.size()!=abbr2.size()) return false;
        
        for(int i=0;i<abbr.size();i++){
            char c1=(char)(abbr.get(i)[0]+97);
            int count=abbr.get(i)[1];
            
            char c2=(char)(abbr2.get(i)[0]+97);
            int count2=abbr2.get(i)[1];
            
            // System.out.println(c1+" "+count+" -> "+c2+" "+count2);
            if(c1!=c2) return false;
            
            if(count2>count) return false;
            
            if(count<3 && count!=count2) return false;
            
            if(count>=3) continue;
            
        }
        return true;
        
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        String s=sc.nextLine();
        
        String[] str=sc.nextLine().split(" ");
        int ans=0;
        for(int i=0;i<str.length;i++){
            if(check(s,str[i])){
                ans++;
            }
        }
        System.out.print(ans);
    }
}