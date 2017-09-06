package adt.hashtable.closed;

import util.Util;

import java.util.LinkedList;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;

public class HashtableClosedAddressImpl<T> extends
		AbstractHashtableClosedAddress<T> {

	private static final int NOT_INITIALIZED = -1;

	/**
	 * A hash table with closed address works with a hash function with closed
	 * address. Such a function can follow one of these methods: DIVISION or
	 * MULTIPLICATION. In the DIVISION method, it is useful to change the size
	 * of the table to an integer that is prime. This can be achieved by
	 * producing such a prime number that is bigger and close to the desired
	 * size.
	 * 
	 * For doing that, you have auxiliary methods: Util.isPrime and
	 * getPrimeAbove as documented bellow.
	 * 
	 * The length of the internal table must be the immediate prime number
	 * greater than the given size. For example, if size=10 then the length must
	 * be 11. If size=20, the length must be 23. You must implement this idea in
	 * the auxiliary method getPrimeAbove(int size) and use it.
	 * 
	 * @param desiredSize
	 * @param method
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize,
			HashFunctionClosedAddressMethod method) {
		int realSize = desiredSize;

		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			realSize = this.getPrimeAbove(desiredSize); // real size must the
														// the immediate prime
														// above
		}
		initiateInternalTable(realSize);
		HashFunction function = HashFunctionFactory.createHashFunction(method,
				realSize);
		this.hashFunction = function;
	}

	// AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given
	 * number. You can use the method Util.isPrime to check if a number is
	 * prime.
	 */
	int getPrimeAbove(int number) {
		int isPrime = number;
		
		if(isPrime % 2 == 0){ isPrime ++;}
		
		while(!Util.isPrime(isPrime)){ isPrime += 2; }
		
		return isPrime;
	}

	@Override
	public void insert(T element) {
		if(element == null){ return; }
		
		int indexCell = getHashFunction().hash(element);
		
		boolean containsElement = searchElementPostion(element) != NOT_INITIALIZED; 
		
		boolean existElements = !(getCell(indexCell).isEmpty());
		
		if(!containsElement){
			if(existElements){ COLLISIONS++; }
			getCell(indexCell).add(element);
			elements++;
		}
		
	}

	@Override
	public void remove(T element) {
		if(element == null && this.isEmpty()){ return ;}
		int indexCell = getHashFunction().hash(element);
		int positionElement = searchElementPostion(element);
		int size = getCell(indexCell).size();
		
		if(positionElement != NOT_INITIALIZED){
			
			if(size > 1){ COLLISIONS --; }
			
			getCell(indexCell).remove(positionElement);
			
			elements--;
		}
	}

	@Override
	public T search(T element) {
		T result = null;
		if(element != null && indexOf(element) != NOT_INITIALIZED){
			int indexCell = getHashFunction().hash(element);
			int position = 	searchElementPostion(element);
			result = getCell(indexCell).get(position);
		}
		return result;
	}

	@Override
	public int indexOf(T element) {
		int indexCell = NOT_INITIALIZED;
		
		if(element != null && !this.isEmpty()){
			
			int positionElement = getHashFunction().hash(element);
			
			if(searchElementPostion(element) != NOT_INITIALIZED){ indexCell = positionElement;}
			
		}
		
		return indexCell;
	}
	
	private int searchElementPostion(T element) {
		int indexCell = getHashFunction().hash(element);
		LinkedList<T> table = getCell(indexCell);
		
		int elementHash = element.hashCode();
		int elementIndex = 0;
		
		for (T t : table) {
			int pointer = t.hashCode();
			
			if(pointer == elementHash){ break;}
			elementIndex++;
		}
		if(elementIndex == table.size()){elementIndex = NOT_INITIALIZED;}
		
		return elementIndex;
	}
	
	@Override
	public HashFunctionClosedAddress<T> getHashFunction() {
		return (HashFunctionClosedAddress<T>) super.getHashFunction();
	}

	@SuppressWarnings("unchecked")
	private LinkedList<T> getCell(int index) {
		if (super.table[index] == null)
			super.table[index] = new LinkedList<T>();
		return (LinkedList<T>) super.table[index];
	}
	
}
