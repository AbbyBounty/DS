
class MaxHeap {
    private int[] arr;
    private int size;

    public MaxHeap(int[] a) {
        arr = a;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void makeHeap() {
        // heap size is num of elements in heap (1 to size)
        size = arr.length-1;
        // from mid node to first node check if each node is greater than both child nodes
        for(int i=size/2; i>=1; i--) {
            // take ith element in temp
            int ci = i * 2, temp = arr[i];
            // find appropriate position for temp
            while(ci <= size) {
                // if right child is present and greater than left child, consider it (right child)
                if((ci+1) <= size && arr[ci+1] > arr[ci])
                    ci = ci + 1;
                // if temp is greater than both child nodes, found right position (parent of child)
                if(temp > arr[ci])
                    break;
                // shift child to parent position
                arr[ci/2] = arr[ci];
                // start comparing its child
                ci = ci * 2;
            }
            // insert temp as max child's parent
            arr[ci/2] = temp;
        }
    }

    public int del() {
        int max = arr[1]; // delete max node & return the same
        // get the last ele into temp
        int temp = arr[size--];
        // find appropriate position for temp
        int i = 1, ci = i * 2;
        while(ci <= size) {
            // if right child is present and greater than left child, consider it (right child)
            if((ci+1) <= size && arr[ci+1] > arr[ci])
                ci = ci + 1;
            // if temp is greater than both child nodes, found right position (parent of child)
            if(temp > arr[ci])
                break;
            // shift child to parent position
            arr[ci/2] = arr[ci];
            // start comparing its child
            ci = ci * 2;
        }
        // insert temp as max child's parent
        arr[ci/2] = temp;
        return max;
    }
    void printHeap() {
        System.out.print("HEAP:");
        for (int i = 1; i < size; i++)
            System.out.print(arr[i] + ", ");
        System.out.println();
    }
}

public class HeapSort {

    public static void printArray(int[] a) {
        System.out.print("ARR: ");
        for (int i = 1; i < a.length; i++)
            System.out.print(a[i] + ", ");
        System.out.println();
    }

    public static void heapSort(int[] a) {
        MaxHeap h = new MaxHeap(a);
        h.makeHeap();
        int size = a.length - 1;
        for (int i = size; i >= 1; i--) {
            int max = h.del();
            a[size] = max;
            size--;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 0, 20, 12, 35, 15, 10, 80, 30, 17, 2, 1 };
        printArray(arr);
        heapSort(arr);
        printArray(arr);
    }
}