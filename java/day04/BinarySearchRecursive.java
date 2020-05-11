import java.util.Arrays;
import java.util.Scanner;

public class BinarySearchRecursive {
    public static int binarySearch(int[] a, int left, int right, int key) {
        if (left > right)
            return -1;
        int i, m;
        m = (left + right) / 2;
        if (key == a[m])
            return m;
        if (key < a[m])
            i = binarySearch(a, left, m-1, key);
        else
            i = binarySearch(a, m+1, right, key);
        return i;
    }

    public static void main(String[] args) {
        int[] arr = { 11, 22, 33, 44, 55, 66, 77, 88, 99 };
        System.out.println(Arrays.toString(arr));

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number to find: ");
        int k = sc.nextInt();

        int index = binarySearch(arr, 0, arr.length-1, k);
        if (index == -1)
            System.out.println("Element not found.");
        else
            System.out.println("Element found at index " + index);

        sc.close();
    }
}
