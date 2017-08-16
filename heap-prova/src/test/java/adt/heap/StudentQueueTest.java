package adt.heap;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class StudentQueueTest {

	public QueueHeapWithMap<Integer> fila; 
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp(){
		fila = new QueueHeapWithMap<Integer>();
		fila.enqueue(15);
		fila.enqueue(14);
		fila.enqueue(36);
		

	}
	
	@Test
	public void dequeueTest(){	
		System.out.println(Arrays.toString(fila.heap));

		assertTrue(fila.dequeue().equals(15));
		System.out.println(Arrays.toString(fila.heap));
		System.out.println(fila.map.toString());
		assertTrue(fila.dequeue().equals(14));
		assertTrue(fila.dequeue().equals(36));
		assertNull(fila.dequeue());
		

	}

}