package tadArvoreBinaria.interfaces;

import tadArvoreBinaria.exceptions.EmptyStackException;

/**
 * @author Geovane Donizete Laera  - RA: 1902679 
 * @author Isaque Ribeiro dos Santos Junior - RA: 1903978
 * @author Marcelo Martinez Mesa Campos - RA: 1905076 
 * @author Paulo Ricardo Costa da Silva - RA: 1905013 
 * @author Vinícius da Cruz Pera - RA: 1903144
 * Data: 13/10/2021
 *
 */
public interface Stack<E> {
	// Retorna o número de elementos na pilha. 
	public int size(); 

	// @return True se a pilha é vazia, False em caso contrário. 
	public boolean isEmpty();

	// @return o elemento que está no topo da pilha.
	// @exception EmptyStackException se a pilha estiver vazia.
	public E top() throws EmptyStackException;

	// @param elemento a ser inserido.
	public void push(E element); 

	// @return elemento removido. 
	// @exception EmptyStackException se a pilha estiver vazia.
	public E pop() throws EmptyStackException; 
}
