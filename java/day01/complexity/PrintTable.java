
import java.util.Scanner;

public class PrintTable {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number: ");
        int i, n = sc.nextInt();
        for(i=1; i<=10; i++)
            System.out.println(n * i);
        sc.close();
    }    
}
