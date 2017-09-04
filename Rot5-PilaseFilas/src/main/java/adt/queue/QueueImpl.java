package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int SIZE;
	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		if(size < 0){
			size = 0;
		}
		array = (T[]) new Object[size];
		tail = -1;
		SIZE = size;
	}

	@Override
	public T head() {
		T element = null;
		if(!isEmpty()){
			element = this.array[0];
		}
		return element;
	}

	@Override
	public boolean isEmpty() {
		return this.tail == -1;
	}

	@Override
	public boolean isFull() {
		return this.tail == SIZE - 1;
	}

	private void shiftLeft() {
		for (int i = 0; i < tail; i++) {
			array[i] = array[i + 1];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(isFull()){
			throw new QueueOverflowException();
		}
		if(element != null){
			this.tail ++;
			this.array[this.tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(isEmpty()){
			throw new QueueUnderflowException();
		}
		T element = this.array[0];
		shiftLeft();
		this.tail --;
		return element;
	}

}
