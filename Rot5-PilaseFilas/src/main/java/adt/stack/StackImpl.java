package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;
	private int SIZE;
	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		if(size < 0){
			size = 0;
		}
		array = (T[]) new Object[size];
		top = -1;
		SIZE = size;
	}

	@Override
	public T top() {
		T element = null;
		if(!isEmpty()){
			element = this.array[this.top];
		}
		return element;
	}

	@Override
	public boolean isEmpty() {
		return this.top == -1;
	}

	@Override
	public boolean isFull() {
		return this.top == SIZE - 1;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		}
		if(element != null){
			this.top ++;
			this.array[this.top] = element;
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty()){
			throw new StackUnderflowException();
		}
		T element = this.array[top];
		this.top --;
		return element;
	}

}
