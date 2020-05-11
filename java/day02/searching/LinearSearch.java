import java.util.Arrays;
import java.util.Scanner;

public class LinearSearch {
    public static int linearSearch(int[] a, int key) {
        for(int i=0; i<a.length; i++) {
            if(a[i] == key)
                return i;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] arr = {88, 33, 66, 99, 11, 77, 22, 55, 11};
        System.out.println(Arrays.toString(arr));
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number to find: ");
        int k = sc.nextInt();
        
        int index = linearSearch(arr, k);
        if(index == -1)
            System.out.println("Element not found.");
        else
            System.out.println("Element found at index " + index);

        sc.close();
    }    
}