
public class Fila {

	private int[] array;
	private int tail;
	private int SIZE;
	
	public Fila(int capacidade){
		if(capacidade < 0){
			capacidade = 0;
		}
		array = new int[capacidade];
		this.tail = -1;
		SIZE = capacidade;
	}
	
	public boolean isEmpty(){
		return this.tail == -1;
	}
	
	public int head(){
		return this.array[0];
	}
	
	public void enqueue(int element){
		if(isFull()){
			throw new RuntimeException("fila ta cheiaa");
		}
		this.tail ++;
		this.array[tail] = element;
	}
	
	public int dequeue(){
		if(isEmpty()){
			throw new RuntimeException("ta vazio fiao");
		}
		int element = this.array[0];
		shiftLeft();
		tail --;
		return element;
		
	}
	private void shiftLeft() {
		for (int i = 0; i < this.tail; i++) {
			this.array[i] = this.array[i + 1];
		}
		
	}

	public boolean isFull() {
		return this.tail == SIZE - 1;
	}
	
}
