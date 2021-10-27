package tadArvoreBinaria.source;

import tadArvoreBinaria.exceptions.InvalidPositionException;
import tadArvoreBinaria.interfaces.Position;

/**
 * @author Geovane Donizete Laera  - RA: 1902679 
 * @author Isaque Ribeiro dos Santos Junior - RA: 1903978
 * @author Marcelo Martinez Mesa Campos - RA: 1905076 
 * @author Paulo Ricardo Costa da Silva - RA: 1905013 
 * @author Vinícius da Cruz Pera - RA: 1903144
 * Data: 13/10/2021
 *
 */
public class DNode<E> implements Position<E> {
	// Referência para os nodos anterior e posterior
	private DNode<E> prev, next;
	
	// Elemento armazenado nesta posição
	private E element;

	// Método construtor
	public DNode(DNode<E> newPrev, DNode<E> newNext, E elem) {
		prev = newPrev;
		next = newNext;
		element = elem;
	}

	// Método da interface Position
	public E element() throws InvalidPositionException {
		if ((prev == null) && (next == null)) {
			throw new InvalidPositionException("Position is not in a list!");
		}
		
		return element;
	}

	// Métodos getters
	public DNode<E> getNext() { 
		return next; 
	}
	
	public DNode<E> getPrev() { 
		return prev; 
	}

	// Métodos setters
	public void setNext(DNode<E> newNext) { 
		next = newNext; 
	}
	
	public void setPrev(DNode<E> newPrev) { 
		prev = newPrev; 
	}
	
	public void setElement(E newElement) { 
		element = newElement; 
	}
}
