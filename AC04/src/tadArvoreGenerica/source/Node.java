package tadArvoreGenerica.source;

import tadArvoreGenerica.exceptions.InvalidPositionException;
import tadArvoreGenerica.interfaces.Position;

/**
 * @author Geovane Donizete Laera  - RA: 1902679 
 * @author Isaque Ribeiro dos Santos Junior - RA: 1903978
 * @author Marcelo Martinez Mesa Campos - RA: 1905076 
 * @author Paulo Ricardo Costa da Silva - RA: 1905013 
 * @author Vinícius da Cruz Pera - RA: 1903144
 * Data: 06/10/2021
 *
 */
public class Node<E> implements Position<E>{
	// Variáveis de instância
	private E element;
	private Node<E> next, prev;

	// Cria um nodo com um dado elemento e o próximo nodo
	public Node(Node<E> newPrev, Node<E> newNext, E elem) {
		element = elem;
		next = newNext;
		prev = newPrev;
	}
	
	public E element() throws InvalidPositionException {
		if ((prev == null) && (next == null)) {
			throw new InvalidPositionException("Position is not in a list!");
		}
		
		return element;
	}

	// Métodos getters	
	public Node<E> getNext() { 
		return next; 
	}
	
	public Node<E> getPrev() {
		return prev;
	}
	
	// Métodos setters
	public void setElement(E newElem) { 
		element = newElem; 
	}
	
	public void setNext(Node<E> newNext) { 
		next = newNext;
	}
	
	public void setPrev(Node<E> newPrev) {
		prev = newPrev;
	}
}