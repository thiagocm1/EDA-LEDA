package adt.linkedList.ordered;

import java.util.Comparator;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

/**
 * Para testar essa classe voce deve implementar seu comparador. Primeiro
 * implemente todos os métodos requeridos. Depois implemente dois comparadores
 * (com idéias opostas) e teste sua classe com eles. Dependendo do comparador
 * que você utilizar a lista funcionar como ascendente ou descendente, mas a
 * implemntação dos métodos é a mesma.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class OrderedSingleLinkedListImpl<T extends Comparable<T>> extends SingleLinkedListImpl<T>
		implements OrderedLinkedList<T> {

	private Comparator<T> comparator;

	public OrderedSingleLinkedListImpl(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	/**
	 * Retorna o elemento de menor valor da lista ou null se a lista form vazia.
	 * Note que, se sua lista esta sempre ordenada, o minimum sera o primeiro
	 * elemento da lista.
	 * 
	 * @return
	 */

	@Override
	public T minimum() {
		T result = null;
		if (isEmpty()) {
			result = null;
		} else {
			/*
			 * anda com o current node comparando o seu elemento com o elemento do head ou o
			 * atual menor elemento, ate achar o menor elemento.
			 */
			SingleLinkedListNode<T> current = getHead().getNext();
			result = getHead().getData();
			while (!current.isNIL() && current.getNext() != null
					&& getComparator().compare(result, current.getData()) <= 0) {
				current = current.getNext();
			}
			result = current.getData();
		}
		return result;
	}

	/**
	 * Retorna o elemento de maior valor da lista ou null se a lista form vazia.
	 * Note que, se sua lista esta sempre ordenada, o maximum sera sempre o ultimo
	 * elemento da lista.
	 * 
	 * @return
	 */
	@Override
	public T maximum() {
		T result;
		if (isEmpty()) {
			result = null;
		} else {
			/*
			 * anda com o current node comparando o seu elemento com o elemento do head ou o
			 * atual maior elemento, ate achar o maior elemento.
			 */
			SingleLinkedListNode<T> currentNode = getHead().getNext();
			result = getHead().getData();
			while (!currentNode.isNIL() && currentNode.getNext() != null
					&& getComparator().compare(result, currentNode.getData()) >= 0) {
				currentNode = currentNode.getNext();
			}
			result = currentNode.getData();
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if (element == null) {
			return;
		}
		SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, new SingleLinkedListNode<T>());
		/*
		 * Se a lista estiver vazia, seta o head com o newNode
		 */
		if (isEmpty()) {
			setHead(newNode);
		} else {
			/*
			 * comparar o elemento com o head, se for menor, newNode assume como nova head e
			 * o head vira o seu proximo no
			 */
			if (getComparator().compare(element, getHead().getData()) <= 0) {
				newNode.setNext(getHead());
				setHead(newNode);
			} else {
				/*
				 * Anda com o currentNode e previousNode ate achar um elemento que seja maior
				 * que o elemento atual e concerta os apontadores
				 */

				SingleLinkedListNode<T> currentNode = getHead().getNext();
				SingleLinkedListNode<T> previousNode = getHead();
				while (!currentNode.isNIL() && currentNode.getNext() != null
						&& getComparator().compare(element, currentNode.getData()) > 0) {
					currentNode = currentNode.getNext();
					previousNode = previousNode.getNext();
				}
				currentNode = newNode;
				previousNode.setNext(currentNode);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element == null || isEmpty()) {
			return;
		}
		/*
		 * se o head possuir o mesmo elemento quem assume o head e o proximo no ou no
		 */
		if (getHead().getData().equals(element)) {
			if (getHead().getNext() != null) {
				setHead(getHead().getNext());
			} else {
				setHead(new SingleLinkedListNode<>());
			}

		} else {
			/*
			 * anda com o previousNode e o no atual ate achar um elemento menor ou maior
			 * depois seta do no anterior ao atual para o current.next
			 * 
			 */
			SingleLinkedListNode<T> current = getHead();
			SingleLinkedListNode<T> previousNode = current;
			while (!current.isNIL() && current.getNext() != null && !current.getData().equals(element)) {
				previousNode = current;
				current = current.getNext();
			}
			if (!current.isNIL() && current.getNext() != null && current.getData().equals(element)) {
				previousNode.setNext(current.getNext());
			}
		}
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

}
