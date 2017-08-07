import static org.junit.Assert.*;

import org.junit.Test;

public class FilaTest {
	Fila fila = new Fila(3);
	
	@Test
	public void testIsEmpty() {
		assertTrue(fila.isEmpty());
		fila.enqueue(3);
		fila.enqueue(6);
		assertFalse(fila.isEmpty());
		fila.dequeue();
		fila.dequeue();
		assertFalse(fila.isFull());
		assertTrue(fila.isEmpty());
	}
	
	@Test
	public void testEnqueue(){
		assertTrue(fila.isEmpty());
		fila.enqueue(3);
		assertEquals(3, fila.head());
		assertFalse(fila.isFull());
		fila.enqueue(4);
		fila.enqueue(-5);
		assertNotEquals(-5, fila.head());
		assertTrue(fila.isFull());
	}

}
