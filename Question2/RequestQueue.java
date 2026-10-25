public class RequestQueue {
    private char[] queueArray;
    private int front;
    private int rear;
    private int size;

    public RequestQueue(int capacity) {
        queueArray = new char[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }
}