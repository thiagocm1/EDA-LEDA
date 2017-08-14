package adt.heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class QueueHeapWithMap<T> extends HeapImpl<Integer> {
	Map<Integer, T> map;
	private int priority;
	
	public QueueHeapWithMap() {
		super((o1,o2) -> o2.compareTo(o1));
		map = new HashMap<Integer, T>();
		priority = 0;
	}
	
	public void enqueue(T element){
		if(element == null){return;}
		else{
			Integer priority = generatePriority();
			map.put(priority, element);
			super.insert(priority);
		}
	}
	
	
	public T dequeue(){
		Integer priority = super.extractRootElement();
		T result = map.get(priority);
		if(result == null){result = null;}
		else{
			map.remove(priority);
		}
		return result;
	}
	
	public T head(){
		T result;
		if(isEmpty()){
			result = null;
		}
		else{
			Integer priority = super.rootElement();
			result = map.get(priority);
		}
		return result;
		
	}
	
	private Integer generatePriority(){
		priority++;
		return priority;
	}
	
	
	public boolean isEmpty(){
		return super.isEmpty();
	}
	

	
	
}
