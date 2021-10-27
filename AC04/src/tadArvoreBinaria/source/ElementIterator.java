package tadArvoreBinaria.source;

import java.util.Iterator;
import java.util.NoSuchElementException;

import tadArvoreBinaria.interfaces.Position;
import tadArvoreBinaria.interfaces.PositionList;

import java.lang.UnsupportedOperationException;

/**
 * @author Geovane Donizete Laera  - RA: 1902679 
 * @author Isaque Ribeiro dos Santos Junior - RA: 1903978
 * @author Marcelo Martinez Mesa Campos - RA: 1905076 
 * @author Paulo Ricardo Costa da Silva - RA: 1905013 
 * @author Vinícius da Cruz Pera - RA: 1903144
 * Data: 13/10/2021
 *
 */
public class ElementIterator<E> implements Iterator<E> {
	protected PositionList<E> list;
	protected Position<E> cursor;

	// Método construtor
	public ElementIterator(PositionList<E> L) {
		list = L;
		cursor = (list.isEmpty()) ? null : list.first();
	}

	// Retorna se o iterator tem ou não um próximo objeto.
	public boolean hasNext() { 
		return (cursor != null); 
	}

	// Retorna o próximo objeto do iterator.
	public E next() throws NoSuchElementException {
		if (cursor == null) {
			throw new NoSuchElementException("No next element");
		}
		
		E toReturn = cursor.element();
		cursor = (cursor == list.last()) ? null : list.next(cursor);
		return toReturn;
	}

	// Dispara um {@link UnsupportedOperationException} para todos os casos, porque
	// a remoção não é uma operação suportada por este iterator.
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("remove");
	}
}