package tadArvoreGenerica.source;

import tadArvoreGenerica.interfaces.Position;
import tadArvoreGenerica.interfaces.PositionList;
import tadArvoreGenerica.interfaces.TreePosition;

/**
 * @author Geovane Donizete Laera  - RA: 1902679 
 * @author Isaque Ribeiro dos Santos Junior - RA: 1903978
 * @author Marcelo Martinez Mesa Campos - RA: 1905076 
 * @author Paulo Ricardo Costa da Silva - RA: 1905013 
 * @author Vinícius da Cruz Pera - RA: 1903144
 * Data: 06/10/2021
 *
 */
public class TreeNode<E> implements TreePosition<E> {
	// Elemento armazenado neste nodo.
	private E element;

	// Nodo pai
	private TreePosition<E> parent;

	// Nodos filhos
	private PositionList<Position<E>> children;

	// Método construtor default
	public TreeNode() {}
	
	// Método construtor
	public TreeNode(E element, TreePosition<E> parent, PositionList<Position<E>> children) {
		setElement(element);
		setParent(parent);
		setChildren(children);
	}
	
	// Retorna o elemento armazenado nesta posição.
	public E element() { 
		return element; 
	}
	
	// Define o elemento a ser armazenado nesta posição
	public void setElement(E o) { 
		element = o;
	}
	
	// Retorna o elemento armazenado nesta posição
	public E getElement() { 
		return element; 
	}
	
	// Retorna os filhos desta posição
	public PositionList<Position<E>> getChildren() { 
		return children; 
	}
	
	// Define os filhos desta posição
	public void setChildren(PositionList<Position<E>> c) { 
		children = c; 
	}
	
	// Retorna o pai desta posição
	public TreePosition<E> getParent() { 
		return parent; 
	}
	
	// Define o pai desta posição
	public void setParent(TreePosition<E> v) { 
		parent = v; 
	}
}
