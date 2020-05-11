import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {
    public static int binarySearch(int[] a, int key) {
        int i=0, j=a.length-1, m;
        while(i <= j) {
            m = (i + j) / 2;
            if(key == a[m])
                return m;
            if(key < a[m])
                j = m - 1;
            else
                i = m + 1;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] arr = {11, 22, 33, 44, 55, 66, 77, 88, 99};
        System.out.println(Arrays.toString(arr));
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number to find: ");
        int k = sc.nextInt();
        
        int index = binarySearch(arr, k);
        if(index == -1)
            System.out.println("Element not found.");
        else
            System.out.println("Element found at index " + index);

        sc.close();
    }    
}