package adt.heap;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class StudentQueueWithAnArray {
	
	public QueueHeap<Integer> fila;
	

	@Before
	public void setUp(){
			fila = new QueueHeap<Integer>();
			
	}
	@Test
	public void testEnqueue(){
		fila.enqueue(15);
		fila.enqueue(14);
		fila.enqueue(36);
		System.out.println(fila.head());
	}
	@Test
	public void dequeueTest(){	
		System.out.println(Arrays.toString(fila.heap));
		fila.enqueue(15);
		fila.enqueue(14);
		fila.enqueue(36);
		fila.dequeue();
		fila.dequeue();
		System.out.println(fila.head());
		//System.out.println(Arrays.toString(fila.heap));
		//System.out.println(fila);
		//assertTrue(fila.dequeue().equals(14));
		//assertTrue(fila.dequeue().equals(36));
		//assertNull(fila.dequeue());
		

	}
	
}


