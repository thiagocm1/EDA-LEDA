package adt.queue;

public class CircularQueue<T> implements Queue<T> {


	private T[] array;

	private int tail;

	private int head;

	private int elements;

	private int capacity;

	@SuppressWarnings("unchecked")
	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		capacity = size;
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull())
			throw new QueueOverflowException();

		if (element != null) {
			if (tail == -1){
		
				tail = 0;
			}
			else{
				tail = (tail + 1) % capacity;
			}
			
			array[tail] = element;
			elements++;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty())
			throw new QueueUnderflowException();
		T element = array[head];
		elements--;
		if (head == tail){
			head = -1;
		
		}
		else{
			head = (head + 1) % capacity;
		}
		return element;
	}

	@Override
	public T head() {
		T elem = null;
		if (head != -1)
			elem = array[head];
		return elem;
	}

	@Override
	public boolean isEmpty() {
		return tail == -1 && head == -1;
	}

	@Override
	public boolean isFull() {
		return elements == capacity;
	}
}