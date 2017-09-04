package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();

	}

	@Override
	public boolean isEmpty() {
		return this.getHead().isNIL();
	}

	@Override
	public int size() {
		int length = 0;
		if(isEmpty()){length = 0;}
		else{
			SingleLinkedListNode<T> auxNode = this.getHead();
			
			while(!auxNode.isNIL() && auxNode.getNext() != null){
				length ++;
				auxNode = auxNode.getNext();
			}
		}
		return length;
	}

	@Override
	public T search(T element) {
		T result = null;
		if(element == null || isEmpty()){ result = null;}
		else{
			SingleLinkedListNode<T> auxNode = this.getHead();
			while(!auxNode.isNIL() && auxNode.getNext() != null && !auxNode.getData().equals(element)){
				auxNode = auxNode.getNext();
			}
			if(!auxNode.isNIL() && auxNode.getNext() != null && auxNode.getData().equals(element)){
				result = auxNode.getData();
			}
		}
		return result;
	}
	@Override
	public void insert(T element) {
		if(element == null){ return;}
		if(isEmpty()){
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, new SingleLinkedListNode<>());
			this.setHead(newNode);
		}
		else{
			SingleLinkedListNode<T> auxNode = this.getHead();
			
			while(!auxNode.isNIL() && auxNode.getNext() != null){
				auxNode = auxNode.getNext();
			}
			
			auxNode.setData(element);
			auxNode.setNext(new SingleLinkedListNode<>());
		}

	}

	@Override
	public void remove(T element) {
		if (element == null || isEmpty()) {
			return;
		}
		if (this.getHead().getData().equals(element)) {

			if (this.getHead().getNext() == null) {
				this.setHead(new SingleLinkedListNode<>());
			}

			else {
				this.setHead(this.getHead().getNext());
			}

		}

		SingleLinkedListNode<T> auxNode = this.getHead();
		SingleLinkedListNode<T> previous = new SingleLinkedListNode<>();
		while (!auxNode.isNIL() && auxNode.getNext() != null && !auxNode.getData().equals(element)) {
			previous = auxNode;
			auxNode = auxNode.getNext();
		}

		if (!auxNode.isNIL() && auxNode.getNext() != null && auxNode.getData().equals(element)) {
			previous.setNext(auxNode.getNext());

		}
	}

	@Override
	public T[] toArray() {
		int size = this.size();
		T[] array = (T[]) new Object[size];
		int index = 0;
		
		SingleLinkedListNode<T> auxNode = this.getHead();
		
		while(!auxNode.isNIL() && auxNode.getNext() != null){
			array[index] = auxNode.getData();
			index++;
			auxNode = auxNode.getNext();
		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}