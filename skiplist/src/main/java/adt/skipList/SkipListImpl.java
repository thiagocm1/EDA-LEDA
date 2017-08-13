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
	
		public SkipListNode<T>[] nodesPerLevel(int height){
		if(height <= 0){
			return new SkipListNode[0];
		}
		int count = 0;
		SkipListNode<T> node = this.root;
		
		for (int i = height - 1; i >= 0; i--) {
			node = node.getForward(i);
			count ++;
		}
		SkipListNode[] array = new SkipListNode[count];
		
		//ArrayList<SkipListNode<T>> array = new ArrayList<SkipListNode<T>>();
		nodesPerLevel(this.root, height - 1, array, 0);
		return array;
	}
	

	private void nodesPerLevel(SkipListNode<T> node, int height, SkipListNode[] array, int index) {
		if(node.forward.length - 1 == height && node.getValue() != null){
			array[index++] = node;
		}
		
		if(node.getForward(height).getValue() == null){
			return;
		}
		
		nodesPerLevel(node.getForward(height), height, array, index);
	}		

	public boolean greatPeformace(int height, int countNodes) {
		boolean verify = false;

		if (height >= 0) {
			verify = (nodesPerLevel(height).length == countNodes);
			if (verify) {
				greatPeformace(--height, 2 * countNodes);

			}else{
				return false;
			}
		}
		
		return verify;
	}
}
