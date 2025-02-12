/*
Write a program that takes an IP address and subnet mask (in CIDR notation, 
e.g., 192.168.1.1/24) as input and calculates the subnet mask in dotted decimal 
format.

Input Format:
---------------
An integer, CIDR

Output Format:
---------------
String, Subnet's IP Address


Sample Input-1:
-----------------
25

Sample Output-1:
------------------
255.255.255.128


Sample Input-2:
-----------------
22

Sample Output-2:
------------------
255.255.252.0
*/

import java.util.*;
public class subnetp1{
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        int n=sc.nextInt();
        
        int idx=0;
        
        StringBuilder sb=new StringBuilder();
        
        while(idx<n){
            sb.append('1');
            idx++;
        }
        while(idx<32){
            sb.append('0');
            idx++;
        }
        
        StringBuilder ans=new StringBuilder();
        
        int left=0,right=32;
        while(left<right){
            int diff=7;
            
            int dec=0;
            
            while(diff>=0){
                if(sb.charAt(left)=='1'){
                    dec+=(int)Math.pow(2,diff);
                }
                left++;
                diff--;
            }
            ans.append(dec+".");
        }
        System.out.print(ans.substring(0,ans.length()-1));
    }
}