package adt.stack;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentStackTest {

	public Stack<Integer> stack1;
	public Stack<Integer> stack2;
	public Stack<Integer> stack3;

	@Before
	public void setUp() throws StackOverflowException{
		stack1 = new StackRecursiveDoubleLinkedListImpl<Integer>(6);
		stack1.push(1);
		stack1.push(2);
		stack1.push(3);
		
		stack2 = new StackRecursiveDoubleLinkedListImpl<Integer>(2);
		stack2.push(1);
		stack2.push(2);
		
		stack3 = new StackRecursiveDoubleLinkedListImpl<Integer>(6);
	}
	
	//MÉTODOS DE TESTE
	@Test
	public void testTop() {
		Assert.assertEquals(3, stack1.top().intValue());
		Assert.assertEquals(2, stack2.top().intValue());
	    Assert.assertEquals(null, stack3.top());
	}

	@Test
	public void testIsEmpty() {
		Assert.assertFalse(stack1.isEmpty());
		Assert.assertFalse(stack2.isEmpty());
		Assert.assertTrue(stack3.isEmpty());
	}

	@Test
	public void testIsFull() {
		Assert.assertFalse(stack1.isFull());
		Assert.assertTrue(stack2.isFull());
		Assert.assertFalse(stack3.isFull());
	}

	@Test
	public void testPush() {
		try{
			stack1.push(5);
			Assert.assertEquals(5, stack1.top().intValue());
			stack3.push(6);
			Assert.assertEquals(6, stack3.top().intValue());
			stack3.push(58);
			Assert.assertEquals(58, stack3.top().intValue());
			stack3.push(58);
			Assert.assertEquals(58, stack3.top().intValue());
			stack1.push(69);
			Assert.assertEquals(69, stack1.top().intValue());
			stack1.push(8);
			Assert.assertEquals(8, stack1.top().intValue());
		}catch (Exception e){
			Assert.fail();
			
		}
	}
	
	@Test(expected=StackOverflowException.class)
	public void testPushComErro() throws StackOverflowException {
		stack2.push(695899);
	}

	@Test
	public void testPop() {
		try{
			Assert.assertEquals(3, stack1.pop().intValue());
			Assert.assertEquals(2, stack2.pop().intValue());
		}catch(Exception e){
			Assert.fail();
		}
	}
	
	@Test(expected=StackUnderflowException.class)
	public void testPopComErro() throws StackUnderflowException {
		stack3.pop();
	}
}