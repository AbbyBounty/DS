
class StackImpl {
    private final int size;
    private int top;
    private int[] arr;
    public StackImpl(int size) {
        this.size = size;
        this.arr = new int[size];
        this.top = -1;
    }
    public void push(int ele) {
        this.top++;
        this.arr[this.top] = ele;
    }
    public void pop() {
        this.top--;
    }
    public int peek() {
        return this.arr[this.top];
    }
    public boolean isEmpty() {
        return this.top == -1;
    }
    public boolean isFull() {
        return this.top == size-1;
    }
}

public class MyStack {
    public static void main(String[] args) {
        StackImpl s = new StackImpl(5);
        s.push(10);
        s.push(20);
        s.push(30);
        s.push(40);
        while(!s.isEmpty()) {
            System.out.println("Popping: " + s.peek());
            s.pop();
        }
    }
}







