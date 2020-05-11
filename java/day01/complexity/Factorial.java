
import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number: ");
        int res, i, n = sc.nextInt();
        res = 1;
        for(i=1; i<=n; i++)
            res = res * i;
        System.out.println("Factorial: " + res);
        sc.close();
    }    
}