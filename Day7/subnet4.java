/*
 * In computer networking, subnetting is the process of dividing a larger IP network
into multiple smaller subnetworks (subnets). Given a base network IP address, 
a CIDR (Classless Inter-Domain Routing) prefix, and the number of subnets required, 
write a Java program that calculates the starting addresses of all the subnets.

Your program should take as input:
	- A base network address (e.g., 192.168.1.0).
	- A CIDR prefix (e.g., /26 means a subnet mask of 255.255.255.192).
	- The number of subnets to generate.

The program should then compute and return the starting addresses of each subnet.

Input Format:
-------------
A string NIP: The base network IP address (e.g., "192.168.1.0").
An integer CIDR: The subnet mask prefix (e.g., 26 for /26).
An integer numSubnets: The number of subnets to be generated.

Output Format:
--------------
A list of subnet addresses, each representing the starting address of a subnet.


Sample Input:
-------------
192.168.1.0
26
4

Sample Output:
--------------
[192.168.1.0/28, 192.168.1.16/28, 192.168.1.32/28, 192.168.1.48/28]

Explanation:
------------
A /26 subnet has 64 IP addresses. The starting addresses of 
the first four subnets are:
192.168.1.0/28, 
192.168.1.16/28, 
192.168.1.32/28, 
192.168.1.48/28

 */
import java.util.*;
public class subnet4{
    public static String join(int[] ip,int cidr){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<ip.length;i++){
            sb.append(ip[i]);
            if(i!=ip.length-1){
                sb.append(".");
            }
        }
        sb.append("/").append(cidr);
        return sb.toString();
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        String[] networkIp=sc.nextLine().split("\\.");
        int cidr=sc.nextInt();
        int nSubnets=sc.nextInt();
        
        int[] nIp=new int[networkIp.length];
        for(int i=0;i<nIp.length;i++){
            nIp[i]=Integer.parseInt(networkIp[i]);
        }

        int new_cidr=cidr+(int)(Math.log(nSubnets)/Math.log(2));

        int incr=(int)(Math.pow(2,(32-cidr))/nSubnets);

        ArrayList<String> seq=new ArrayList<>();
        seq.add(join(nIp,new_cidr));

        for(int i=1;i<nSubnets;i++){
            nIp[3]+=incr;

            for(int j=3;j>0;j--){
                if(nIp[j]>255){
                    nIp[j]-=256;
                    nIp[j-1]++;
                }
            }
            seq.add(join(nIp,new_cidr));
        }
        System.out.print(seq);
    }
}