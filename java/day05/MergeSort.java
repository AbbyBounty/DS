import java.util.Arrays;

public class MergeSort {
    public static void mergeSort(int[] a, int left, int right) {
        // terminating condition
        if(left >= right)
            return;
        // divide array into two partitions
        int m = (left+right)/2;
        // sort both partitions
        mergeSort(a, left, m);
        mergeSort(a, m+1, right);
        // merge two sorted partitions into temp array
        int i = left, j = m+1, k=0, n=right-left+1;
        int[] temp = new int[n]; 
        while(i<=m && j<=right)
            temp[k++] = a[i] < a[j] ? a[i++] : a[j++];
        while(i<=m)
            temp[k++] = a[i++];
        while(j<=right)
            temp[k++] = a[j++];
        // overwrite temp array back to original array
        for(i=0; i<n; i++)
            a[left+i] = temp[i];
    }

    public static void main(String[] args) {
        int[] arr = { 55, 11, 99, 33, 77, 22, 66, 44, 88 };
        System.out.println(Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}