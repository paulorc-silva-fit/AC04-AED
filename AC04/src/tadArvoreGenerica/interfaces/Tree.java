package tadArvoreGenerica.interfaces;

import java.util.Iterator;

import tadArvoreGenerica.exceptions.BoundaryViolationException;
import tadArvoreGenerica.exceptions.EmptyTreeException;
import tadArvoreGenerica.exceptions.InvalidPositionException;

/**
 * @author Geovane Donizete Laera  - RA: 1902679 
 * @author Isaque Ribeiro dos Santos Junior - RA: 1903978
 * @author Marcelo Martinez Mesa Campos - RA: 1905076 
 * @author Paulo Ricardo Costa da Silva - RA: 1905013 
 * @author Vinícius da Cruz Pera - RA: 1903144
 * Data: 06/10/2021
 *
 */
public interface Tree<E> extends Iterable<E> {
	// Retorna a quantidade de nodos da árvore.
	public int size();
	
	// Retorna se a árvore está vazia.
	public boolean isEmpty();
	
	// Retorna um iterador sobre os elementos armazenados na árvore.
	public Iterator<E> iterator();
	
	// Retorna uma coleção iterável dos nodos.
	public Iterable<Position<E>> positions();
	
	// Substitui o elemento armazenado em um dado nodo.
	public E replace(Position<E> v, E e) throws InvalidPositionException;
	
	// Retorna a raiz da árvore.
	public TreePosition<E> root() throws EmptyTreeException;
	
	// Retorna o pai de um dado nodo.
	public TreePosition<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException;
	
	// Retorna uma coleção iterável dos filhos de um dado nodo.
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException;
	
	// Retorna se um dado nodo é interno.
	public boolean isInternal(Position<E> v) throws InvalidPositionException;
	
	// Retorna se um dado nodo é externo.
	public boolean isExternal(Position<E> v) throws InvalidPositionException;
	
	// Retorna se um dado nodo é a raiz da árvore.
	public boolean isRoot(Position<E> v) throws InvalidPositionException;
}
