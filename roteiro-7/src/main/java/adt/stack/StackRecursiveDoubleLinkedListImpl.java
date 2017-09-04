package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;
import adt.linkedList.RecursiveSingleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		if (size < 0) {
			size = 0;
		}
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		}
		if (element != null) {
			top.insertFirst(element);
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty()){
			throw new StackUnderflowException();
		}
		T element = ((RecursiveSingleLinkedListImpl<T>) this.top).getData();
		top.removeFirst();
		return element;
	}

	@Override
	public T top() {
		return ((RecursiveSingleLinkedListImpl<T>) this.top).getData();
	}

	@Override
	public boolean isEmpty() {
		return this.top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.top.size() == this.size;
	}

}
