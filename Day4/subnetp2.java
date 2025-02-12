/*
Write a program that takes an IP address and subnet mask (in CIDR notation, 
e.g., 192.168.1.1/24) as input and calculates the network and broadcast addresses.

Input Format:
---------------
A String, IPAddress
An integer, CIDR

Output Format:
---------------
Space separated IP addresses, network IP and broadcast IP.


Sample Input-1:
-----------------
192.168.1.10
24

Sample Output-1:
------------------
192.168.1.0 192.168.1.255


Sample Input-2:
-----------------
192.0.2.1
24

Sample Output-2:
------------------
192.0.2.0 192.0.2.255

*/
import java.util.*;
public class subnetp2{
    public static String generateSubnet(int n){
        StringBuilder sb=new StringBuilder();
        int idx=0;
        while(idx<n){
            sb.append('1');
            idx++;
        }
        while(idx<32){
            sb.append('0');
            idx++;
        }
        StringBuilder ans=new StringBuilder();
        int l=0,r=32;
        while(l<r){
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
        
        String ip=sc.nextLine();
        int n=sc.nextInt();
        
        String[] ipAddress=ip.split("\\.");
        String subnet=generateSubnet(n);
        String[] subnetmask=subnet.split("\\.");
        // System.out.println(ip);
        // System.out.println(subnet);
        // System.out.println(Arrays.toString(ipAddress));
        // System.out.println(Arrays.toString(subnetmask));
        StringBuilder networkIp=new StringBuilder();
        StringBuilder broadcastIp=new StringBuilder();
        
        for(int i=0;i<ipAddress.length;i++){
            int ipV=Integer.parseInt(ipAddress[i]);
            int sbV=Integer.parseInt(subnetmask[i]);
            
            int nsbV= ~sbV;
            
            int nV= ipV&sbV;
            int bV= nV | nsbV;
            networkIp.append((nV)+".");
            broadcastIp.append((256+bV)+".");
        }
        System.out.print(networkIp.substring(0,networkIp.length()-1)+" "+broadcastIp.substring(0,broadcastIp.length()-1));
    }
}