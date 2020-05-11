import java.util.Scanner;

public class DecToBinary {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number: ");
        int n = sc.nextInt();
        String res = "";
        while(n != 0) {
            res = (n % 2) + res;
            n = n / 2;
        }
        System.out.println("Bin = " + res);
        sc.close();
    }    
}