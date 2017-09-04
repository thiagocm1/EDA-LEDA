package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		if(!isEmpty()){
			RecursiveDoubleLinkedListImpl<T> newNode = new RecursiveDoubleLinkedListImpl<T>(this.getData(), this.getNext(), this);
			this.setData(element);
			this.setNext(newNode);
		}else{
			this.setData(element);
			this.setNext(new RecursiveDoubleLinkedListImpl<T>());
			this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
		}
	}
	@Override
	public void insert(T element){
		if (isEmpty()){
			this.setData(element);
			this.setNext(new RecursiveDoubleLinkedListImpl<T>());
			this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
		}else{
			if (this.getNext().isEmpty()){
				this.getNext().setData(element);
				this.getNext().setNext(new RecursiveDoubleLinkedListImpl<T>());
				this.getNext().setPrevious(this);
			}else{
				this.getNext().insert(element);
			}
		}
	}

	@Override
	public void removeFirst() {
		if (isEmpty()) { return; }
		if (this.getNext().isEmpty()){
			this.setData(null);
		}else{
			this.setData(this.getNext().getData());
			this.setNext(this.getNext().getNext());
		}
	}

	@Override
	public void removeLast() {
		if (isEmpty()) { return; }
		
		if (this.getNext().isEmpty()){
			getPrevious().setNext(new RecursiveDoubleLinkedListImpl<T>());
		}else{
			this.getNext().removeLast();
		}
	}
	@Override
	public void remove(T element){
		if (element == null || isEmpty()) { return; }
		
		if (this.getData().equals(element)){
			this.getPrevious().setNext(this.getNext());
			this.getNext().setPrevious(this.getPrevious());
		}else{
			this.getNext().remove(element);
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}
	
	public RecursiveDoubleLinkedListImpl<T> getNext(){
		return (RecursiveDoubleLinkedListImpl<T>) this.next;
	}

}
