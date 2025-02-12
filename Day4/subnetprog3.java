/*
 * 
Given two IP addresses IP1 and IP2, and a subnet mask, your task is to check 
whether IP-1 and IP-2 belongs to same host range or not.

Input Format:
---------------
Two space separated strings, IP1 and IP2.
An integer, CIDR (subnet mask).

Output Format:
---------------
A boolean result.


Sample Input-1:
-----------------
192.168.1.10 192.168.1.20
24

Sample Output-1:
------------------
true


Sample Input-2:
-----------------
192.0.2.1 192.0.3.253
24

Sample Output-2:
------------------
false


 */
import java.util.*;
public class subnetprog3{
    public static String generateSubnet(int n) {
        StringBuilder sb=new StringBuilder();
        int idx=0;
        while(idx<n){
            sb.append('1');
            idx++;
        }
        while(idx<32)
        {
            sb.append('0');
            idx++;
        }
        StringBuilder ans=new StringBuilder();
        int l=0,r=32;
        while(l<r)
        {
            int diff=7;
            int dec=0;
            while(diff>=0){
                if(sb.charAt(l)=='1'){
                    dec+=(int)Math.pow(2,diff);
                }
                l++;
                diff--;
            }
            ans.append(dec+".");
        }
        return ans.substring(0,ans.length()-1).toString();
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        String ip1=sc.next();
        String ip2=sc.next();
        int n=sc.nextInt();
        
        String[] ipVal1=ip1.split("\\.");
        String[] ipVal2=ip2.split("\\.");
        
        String subnet=generateSubnet(n);
        
        String[] subnetMask=subnet.split("\\.");
        
        for(int i=0;i<ipVal1.length;i++){
            int ip1Val=Integer.parseInt(ipVal1[i]);
            int ip2Val=Integer.parseInt(ipVal2[i]);
            
            int sb=Integer.parseInt(subnetMask[i]);
            
            int and1=ip1Val&sb;
            int and2=ip2Val&sb;
            if(and1!=and2){
                System.out.print(false);
                return;
            }
        }
        System.out.print(true);
    }
}