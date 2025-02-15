/*
 * In networking, a subnet is a portion of a network with a defined range of IP addresses. 
Two subnets overlap if they share some common IP addresses. Given two network 
IP addresses with their respective CIDR notations, write a program that determines 
whether these subnets overlap.

Your program should take as input:

IP1: The first subnet’s network address.
CIDR1: The CIDR notation (prefix length) for the first subnet.
IP2: The second subnet’s network address.
CIDR2: The CIDR notation (prefix length) for the second subnet.
The program should return true if the subnets overlap and false otherwise.

Input Format:
-------------
A string IP1: The first network IP address (e.g., "192.168.1.0").
An integer CIDR1: The subnet mask prefix (e.g., 24 for /24).
A string IP2: The second network IP address (e.g., "192.168.1.128").
An integer CIDR2: The subnet mask prefix for the second subnet.

Output Format:
--------------
A boolean value, if the two subnets overlap or not.


Sample Input:
-------------
192.168.1.0
24
192.168.1.128
25

Sample Output:
--------------
true

Explanation:
------------
A /26 subnet has 64 IP addresses. The starting addresses of 
the first four subnets are:
192.168.1.0
192.168.1.64
192.168.1.128
192.168.1.192


Sample Input:
-------------
10.0.0.0
16
10.1.0.0
16

Sample Output:
--------------
false
 */
import java.util.*;
public class subnet5 {

    public static int[] getIp(String[] ip){
        int[] newip=new int[ip.length];
        for(int i=0;i<newip.length;i++){
            newip[i]=Integer.parseInt(ip[i]);
        }
        return newip;
    }
    public static int[] getnewIp(int[] ip,long end){
        int[] newip=new int[ip.length];
        for(int i=0;i<ip.length;i++) newip[i]=ip[i];
        newip[3]+=end;
        for(int j=3;j>0;j--){
            if(newip[j]>255){
                newip[j]-=256;
                newip[j-1]++;
            }
        }
        return newip;
    }
    public static long getBigInt(int[] ip){
        long l=0;
        int p=3;
        for(int i=0;i<ip.length;i++){
            l+=(long)(ip[i])*(long)Math.pow(256,p);
            p--;
        }
        return l;
    }
    public static boolean checkOverlap(long a1,long a2,long b1,long b2){
        return (a1<=b1 && a2>=b1) ||(a1<=b2 && a2>=b2) || (b1<=a1 && b2>=a1) || (b1<=a2 && b2>=a2);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        String[] ip1=sc.next().split("\\.");
        int cidr1=sc.nextInt();
        String[] ip2=sc.next().split("\\.");
        int cidr2=sc.nextInt();

        int[] Ip1_start=getIp(ip1);
        int[] Ip2_start=getIp(ip2);

        int[] Ip1_end=new int[Ip1_start.length];
        int[] Ip2_end=new int[Ip2_start.length];

        long endrange1=(long)Math.pow(2,32-cidr1)-1;
        long endrange2=(long)Math.pow(2,32-cidr2)-1;

        Ip1_end=getnewIp(Ip1_start,endrange1);
        Ip2_end=getnewIp(Ip2_start,endrange2);

        boolean inColab=checkOverlap(getBigInt(Ip1_start),getBigInt(Ip1_end),getBigInt(Ip2_start),getBigInt(Ip2_end));
        System.out.print(inColab);
    }
}
