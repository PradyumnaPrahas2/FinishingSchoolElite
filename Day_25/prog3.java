/*
 * A bookstore manager is analyzing the best-selling books from their records. 
Each record contains a book title, author, and publishing date in the format 
"DD-MM-YYYY".

Your task is to extract and count the number of distinct publishing years from 
the given list of best-selling books.

Input Format
------------
A string containing multiple book records, where each book entry includes a 
publishing date in "DD-MM-YYYY" format.

Output Format
--------------
Return an integer representing the total number of distinct publishing years 
in the given records.


Sample Input:
--------------
The Great Gatsby by F. Scott Fitzgerald was published on 10-04-1925. To Kill a Mockingbird by Harper Lee was published on 11-07-1960.

Sample Output:
----------------
2

Explanation:
--------------
The books were published in 1925 and 1960.
Total distinct years = 2.


Sample Input:
-------------
1984 by George Orwell was published on 08-06-1949. Animal Farm by George Orwell was published on 17-08-1945. The Catcher in the Rye by J.D. Salinger was published on 16-07-1951

Sample Output:
---------------
3

Explanation:
-------------
The books were published in 1945, 1949, and 1951.
Total distinct years = 3.


Sample Input:
---------------
Pride and Prejudice by Jane Austen was published on 28-01-1812. Sense and Sensibility by Jane Austen was published on 30-10-1812. Emma by Jane Austen was published on 23-12-1812.

Sample Output:
----------------
1


 */
import java.util.*;
public class prog3{
    public static void main (String[] args) {
        Scanner sc=new Scanner(System.in);
        
        String[] arr=sc.nextLine().split(" ");
        HashSet<Integer> set=new HashSet<>();
        for(String s:arr){
            String[] inner = s.split("-");
            
            if(inner.length==3){
                try{
                    if(inner[0].length()==2 && inner[1].length()==2 && inner[2].length()>=4){
                    int month=Integer.parseInt(inner[0]);
                    int date=Integer.parseInt(inner[1]);
                    try{
                        
                        int year=Integer.parseInt(inner[2]);
                        set.add(year);
                    }
                    catch(Exception e){
                        try{
                        int year=Integer.parseInt(inner[2].substring(0,inner[2].length()-1));
                        
                        set.add(year);
                        }
                        catch(Exception e1){
                            continue;
                        }
                    }
                    }
                }
                catch(Exception e){
                    continue;
                }
            }
        }
        // System.out.println(set);
        System.out.println(set.size());
    }
}