import java.util.Scanner;

public class PrimeNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number: ");
        int i, n = sc.nextInt();
        for(int num=1; num<=n; num++) {
            for(i=2; i<num; i++) {
                if(num % i == 0)
                    break;
            }
            if(i==num)
                System.out.println(num);
        }
        sc.close();
    }
}
