package tadArvoreBinaria.source;

import tadArvoreBinaria.interfaces.BTPosition;

/**
 * @author Geovane Donizete Laera  - RA: 1902679 
 * @author Isaque Ribeiro dos Santos Junior - RA: 1903978
 * @author Marcelo Martinez Mesa Campos - RA: 1905076 
 * @author Paulo Ricardo Costa da Silva - RA: 1905013 
 * @author Vinícius da Cruz Pera - RA: 1903144
 * Data: 13/10/2021
 *
 */
public class BTNode<E> implements BTPosition<E> {
	// Elemento armazenado neste nodo
	private E element;

	// Nodos adjacentes
	private BTPosition<E> left, right, parent;

	// Método construtor
	public BTNode(E element, BTPosition<E> parent, BTPosition<E> left, BTPosition<E> right) {
		setElement(element);
		setParent(parent);
		setLeft(left);
		setRight(right);
	}

	// Retorna o elemento armazenado nesta posição
	public E element() { 
		return element; 
	}

	// Define o elemento armazenado nesta posição
	public void setElement(E o) { 
		element = o; 
	}

	// Retorna o filho da esquerda desta posição
	public BTPosition<E> getLeft() { 
		return left; 
	}

	// Define o filho da esquerda desta posição
	public void setLeft(BTPosition<E> v) { 
		left = v; 
	}

	// Retorna o filho da direita desta posição
	public BTPosition<E> getRight() { 
		return right; 
	}

	// Define o filho da direita desta posição
	public void setRight(BTPosition<E> v) { 
		right = v; 
	}

	// Retorna o pai desta posição
	public BTPosition<E> getParent() { 
		return parent; 
	}

	// Define o pai desta posição
	public void setParent(BTPosition<E> v) { 
		parent = v; 
	}
}
