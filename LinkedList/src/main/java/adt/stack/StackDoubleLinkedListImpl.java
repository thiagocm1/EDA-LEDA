package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	if(size < 0){ size = 0;}
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(isFull()){
			throw new StackOverflowException();
		}
		this.top.insert(element);
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty()){
			throw new StackUnderflowException();
		}
		T element = this.top();
		this.top.remove(element);
		return element;
	}

	@Override
	public T top() {
		T topElement = null;
		if(!isEmpty()){
			 topElement = ((DoubleLinkedListImpl<T>) this.top).getLast().getData();
		}
		 return topElement;
	}

	@Override
	public boolean isEmpty() {
		return this.top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.top.size() == size;
	}

}
