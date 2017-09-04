package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

	
	public SetLinkedListImpl() {
		super();
	}
	
	@Override
	public void removeDuplicates() {
		SingleLinkedListNode<T> auxNode = this.getHead();
		
		while(!auxNode.isNIL() && auxNode.getNext() != null){
			SingleLinkedListNode<T> tempNode = auxNode.getNext();
			
			while(!tempNode.isNIL() && tempNode.getNext() != null){
				if(auxNode.getData().equals(tempNode.getData())){
					SingleLinkedListNode<T> removeNode = tempNode.getNext();
					tempNode.setNext(tempNode.getNext().getNext());
				}
				else{
					tempNode = tempNode.getNext();
				}
			}
			auxNode = auxNode.getNext();
		}

	}

	@Override
	public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
		if(otherSet.isEmpty()){return null;}
		
		SingleLinkedListNode<T> auxNode = this.getHead();
		
		while(!auxNode.isNIL() && auxNode.getNext() != null){
			otherSet.insert(auxNode.getData());
			auxNode = auxNode.getNext();
		}
		otherSet.removeDuplicates();
		return otherSet;
	
	}

	@Override
	public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
		if (otherSet.isEmpty()) {
			return null;
		}
		SetLinkedListImpl<T> out = new SetLinkedListImpl<>();
		SetLinkedListImpl<T> other = (SetLinkedListImpl<T>) otherSet;
		other.removeDuplicates();
		this.removeDuplicates();

		SingleLinkedListNode<T> auxNode = this.getHead();
		while (!auxNode.isNIL() && auxNode.getNext() != null) {
			SingleLinkedListNode<T> tempNode = auxNode.getNext();

			while (!tempNode.isNIL() && tempNode.getNext() != null) {
				if (auxNode.getData().equals(tempNode.getData())) {
					out.insert(auxNode.getData());
				}
				tempNode = tempNode.getNext();
			}
			auxNode = auxNode.getNext();
		}
		out.removeDuplicates();
		return out;
	}

	@Override
	public void concatenate(SetLinkedList<T> otherSet) {
		if(otherSet.isEmpty()){return;}
		SingleLinkedListImpl<T> auxNode = (SingleLinkedListImpl<T>) otherSet;
		SingleLinkedListNode<T> tempNode = this.getHead();
		
		while(!tempNode.isNIL() && tempNode.getNext() != null){
			tempNode = tempNode.getNext();
		}
		tempNode.setData(auxNode.getHead().getData());
		tempNode.setNext(auxNode.getHead().getNext());
		
		this.removeDuplicates();
	}

}
