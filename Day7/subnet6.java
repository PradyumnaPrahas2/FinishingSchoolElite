// return class of an ip
import java.util.*;
public class subnet6 {
    public static String classOf(int ip){
        if(ip>=1 && ip<=126) return "Class A";
        if(ip>127 && ip<=191) return "Class B";
        if(ip>191 && ip<=223) return "Class C";
        return "Multiclass";
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        String[] ip=sc.next().split("\\.");

        System.out.print(classOf(Integer.parseInt(ip[0])));
    }
}
