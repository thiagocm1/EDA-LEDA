package adt.heap;

import java.util.Comparator;

import util.Util;

public class StackHeap<T> extends HeapImpl {

	public StackHeap() {
		super((o1,o2) -> ((Comparable) o2).compareTo(o1)); 
		// TODO Auto-generated constructor stub
	}
	
	
	public T top(){
		T result;
		if(isEmpty()){
			result = null;
		}
		else{
			result = (T) rootElement();
		}
		return result;
	}
	
	
	public void push(T element){
		if(element == null){return;}
		if(isEmpty()){
			super.insert((Comparable) element);
		}
		else{
			super.insert((Comparable) element);
			Util.swap(getHeap(), this.index, 0);
		}
	}
	
	
	public T pop(){
		T result;
		if(isEmpty()){
			result = null;
		}
		result = (T) extractRootElement();
		int lastIndex = size() - index;
		Util.swap(getHeap(), lastIndex, 0);
		return result;
	}
}
