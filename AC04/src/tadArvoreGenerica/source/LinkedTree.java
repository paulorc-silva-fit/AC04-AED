package tadArvoreGenerica.source;

import java.util.Iterator;

import tadArvoreGenerica.exceptions.BoundaryViolationException;
import tadArvoreGenerica.exceptions.EmptyTreeException;
import tadArvoreGenerica.exceptions.InvalidPositionException;
import tadArvoreGenerica.exceptions.NonEmptyTreeException;
import tadArvoreGenerica.interfaces.Position;
import tadArvoreGenerica.interfaces.PositionList;
import tadArvoreGenerica.interfaces.Tree;
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
public class LinkedTree<E> implements Tree<E>  {
	// Referência para a raíz
	protected TreePosition<E> root;

	// Número de Nodos
	protected int size;

	// Cria uma árvore vazia
	public LinkedTree() {
		root = null;
		size = 0;
	}

	// Retorna um número de nodos da árvore
	public int size() {
		return size;
	}
	
	// Retorna se a árvore está vazia
	public boolean isEmpty() { 
		return (size == 0); 
	}
	
	// Retorna se um nodo é interno
	public boolean isInternal(Position<E> v) throws InvalidPositionException { 
		return !isExternal(v); 
	}
	
	// Retorna se um nodo é externo
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TreePosition<E> vv = checkPosition(v);
		return (vv.getChildren() == null) || vv.getChildren().isEmpty();
	}
	
	// Retorna se um nodo é a raíz
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		checkPosition(v);
		return (v == root());
	}
	
	// Retorna a raíz da árvore
	public TreePosition<E> root() throws EmptyTreeException {
		if (root == null) {
			throw new EmptyTreeException("The tree is empty");
		}

		return root;
	}
	
	// Retorna o pai de um nodo
	public TreePosition<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TreePosition<E> vv = checkPosition(v);
		TreePosition<E> parentPos = vv.getParent();

		if (parentPos == null) {
			throw new BoundaryViolationException("No parent");
		}

		return parentPos;
	}
	
	// Retorna uma coleção iterável dos filhos de um nodo
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TreePosition<E> vv = checkPosition(v);
		return vv.getChildren();
	}
	
	// Retorna uma coleção iterável dos nodos da árvore.
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> positions = (PositionList<Position<E>>) new NodePositionList<Position<E>>();
		
		if (size != 0) {
			preorderPositions(root(), positions);
		}

		return positions;
	}
	
	// Retorna um iterator dos elementos armazenados nos nodos
	public Iterator<E> iterator() {
		Iterable<Position<E>> positions = positions();
		PositionList<E> elements = (PositionList<E>) new NodePositionList<E>();

		for (Position<E> pos : positions) {
			elements.addLast(pos.element());
		}

		return elements.iterator();
	}
	
	// Troca o elemento de um nodo
	public E replace(Position<E> v, E o) throws InvalidPositionException {
		TreePosition<E> vv = checkPosition(v);
		E temp = v.element();
		vv.setElement(o);
		return temp;
	}

	// Adiciona um nodo raíz para uma árvore vazia
	public TreePosition<E> addRoot(E e) throws NonEmptyTreeException {
		if (!isEmpty()) {
			throw new NonEmptyTreeException("Tree already has a root");
		}

		size = 1;
		root = createNode(e, null, null);
		return root;
	}

	// Troca os elementos de dos nodos
	public void swapElements(Position<E> v, Position<E> w) throws InvalidPositionException {
		TreePosition<E> vv = checkPosition(v);
		TreePosition<E> ww = checkPosition(w);
		E temp = w.element();
		ww.setElement(v.element());
		vv.setElement(temp);
	}

	// Se v é um bom nodo da árvore, cast para TreePosition, caso contrário, lança exceção
	protected TreePosition<E> checkPosition(Position<E> v) throws InvalidPositionException {
		if (v == null || !(v instanceof TreePosition)) {
			throw new InvalidPositionException("The position is invalid");
		}

		return (TreePosition<E>) v;
	}
	
	// Cria um novo nodo da árvore
	protected TreePosition<E> createNode(E element, TreePosition<E> parent, PositionList<Position<E>> children) {
		return (TreePosition<E>) new TreeNode<E>(element, parent, children);
	}
	
	// Cria uma lista armazenando os nodos das subárvore de um nodo
	// ordenado de acordo com a travessia das subárvores
	protected void preorderPositions(Position<E> v, PositionList<Position<E>> pos) throws InvalidPositionException {
		pos.addLast(v);
		for (Position<E> w : children(v)) {
			preorderPositions(w, pos);
		}
	}

	// Método que retorna a árvore em forma de String
	public String toString() { 
		return toString(this); 
	}

	// Método estático que converte a árvore para String
	public static <E> String toString(LinkedTree<E> T) {
		String s = "";

		for (E i : T) { 
			s += ", " + i; 
		}

		s = (s.length() == 0 ? s : s.substring(2));
		return "[" + s + "]";
	}

	// Retorna a estrutura da árvore
	public String parentheticRepresentation(Tree<E> T, Position<E> v) {
		String s = v.element().toString(); 
		String tabs = "\t";

		if (T.isInternal(v)) {
			Boolean firstTime = true;

			for (Position<E> w : T.children(v)) {
				if (firstTime) {
					s += "(\n" + tabs + parentheticRepresentation(T, w);
					firstTime = false;
				} 
				else {
					s += "," + parentheticRepresentation(T, w);
				}

				s += ")";
			}
		}

		return s;
	}
	
	// Cria uma lista armazenando os nodos das subárvores de um nodo
	// ordenado de acordo com as subárvores enraizadas
	public String toStringPostorder(LinkedTree<E> T, Position<E> v) {
		String teste="";

		for (Position<E> w : T.children(v)) {
			teste +=toStringPostorder(T, w)+"\n";
		}
		
		teste += v.element();
		return teste;
	}
	

	public void postorder(LinkedTree<E> T, Position<E> v) {
		for (Position<E> w : T.children(v)) {
			postorder(T, w);
		}
		
		System.out.println(v.element()+" Visitado");
	}
	
	// Retorna o tamanho do nodo no disco
	@SuppressWarnings("rawtypes")
	public int diskSpace(LinkedTree<DiscNode> d, TreePosition<DiscNode> treePosition) {
		int s = treePosition.element().getKbytes();
		
		for (Position<DiscNode> w : treePosition.getChildren()) {
			s += diskSpace(d, (TreePosition<DiscNode>) w);
		}
		
		if (d.isInternal(treePosition)) {
			System.out.println(treePosition.getElement().getName() + ": " + s);
		}
		
		return s;
	}

	// Retorna a profundidade da árvore
	public int depth(LinkedTree<E> T, Position<E> v) {
		if (T.isRoot(v)) {
			return 0;
		}

		return 1 + depth(T, T.parent(v));
	}
	
	// Retorna a altura da árvore utilizando o método depth
	public int height1(LinkedTree<E> T) {
		int h = 0;

		for (Position<E> v : T.positions()) {
			if (T.isExternal(v)) {
				h = Math.max(h, T.depth(T, v));
			}
		}

		return h;
	}

	// Retorna a profundidade da árvore
	public int height2(LinkedTree<E> T, Position<E> v) {
		if (!T.isExternal(v)) {
			int h = 0;

			for (Position<E> w : T.children(v)) {
				h = Math.max(h, height2(T, w));
			}

			return h + 1;
		}
		return 0;
	}
}
