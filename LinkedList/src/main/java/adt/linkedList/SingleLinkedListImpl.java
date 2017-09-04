package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int length = 0;
		if(isEmpty()){ return length; }
		
		SingleLinkedListNode<T> auxNode = this.getHead();
		
		while(!auxNode.isNIL() && auxNode.getNext() != null){
			length ++;
			auxNode = auxNode.getNext();
		}
		
		return length;
		
	}

	@Override
	public T search(T element) {
		T result = null;
		if(element == null || isEmpty()){ return result; }
		
		SingleLinkedListNode<T> auxNode = this.getHead();
		
		while(!auxNode.isNIL() && auxNode.getNext() != null && !auxNode.getData().equals(element)){
			auxNode = auxNode.getNext();
		}
		
		if(!auxNode.isNIL() && auxNode.getData().equals(element)){
			result = auxNode.getData();
		}
		
		return result;
		
	}

	@Override
	public void insert(T element) {
		if(element == null){ return; }
		SingleLinkedListNode<T> newNode = this.getHead();
		
		while(!newNode.isNIL() && newNode.getNext() != null){
			newNode = newNode.getNext();
		}
		
		newNode.setData(element);
		newNode.setNext(new SingleLinkedListNode<>());
	}

	@Override
	public void remove(T element) {
		if(element == null && this.isEmpty()){ return; }
		
		if(this.getHead().getData().equals(element)){
			
			if(this.getHead().getNext() == null){ this.setHead(new SingleLinkedListNode<>()); }
			
			else { this.setHead(this.getHead().getNext()); }
			
		}
		
		SingleLinkedListNode<T> previous = new SingleLinkedListNode<>();
		SingleLinkedListNode<T> auxNode = this.getHead();
		
		while(!auxNode.isNIL() && auxNode.getNext() != null && !auxNode.getData().equals(element)){
			previous = auxNode;
			auxNode = auxNode.getNext();
		}
		
		if(!auxNode.isNIL() && auxNode.getData().equals(element)){
			previous.setNext(auxNode.getNext());
		}
		
	}

	@Override
	public T[] toArray() {
		int size = this.size();
		T[] array = (T[]) new Object[size];
		int index = 0;
		SingleLinkedListNode<T> auxNode = this.getHead();
		
		while(auxNode.getNext() != null && !auxNode.isNIL()){
			array[index] = auxNode.getData();
			index++;
			auxNode = auxNode.getNext();
		}
		
		return array;
	}
	/*
	 * Pense em um método de uma lista que retorna o índice da primeira ocorrência de um
elemento na lista. O índice varia entre 0 e o tamanho da lista -1. Caso o elemento não esteja
na lista ele deve retornar -1. */
	public int indexOf(T element) {
		int index = -1;
		if (isEmpty() || element == null) {
			index = -1;
		} else {
			SingleLinkedListNode<T> auxNode = this.getHead();
			while (!auxNode.isNIL() && auxNode.getNext() != null && !auxNode.getData().equals(element)) {
				index++;
				auxNode = auxNode.getNext();
			}
			
			if (!auxNode.isNIL() && auxNode.getNext() != null && auxNode.getData().equals(element)) {
				index++;
			}
			else{
				index = -1;
			}

		}
		return index;
	}
	
	public int lastIndexOf(T element){
		int index = 0;
		int lastOccurrence = 0;
		boolean found = false;
		if(isEmpty() || element == null){ index = -1;}
		else{
			SingleLinkedListNode<T> auxNode = this.getHead();
			 while(!auxNode.isNIL() && auxNode.getNext() != null){
				 if(auxNode.getData().equals(element)){
					lastOccurrence = index; 
					found = true;
				 }
				 index ++;
				 auxNode = auxNode.getNext();
			 }
		}
		if(lastOccurrence > 0){ index = lastOccurrence; }
		if(found == false){ lastOccurrence = -1;}
		return lastOccurrence;
	}
	
	public void reverseLinked(){
		SingleLinkedListNode<T> headNode = this.getHead();
		reverseLinked(headNode);
	}
	
	private void reverseLinked(SingleLinkedListNode<T> head){
		
		SingleLinkedListNode<T> result = new SingleLinkedListNode<>();
		SingleLinkedListNode<T> current = head;
		SingleLinkedListNode<T> nextNode = new SingleLinkedListNode<>();
		
		while(!current.isNIL() && current.getNext() != null){
			nextNode = current.getNext();
			current.setNext(result);
			result = current;
			current = nextNode;
		}
		setHead(result);
		
		printList(result);
		
	}
	
	private void printList(SingleLinkedListNode<T> head){
		SingleLinkedListNode<T> auxNode = head;
		while(!auxNode.isNIL() && auxNode.getNext() != null){
			System.out.println(auxNode.getData());
			auxNode = auxNode.getNext();
		}
	}
	
	public T nthOrder(int n){
		return nthOrder(getHead(), n);
	}
	private T nthOrder(SingleLinkedListNode<T> node, int k) {
		if(node.isNIL() ||  k < 0){return null;}
		SingleLinkedListNode<T> current = this.getHead();
		SingleLinkedListNode<T> auxnode = this.getHead();
		
		for (int i = 0; i < k; i++) {
			if(auxnode.isNIL()){
				return null;
			}
			auxnode = auxnode.getNext();
		}
		
		while(!auxnode.isNIL() && auxnode.getNext() != null){
			current = current.getNext();
			auxnode = auxnode.getNext();
		}
		return current.getData();
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
