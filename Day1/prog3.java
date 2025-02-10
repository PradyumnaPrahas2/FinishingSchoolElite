// sliding window
import java.util.*;
public class prog3 {
    public static void main(String[] args) {
        
        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();
        int mf=sc.nextInt();
        int mA=sc.nextInt();

        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }

        HashMap<Integer,Integer> freqMap=new HashMap<>();
        int sum=0;
        int start=0,end=0;
        int ans=-1;
        for(int i=0;i<n;i++){
            int ele=arr[i];
            int freq=freqMap.getOrDefault(ele,0);
            
            if(freq==mf){
                start++;
            }
            freqMap.put(ele,freq+1);
            end++;
            sum+=ele;
            if(sum>mA){
                while(sum>mA && start<=end){
                    int element=arr[start];
                    freq=freqMap.getOrDefault(element,0);
                    if(freq==1){
                        freqMap.remove(element);
                    }
                    else{
                        freqMap.put(element,freq-1);
                    }
                    sum-=element;
                    start++;
                }
            }
            ans=Math.max(ans,end-start);

        }
        
        ans=Math.max(ans,end-start);
        System.out.print(ans);
    }
}
