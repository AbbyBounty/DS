
class CircularQueueImpl2 {
    private final int size;
    private int front, rear;
    private int[] arr;
    public CircularQueueImpl2(int size) {
        this.size = size;
        this.arr = new int[size];
        this.front = -1;
        this.rear = -1;
    }
    public void push(int ele) {
        this.rear = (this.rear+1) % size;
        this.arr[this.rear] = ele;
    }
    public void pop() {
        this.front = (this.front+1) % size;
        if(this.front == this.rear)
            this.front = this.rear = -1;
    }
    public int peek() {
        int index = (this.front+1) % size;
        return this.arr[index];
    }
    public boolean isEmpty() {
        return this.rear == this.front;
    }
    public boolean isFull() {
        return (this.front == -1 && this.rear == this.size-1) || 
                (this.front == this.rear && this.front!=-1);
    }
}

public class MyCircularQueue2 {
    public static void main(String[] args) {
        CircularQueueImpl2 q = new CircularQueueImpl2(5);
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
