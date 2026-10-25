public class TransactionStack {
    private char[] stackArray;
    private int top;

    public TransactionStack(int capacity) {
        stackArray = new char[capacity];
        top = -1;
    }

    public void push(char c) {
        stackArray[++top] = c;
    }

    public char pop() {
        return stackArray[top--];
    }

    public char peek() {
        return stackArray[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}