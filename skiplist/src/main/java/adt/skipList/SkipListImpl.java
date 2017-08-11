package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}


	@Override
	public void insert(int key, T newValue, int height) {
		if(newValue == null){return;}
		SkipListNode[] update = new SkipListNode[height];
		SkipListNode<T> auxNode = this.root;
		
		for (int i = height - 1; i >= 0; i--) {
			while(auxNode.getForward(i) != null && auxNode.getForward(i).getKey() < key){
				auxNode = auxNode.getForward(i);
			}
			update[i] = auxNode;
		}
		auxNode = auxNode.getForward(0);
		if(auxNode.getKey() == key){
			auxNode.setValue(newValue);
		}
		else{
		
			auxNode = new SkipListNode<T>(key, height, newValue);
			for (int i = 0; i < update.length; i++) {
				if(update[i].getForward(i) == null){
					auxNode.getForward()[i] = NIL;
				}
				else{
					auxNode.getForward()[i] = update[i].getForward(i);
				}
				update[i].getForward()[i] = auxNode;
			}
		}
		
 	}
	private int randomLevel(){
		int randomLevel = 1;
		double random = Math.random();
		while(random < PROBABILITY && randomLevel < maxHeight){
			randomLevel += 1;
		}
		return randomLevel;
	}
	@Override
	public void remove(int key) {
		SkipListNode[] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> auxNode = this.root;
		for (int i = maxHeight - 1; i >= 0; i--) {
			while(auxNode.getForward(i) != null && auxNode.getForward(i).getKey() < key){
				auxNode = auxNode.getForward(i);
			}
			update[i] = auxNode;
		}
		auxNode = auxNode.getForward(0);
		if(auxNode.getKey() == key){
			for (int i = 0; i < update.length; i++) {
				if(update[i].forward[i] != auxNode){
					break;
				}
				else{
					update[i].getForward()[i] = auxNode.getForward(i);
				}
			}
		}
	}

	@Override
	public int height() {
		int height = this.maxHeight - 1;
		while(height >= 0 && root.getForward(height) == NIL){
			if(height == 0){
				height --;
				break;
			}
			else{
				height --;
			}
		}
		return height + 1;
		
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> auxNode = this.root;
		for (int i = height() - 1; i >= 0; i--) {
			while(auxNode.getForward(i) != null && auxNode.getForward(i).getKey() <= key){
				auxNode = auxNode.getForward(i);
			}
		}

		if(auxNode.getKey() == key){
			return auxNode;
		}
		else{
			return null;
		}
		
	}

	@Override
	public int size() {
		int size = 0;
		SkipListNode<T> auxNode = root.getForward(0);
		while(auxNode.getKey() != NIL.getKey()){
			auxNode = auxNode.getForward(0);
			size++;
		}
		return size;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode<T> aux = root;

		SkipListNode<T>[] array = new SkipListNode[this.size() + 2];

		int i = 0;
		while (aux != NIL) {
			array[i] = aux;
			aux = aux.forward[0];
			i++;
		}
		array[i] = aux;
		return array;
	}
	
		@SuppressWarnings("unchecked")
	public SkipListNode<T>[] nodesPerLevel(int height){
		SkipListNode<T> node = this.root;
		
		int index;
		for(index = height   ; index >= 0 && (node.getForward(index) == null|| node.forward[index].getKey() < NIL.getKey()); index--){
			;
		}
		SkipListNode[] array = new SkipListNode[size()];
		
		nodesPerlevel(node, array, index);
		return array;
		
	}
	
	private int nodesPerlevel(SkipListNode<T> node, SkipListNode<T>[] array, int index) {
		if(node.getForward(index) != null){
			index = nodesPerlevel(node.getForward()[index],array, index);
			array[index++] = node;
		}
		return index;
	}
		/*
		int index;
		int count = 0;
		for ( index = maxHeight - height ; index > 0 ; index--) {
			count ++;
		}
		SkipListNode[] array = new SkipListNode[count];
		root = root.forward[maxHeight - height];
		nodesPerlevel(root, array, index);
		return array;
	}
	
		private int nodesPerlevel(SkipListNode<T> node, SkipListNode<T>[] array, int index) {
			if(node.getForward(index) != null){
				index = nodesPerlevel(node.getForward(index),array, index);
				array[index++] = node;
			
			}
			return index;
		}
		
	*/
	

		/*
		SkipListNode<T> auxNode = this.root;
		SkipListNode[] array;
		int count= 0;
		for (int i = height -1 ; i > 0 ; i--) {
			if(auxNode.getForward(i) != null){
				auxNode = auxNode.getForward(i);
				count ++;
				
			}
		}
		
		array = new SkipListNode[this.size()];
		nodesPerlevel(auxNode, array, 0);
		return array;
	
	}
	private int nodesPerlevel(SkipListNode<T> node, SkipListNode<T>[] array, int index) {
		if(node.getForward(index) != null){
			index = nodesPerlevel(node.getForward()[index], array, index);
			array[index] = node;
			index++;
		}
		return index;
	}
	
	*/
	

	/** maybe right
	public SkipListNode<T>[] nodesLevel(int height){
		SkipListNode<T> auxNode = this.root;
		SkipListNode<T>[] array;
		int count = 0;
		for (int i = maxHeight - 1; i >= 0; i--) {
			while(auxNode.getForward(i) != null){
				auxNode = auxNode.getForward(i);
				count++;
			}
		}
		array = new SkipListNode[count];
		
		for (int i = 0; i < array.length; i++) {
			array[i] = auxNode;
			auxNode = auxNode.forward[i];
		}
		return array;
	}
	*/
}
