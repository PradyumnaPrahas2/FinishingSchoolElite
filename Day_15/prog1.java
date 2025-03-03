/*
 * Imagine you are an artisan tasked with assembling a decorative mosaic from a 
collection of uniquely colored tiles. Each tile is marked with a character, 
and your challenge is to rearrange these tiles to create a design that mirrors 
itself perfectly from left to right. 
Your goal is to determine whether the available tiles can be arranged to form 
such a symmetric pattern. Print true if a symmetric design is possible,
and false otherwise.


Input format:
A string representing the characters on the tiles.

Output format:
Print a boolean value

Example 1:
input: work
output: false

Example 2:
input: ivicc
output: true

Constraints:
1 <= string.length <= 5000
tile characters are all lowercase English letters.

 */
import java.util.*;
public class prog1{
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        String s = sc.nextLine();
        
        int[] map = new int[26];
        
        for(int i=0;i<s.length();i++){
            map[s.charAt(i)-'a']++;
        }
        
        if(s.length()%2==0){
            for(int i=0;i<26;i++){
                if(map[i]%2!=0){
                    System.out.print(false);
                    return;
                }
            }
        }
        else{
            boolean isOdd=false;
            for(int i=0;i<26;i++){
                if(map[i]%2==1){
                    if(isOdd==true){
                        System.out.print(false);
                        return;
                    }
                    else{
                        isOdd=true;
                    }
                }
            }
            System.out.print(true);
            return;
        }
        System.out.print(true);
    }
}