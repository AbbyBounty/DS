
class CircularQueueImpl1 {
    private final int size;
    private int front, rear, count;
    private int[] arr;
    public CircularQueueImpl1(int size) {
        this.size = size;
        this.arr = new int[size];
        this.front = -1;
        this.rear = -1;
        this.count = 0;
    }
    public void push(int ele) {
        this.rear = (this.rear+1) % size;
        this.arr[this.rear] = ele;
        this.count++;
    }
    public void pop() {
        this.front = (this.front+1) % size;
        this.count--;
    }
    public int peek() {
        int index = (this.front+1) % size;
        return this.arr[index];
    }
    public boolean isEmpty() {
        return this.count == 0;
    }
    public boolean isFull() {
        return this.count == this.size;
    }
}

public class MyCircularQueue1 {
    public static void main(String[] args) {
        CircularQueueImpl1 q = new CircularQueueImpl1(5);
        q.push(10);
        q.push(20);
        q.push(30);
        q.push(40);
        while(!q.isEmpty()) {
            System.out.println("Popping: " + q.peek());
            q.pop();
        }
    }
}
