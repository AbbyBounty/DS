import java.util.Arrays;

public class QuickSort {
    public static void quickSort(int[] a, int left, int right) {
        if(left >= right)
            return;
        int i = left, j = right, pivot = a[left];
        while (i < j) {
            while (i <= right && a[i] <= pivot)
                i++;
            while (a[j] > pivot)
                j--;
            if (i < j) {
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[left];
        a[left] = a[j];
        a[j] = t;

        quickSort(a, left, j-1);
        quickSort(a, j+1, right);
    }

    public static void main(String[] args) {
        int[] arr = { 55, 11, 99, 33, 77, 22, 66, 44, 88 };
        System.out.println(Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}