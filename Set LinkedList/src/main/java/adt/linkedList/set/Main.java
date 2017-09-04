package adt.linkedList.set;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		SetLinkedList<Integer> set = new SetLinkedListImpl<>();
		SetLinkedList<Integer> set2 = new SetLinkedListImpl<>();
		
		
		
		set.insert(2);
		set.insert(3);
		set.insert(4);
		set.insert(4);
		set.insert(9);
		set.insert(2);
		set.insert(3);
		set.insert(3);
		set.insert(3);
		
		set2.insert(3);
		set2.insert(13);
		set2.insert(78);
		set2.insert(3);
		set2.insert(963);
		set2.insert(3);
		set2.insert(7);
		set2.insert(10);


		SetLinkedListImpl<Integer> set4 = new SetLinkedListImpl<>();
		set4.insert(2);
		set4.insert(3);
		set4.insert(4);
		set4.insert(4);
		set4.insert(9);
		set4.insert(2);
		set4.insert(3);
		set4.insert(3);
		set4.insert(3);
		
		set4.insert(3);
		set4.insert(13);
		set4.insert(78);
		set4.insert(3);
		set4.insert(963);
		set4.insert(3);
		set4.insert(7);
		set4.insert(10);
	
		System.out.println("set2 = " + Arrays.toString(set2.toArray()));

		set2.removeDuplicates();
		System.out.println("set2 = " + Arrays.toString(set2.toArray()));
		System.out.println("set4 = " + Arrays.toString(set4.toArray()));
		set4.removeDuplicates();
		System.out.println("set4 = " + Arrays.toString(set4.toArray()));
		
		SetLinkedList<Integer> set3 = set.union(set2);
		System.out.println("set3 = " + Arrays.toString(set3.toArray()));

		
		System.out.println("set = " + Arrays.toString(set.toArray()));
		System.out.println("set2 = " + Arrays.toString(set2.toArray()));

		set.concatenate(set2);
		
		System.out.println("set = " + Arrays.toString(set.toArray()));

		SetLinkedList<Integer> s = set.intersection(set2);
		System.out.println("s = " + Arrays.toString(s.toArray()));

		
		
		
		
	}
	
}
