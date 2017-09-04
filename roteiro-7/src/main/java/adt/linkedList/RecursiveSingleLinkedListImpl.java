package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		if(isEmpty()){ return 0; }
		
		return 1  + this.getNext().size();
	}

	@Override
	public T search(T element) {
		if(element == null || isEmpty()) {return null;}
		 
		if(this.getData().equals(element)){
			return this.getData();
		}
		return this.getNext().search(element);
		
	}

	@Override
	public void insert(T element) {
		if(element == null) { return; }
		
		if(isEmpty()){
			this.setData(element);
			this.setNext(new RecursiveSingleLinkedListImpl<>());
		}
		this.getNext().insert(element);
	}

	@Override
	public void remove(T element) {
		if(element == null){ return ;}
		if(this.getData().equals(element)){
			if(this.getNext().isEmpty()){
				this.setData(null);
			}
			else{
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());
			}
		}
		else{
			this.getNext().remove(element);
		}
	}

	@Override
	public T[] toArray() {
		int size = size();
		
		T[] array = (T[]) new Object[size];
		int index = 0;
		toArray(array, this,index);
		return array;
	}



	private void toArray(T[] array, RecursiveSingleLinkedListImpl<T> node, int index) {
		if(node.isEmpty()) { return;}
		
		array[index] = node.getData();
		index ++;
		toArray(array, node.getNext(), index);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
