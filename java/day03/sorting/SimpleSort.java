import java.util.Arrays;

public class SimpleSort {
    public static void selectionSort(int[] a) {
        for(int i = 0; i < a.length-1; i++) {
            for (int j = i+1; j < a.length; j++) {
                if(a[i] > a[j]) {
                    int t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                }
            }
        }
    }

    public static void bubbleSort(int[] a) {
        for(int i = 1; i < a.length; i++) {
            for (int j = 0; j < a.length-1; j++) {
                if(a[j] > a[j+1]) {
                    int t = a[j];
                    a[j] = a[j+1];
                    a[j+1] = t;
                }
            }
        }
    }

    public static void improvedBubbleSort(int[] a) {
        for(int i = 1; i < a.length; i++) {
            for (int j = 0; j < a.length-i; j++) {
                if(a[j] > a[j+1]) {
                    int t = a[j];
                    a[j] = a[j+1];
                    a[j+1] = t;
                }
            }
        }
    }

    public static void furtherImprovedbubbleSort(int[] a) {
        boolean swap_flag;
        for(int i = 1; i < a.length; i++) {
            swap_flag = false;
            for (int j = 0; j < a.length-i; j++) {
                if(a[j] > a[j+1]) {
                    int t = a[j];
                    a[j] = a[j+1];
                    a[j+1] = t;
                    swap_flag = true;
                }
            }
            if(swap_flag == false)
                break;
        }
    }


    public static void insertionSort(int[] a) {
        int i, j;
        for(i = 1; i < a.length; i++) {
            int t = a[i];
            for (j = i-1; j >= 0 && a[j] > t; j--)
                a[j+1] = a[j];
            a[j+1] = t;
        }
    }

    public static void main(String[] args) {
        int[] arr = {88, 33, 66, 99, 11, 77, 22, 55, 44};
        System.out.println(Arrays.toString(arr));
        // selectionSort(arr);
        // bubbleSort(arr);
        //improvedBubbleSort(arr);
        //furtherImprovedbubbleSort(arr);
	insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }    
}
