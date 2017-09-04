package adt.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentQueueTest {


	public Queue<Integer> queue1;
	public Queue<Integer> queue2;
	public Queue<Integer> queue3;

	@Before
	public void setUp() throws QueueOverflowException{
		//queue1 = new QueueImpl<Integer>(6);
		//queue1 = new CircularQueue<Integer>(6);
		queue1.enqueue(1);
		queue1.enqueue(2);
		queue1.enqueue(3);
		
		//queue2 = new QueueImpl<Integer>(2);
		//queue2 = new CircularQueue<Integer>(2);
		queue2.enqueue(1);
		queue2.enqueue(2);
		
		//queue3 = new QueueImpl<Integer>(6);
		//queue3 = new CircularQueue<Integer>(6);
	}
	
	//MÉTODOS DE TESTE
	@Test
	public void testHead() {
		Assert.assertEquals(1, queue1.head().intValue());
		Assert.assertEquals(1, queue2.head().intValue());
		Assert.assertEquals(null, queue3.head());
	}

	@Test
	public void testIsEmpty() {
		Assert.assertFalse(queue1.isEmpty());
		Assert.assertFalse(queue2.isEmpty());
		Assert.assertTrue(queue3.isEmpty());
	}

	@Test
	public void testIsFull() {
		Assert.assertFalse(queue1.isFull());
		Assert.assertTrue(queue2.isFull());
		Assert.assertFalse(queue3.isFull());
	}

	@Test
	public void testEnqueue() {
		try{
			queue3.enqueue(5);
			Assert.assertEquals(5, queue3.head().intValue());
		}catch(Exception e){
			Assert.fail();
		}
	}
	
	@Test(expected=QueueOverflowException.class)
	public void testEnqueueComErro() throws QueueOverflowException {
		queue2.enqueue(100);
	}

	@Test
	public void testDequeue() {
		try{
			Assert.assertEquals(1, queue1.dequeue().intValue());
			Assert.assertEquals(2, queue1.head().intValue());
		}catch(Exception e){
			Assert.fail();
		}
	}
	
	@Test(expected=QueueUnderflowException.class)
	public void testDequeueComErro() throws QueueUnderflowException {
		queue3.dequeue();
	}
}