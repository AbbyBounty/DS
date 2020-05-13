
class LinearQueueImpl {
    private final int size;
    private int front, rear;
    private int[] arr;
    public LinearQueueImpl(int size) {
        this.size = size;
        this.arr = new int[size];
        this.front = -1;
        this.rear = -1;
    }
    public void push(int ele) {
        this.rear++;
        this.arr[this.rear] = ele;
    }
    public void pop() {
        this.front++;
    }
    public int peek() {
        return this.arr[this.front+1];
    }
    public boolean isEmpty() {
        return this.rear == this.front;
    }
    public boolean isFull() {
        return this.rear == size-1;
    }
}

public class MyLinearQueue {
    public static void main(String[] args) {
        LinearQueueImpl q = new LinearQueueImpl(5);
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







