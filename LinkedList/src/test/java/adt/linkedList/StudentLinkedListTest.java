package adt.linkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentLinkedListTest {

	private LinkedList<Integer> lista1;
	private LinkedList<Integer> lista2;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementaÃ§Ã£o
		lista1 = new SingleLinkedListImpl<>();
		lista2 = new SingleLinkedListImpl<>();
	}

	@Test
	public void testIsEmpty() {
		Assert.assertFalse(lista1.isEmpty());
		Assert.assertTrue(lista2.isEmpty());
	}

	@Test
	public void testSize() {
		Assert.assertEquals(3, lista1.size());
		Assert.assertEquals(0, lista2.size());
	}

	@Test
	public void testSearch() {
		Assert.assertTrue(2 == lista1.search(2));
		Assert.assertNull(lista1.search(4));
		Assert.assertFalse(3 == lista1.search(2));
	}

	@Test
	public void testInsert() {
		Assert.assertEquals(3, lista1.size());
		lista1.insert(5);
		lista1.insert(7);
		Assert.assertEquals(5, lista1.size());

		Assert.assertEquals(0, lista2.size());
		lista2.insert(4);
		lista2.insert(7);
		Assert.assertEquals(2, lista2.size());
	}
	@Test
	public void testRemove() {
		Assert.assertEquals(3, lista1.size());
		lista1.remove(2);
		lista1.remove(1);
		Assert.assertEquals(1, lista1.size());
		
	}
	
	@Test
	public void testToArray() {
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
		Assert.assertArrayEquals(new Integer[] { 3, 2, 1 }, lista1.toArray());
	}
	
	@Test
	public void testIndexOf(){
		lista1.insert(5);
		lista1.insert(4);
		lista1.insert(5);
		lista1.insert(5);
		lista1.insert(10);
		lista1.insert(5);
		Assert.assertEquals(-1, ((SingleLinkedListImpl<Integer>) lista1).indexOf(33));
		Assert.assertEquals(9, lista1.size());
		Assert.assertEquals(2, ((SingleLinkedListImpl<Integer>) lista1).indexOf(1));
		Assert.assertEquals(3, ((SingleLinkedListImpl<Integer>) lista1).indexOf(5));
		Assert.assertEquals(-1, ((SingleLinkedListImpl<Integer>) lista1).indexOf(6));
		Assert.assertEquals(3, ((SingleLinkedListImpl<Integer>) lista1).indexOf(5));
		
	}
	@Test
	public void testLastIndexOf(){
		Assert.assertEquals(0, ((SingleLinkedListImpl<Integer>) lista1).lastIndexOf(3));
		Assert.assertEquals(-1, ((SingleLinkedListImpl<Integer>) lista1).lastIndexOf(23));
		lista1.insert(3);	
		Assert.assertEquals(3, ((SingleLinkedListImpl<Integer>) lista1).lastIndexOf(3));
		lista1.insert(30);
		Assert.assertEquals(4, ((SingleLinkedListImpl<Integer>) lista1).lastIndexOf(30));
		lista1.insert(30);
		lista1.insert(30);
		Assert.assertEquals(6, ((SingleLinkedListImpl<Integer>) lista1).lastIndexOf(30));
		Assert.assertEquals(1, ((SingleLinkedListImpl<Integer>) lista1).lastIndexOf(2));
	}
	
	@Test
	public void testRemoveByIndex(){
		Assert.assertEquals(3, lista1.size());
		lista1.remove(2);
		lista1.remove(1);
		Assert.assertEquals(1, lista1.size());
	}
	/*
	@Test
	public <T> void testReverseLinked(){
		
		//lista.insert(5);
		//lista.insert(10);
		//ista.insert(33);
		((SingleLinkedListImpl<Integer>) lista1).reverseLinked();
		Assert.assertArrayEquals(new Integer[] {1,2,3}, lista1.toArray());
		
	
	}
	*/

}