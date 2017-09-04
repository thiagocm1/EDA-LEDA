package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	/*This method insert in last position*/
	
	
	@Override
	public void insert(T element){
		if(element == null) { return; }
		if(isEmpty()){ insertFirst(element); }
		else{
			DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<>(), this.getLast());
			this.getLast().setNext(newLast);
			setLast(newLast);
		}
	}
	
	
	@Override
	public void remove(T element){
		if(element == null){ return; }
		if( isEmpty() ){ removeFirst();}
		else{
			DoubleLinkedListNode<T> dumbNode = (DoubleLinkedListNode<T>) this.getHead(); /*This is a dumb node, useless */
			while(!dumbNode.isNIL() && dumbNode.getNext() != null && !dumbNode.getData().equals(element)){
				dumbNode = (DoubleLinkedListNode<T>) dumbNode.getNext();
			} 
			if(!dumbNode.isNIL() && dumbNode.getData().equals(element)){
				dumbNode.getPrevious().setNext(dumbNode.getNext());
				((DoubleLinkedListNode<T>) dumbNode.getNext()).setPrevious(dumbNode.getPrevious());
				if(dumbNode.getNext().isNIL()){
					setLast(dumbNode.getPrevious());
				}
			}
			
		}
		
	}
	
	
	/*Esse método insere o elemento no inicio da linked list*/
	@Override
	public void insertFirst(T element) {
		if(element == null){ return; }
		
		if(isEmpty()){/*Se a lista estiver vazia, ou seja sem ngm - forever alone 
		o 1 elemento vai ser a  propria cabeca da lista e o proprio tail*/
			DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<>(), new DoubleLinkedListNode<>());
			this.setHead(newHead);
			this.setLast(newHead);
		}
		else{
			/*Caso a lista n estiver vazia, add o newHead.
			 * MAAAAAAs lembre de setar o previous da antiga cabeca pro newHead*/
			
			DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, (DoubleLinkedListNode<T>) this.getHead(), new DoubleLinkedListNode<>());
			((DoubleLinkedListNode<T>) this.getHead()).setPrevious(newHead);
			this.setHead(newHead);	
		}
	}
	
	/*Remove o primeiro elemtno do inicio da linkedList*/
	@Override
	public void removeFirst() {
		
		if(isEmpty()){/*Caso mais simples, se n tiver ngm*/
			this.setHead(new DoubleLinkedListNode<>());
		} 
		else{/*Verifica se a cabeca existe + alguem, ou seja size > 1.
		 	Seta o proximo elemento da cabeca, pra ser a cabeca e o seus previous ser um nilNode, ou nó vazio*/
			if(!this.getHead().isNIL() && this.getHead().getNext() != null){
				this.setHead(this.getHead().getNext());
				((DoubleLinkedListNode<T>) this.getHead()).setPrevious(new DoubleLinkedListNode<>());
			}
		}
		
		
	}
	
	@Override
	public void removeLast() {
		if(isEmpty()){ this.setLast(new DoubleLinkedListNode<>()); }
		else{
			this.getLast().getPrevious().setNext(new DoubleLinkedListNode<>());
			if(this.size() == 1){
				this.setHead(getLast().getPrevious());
			}
			this.setLast(getLast().getPrevious());
		}
		
		
	}
	
	
	
	public DoubleLinkedListNode<T> getLast() {
		return last;
	}
	
	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
/*
	@Override
	public void insert(T element){
		if(element == null){ return; }
		if(isEmpty()){ insertFirst(element); }
		
		else{
			
			DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<>(), this.getLast());
			getLast().setNext(newLast);
			setLast(newLast);
			
		}
	}

	@Override
	public void remove(T element) {
		if(element == null){ return; }
		
		if(isEmpty()){ removeFirst(); }
		
		else{
			DoubleLinkedListNode<T> auxNode = (DoubleLinkedListNode<T>) this.getHead();
			
			while(!auxNode.isNIL() && auxNode.getNext() != null && !auxNode.getData().equals(element)){
				auxNode = (DoubleLinkedListNode<T>) auxNode.getNext();
			}
			
			if(!auxNode.isNIL() && auxNode.getData().equals(element)){
					auxNode.getPrevious().setNext(auxNode.getNext());
					((DoubleLinkedListNode<T>) auxNode.getNext()).setPrevious(auxNode.getPrevious());
					if(auxNode.getNext().isNIL()){
						setLast(auxNode.getPrevious());
					}
			} 
		}
	}
	
	
	@Override
	public void insertFirst(T element) {
		if(element == null){ return; }
		
		if(isEmpty()){
			DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<>(), new DoubleLinkedListNode<>());
			this.setHead(newHead);
			this.setLast(newHead);
		}
		else {
			DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, (DoubleLinkedListNode<T>) this.getHead(), new DoubleLinkedListNode<>());
			((DoubleLinkedListNode<T>) this.getHead()).setPrevious(newHead);
			this.setHead(newHead);
		}
		
	}

	@Override
	public void removeFirst() {
		if(!this.getHead().isNIL()){
			if(this.getHead().getNext() != null){
				this.setHead(this.getHead().getNext());
				((DoubleLinkedListNode<T>) this.getHead()).setPrevious(new DoubleLinkedListNode<>());
			}
			else{
				this.setHead(new DoubleLinkedListNode<>());
			}
		}
		
	}

	@Override
	public void removeLast() {
		if(isEmpty()) { return; }
		
		getLast().getPrevious().setNext(new DoubleLinkedListNode<>());
		if(this.size() == 1){
			setHead(getLast().getPrevious());
		}
		setLast(getLast().getPrevious());
	}
	
	*/


}
