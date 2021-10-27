package tadArvoreBinaria.interfaces;

import tadArvoreBinaria.exceptions.BoundaryViolationException;
import tadArvoreBinaria.exceptions.InvalidPositionException;

/**
 * @author Geovane Donizete Laera  - RA: 1902679 
 * @author Isaque Ribeiro dos Santos Junior - RA: 1903978
 * @author Marcelo Martinez Mesa Campos - RA: 1905076 
 * @author Paulo Ricardo Costa da Silva - RA: 1905013 
 * @author Vinícius da Cruz Pera - RA: 1903144
 * Data: 13/10/2021
 *
 */
 public interface BinaryTree<E> extends Tree<E> {
	// Retorna o filho da esquerda do nodo.
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException;

	// Retorna o filho da direita do nodo.
	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException;

	// Retorna se o nodo tem filho da esquerda.
	public boolean hasLeft(Position<E> v) throws InvalidPositionException;

	// Retorna se o nodo tem filho da direita.
	public boolean hasRight(Position<E> v) throws InvalidPositionException;
 }
