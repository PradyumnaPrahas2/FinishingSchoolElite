/*
 * Cliff Shaw is working on the singly linked list.
He is given a list of boxes arranged as singly linked list,
where each box is printed a positive number on it.

Your task is to help Mr Cliff to find the given list is equivalent to 
the reverse of it or not. If yes, print "true", otherwise print "false"

Input Format:
-------------
Line-1: space separated integers, boxes as list.

Output Format:
--------------
Print a boolean a value.


Sample Input-1:
---------------
3 6 2 6 3

Sample Output-1:
----------------
true


Sample Input-2:
---------------
3 6 2 3 6

Sample Output-2:
----------------
false
 */
import java.util.*;
class LL{
    int data;
    LL next;
    public LL(int v){
        this.data=v;
        this.next=null;
    }
}
public class prog2{
    public static void printll(LL head){
        while(head!=null){
            System.out.print(head.data+" -> ");
            head=head.next;
        }
        System.out.print("NULL\n");
    }
    public static LL reverse(LL head){
        LL prev=null;
        while(head!=null){
            LL nn=new LL(head.data);
            nn.next=prev;
            prev=nn;
            head=head.next;
        }
        return prev;
    }
    public static boolean check(LL first,LL nxt){
        first = reverse(first);
        // LL nxt = reverse(nt);
        // printll(first);
        // printll(nxt);
        while(first!=null && nxt!=null){
            if(first.data!=nxt.data) return false;
            first=first.next;
            nxt=nxt.next;
        }
        return true;
    }
    public static boolean ispal(LL head){
        LL slow = head;
        LL fast = head;
        LL firstHalf = new LL(head.data);
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            
            LL newnode = new LL(slow.data);
            newnode.next = firstHalf;
            firstHalf = newnode;
        }
       
        LL prev =null;
        firstHalf = firstHalf.next;
        
        while(slow!=null){
            LL newnode = new LL(slow.data);
            
            newnode.next = prev;
            prev = newnode;
            
            slow=slow.next;
        }
        return check(firstHalf,prev);
        
    }
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        String[] arr=sc.nextLine().split(" ");
        
        LL head = new LL(Integer.parseInt(arr[0]));
        
        for(int i=1;i<arr.length;i++){
            LL nextnode = new LL(Integer.parseInt(arr[i]));
            
            nextnode.next = head;
            
            head = nextnode;
        }
        
        // printll(head);
        
        System.out.print(ispal(head));
    }
}