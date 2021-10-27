package tadArvoreBinaria.source;

import java.util.Arrays;
import java.util.Iterator;

import tadArvoreBinaria.exceptions.BoundaryViolationException;
import tadArvoreBinaria.exceptions.EmptyTreeException;
import tadArvoreBinaria.exceptions.InvalidPositionException;
import tadArvoreBinaria.exceptions.NonEmptyTreeException;
import tadArvoreBinaria.interfaces.BTPosition;
import tadArvoreBinaria.interfaces.BinaryTree;
import tadArvoreBinaria.interfaces.Position;
import tadArvoreBinaria.interfaces.PositionList;
import tadArvoreBinaria.interfaces.Tree;

/**
 * @author Geovane Donizete Laera  - RA: 1902679 
 * @author Isaque Ribeiro dos Santos Junior - RA: 1903978
 * @author Marcelo Martinez Mesa Campos - RA: 1905076 
 * @author Paulo Ricardo Costa da Silva - RA: 1905013 
 * @author Vinícius da Cruz Pera - RA: 1903144
 * Data: 13/10/2021
 *
 */
public class LinkedBinaryTree<E> implements BinaryTree<E> {
	// Referência para a raiz
	protected BTPosition<E> root;
	
	// Número de nodos
	protected int size;

	// Método construtor
	public LinkedBinaryTree() {
		root = null;
		size = 0;
	}

	// Retorna o número de nodos da árvore.
	public int size() { 
		return size; 
	}

	// Retorna se a árvore está vazia
	public boolean isEmpty() { 
		return (size == 0); 
	}

	// Retorna se um nodo é interno.
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		checkPosition(v);
		return (hasLeft(v) || hasRight(v));
	}

	// Retorna se um nodo é externo.
	public boolean isExternal(Position<E> v) throws InvalidPositionException { 
		return !isInternal(v); 
	}

	// Retorna se um nodo é a raiz.
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		checkPosition(v);
		return (v == root());
	}
	
	// Retorna se um nodo tem o filho da esquerda.
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		BTPosition<E> vv = checkPosition(v);
		return (vv.getLeft() != null);
	}

	// Retorna se um nodo tem o filho da direita.
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		BTPosition<E> vv = checkPosition(v);
		return (vv.getRight() != null);
	}

	// Retorna a raiz da árvore.
	public Position<E> root() throws EmptyTreeException {
		if (root == null) {
			throw new EmptyTreeException("The tree is empty");
		}

		return root;
	}
		
	// Retorna o filho da esquerda de um nodo.
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTPosition<E> vv = checkPosition(v);
		Position<E> leftPos = (Position<E>) vv.getLeft();

		if (leftPos == null) {
			throw new BoundaryViolationException("No left child");
		}

		return leftPos;
	}
	
	// Retorna o filho da direita de um nodo.
	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTPosition<E> vv = checkPosition(v);
		Position<E> rightPos = (Position<E>) vv.getRight();

		if (rightPos == null) {
			throw new BoundaryViolationException("No right child");
		}

		return rightPos;
	}

	// Retorna o pai de um nodo.
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTPosition<E> vv = checkPosition(v);
		Position<E> parentPos = (Position<E>) vv.getParent();

		if (parentPos == null) {
			throw new BoundaryViolationException("No parent");
		}
		
		return parentPos;

	}
		
	// Retorna uma coleção iterável contendo os filhos de um nodo.
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		PositionList<Position<E>> children = new NodePositionList<Position<E>>();

		if (hasLeft(v)) {
			children.addLast(left(v));
		}

		if (hasRight(v)) {
			children.addLast(right(v));
		}

		return children;
	}
	
	// Cria uma lista que armazena os nodos da subárvore de um nodo ordenados de acordo com o caminhamento inorder da subárvore.
	public void inorderPositions(Position<E> v, PositionList<Position<E>> pos) throws InvalidPositionException {
		if (hasLeft(v)) {
			inorderPositions(left(v), pos); 
		}
		
		pos.addLast(v);
		
		if (hasRight(v)) {
			inorderPositions(right(v), pos); 
		}
	}

	// Retorna uma coleção iterável (inorder) contendo os nodos da árvore.
	public Iterable<Position<E>> positionsInorder() {
		PositionList<Position<E>> positions = new NodePositionList<Position<E>>();

		if (size != 0) {
			inorderPositions(root(), positions); 
		}

		return positions;
	}
		
		
	// Retorna uma coleção iterável contendo os nodos da árvore.
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> positions = new NodePositionList<Position<E>>();

		if (size != 0) {
			preorderPositions(root(), positions);
		}

		return positions;
	}
	
	// Retorna um iterador sobre os elementos armazenados nos nodos
	public Iterator<E> iterator() {
		Iterable<Position<E>> positions = positions();
		PositionList<E> elements = new NodePositionList<E>();

		for (Position<E> pos : positions) {
			elements.addLast(pos.element());
		}

		return elements.iterator(); 
	}

	// Substitui o elemento armazenado no nodo.
	public E replace(Position<E> v, E o) throws InvalidPositionException {
		BTPosition<E> vv = checkPosition(v);
		E temp = v.element();
		vv.setElement(o);
		return temp;
	}

	// Retorna o irmão de um nodo
	public Position<E> sibling(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTPosition<E> vv = checkPosition(v);
		BTPosition<E> parentPos = vv.getParent();

		if (parentPos != null) {
			BTPosition<E> sibPos;
			BTPosition<E> leftPos = parentPos.getLeft();
			if (leftPos == vv) {
				sibPos = parentPos.getRight();
			}
			else {
				sibPos = parentPos.getLeft();
			}

			if (sibPos != null) {
				return sibPos;
			}
		}

		throw new BoundaryViolationException("No sibling");
	}

	// Insere a raiz em uma árvore vazia
	public Position<E> addRoot(E e) throws NonEmptyTreeException {
		if (!isEmpty()) {
			throw new NonEmptyTreeException("Tree already has a root");
		}

		size = 1;
		root = createNode(e, null, null, null);
		return root;
	}

	// Insere o filho da esquerda em um nodo.
	public Position<E> insertLeft(Position<E> v, E e) throws InvalidPositionException {
		BTPosition<E> vv = checkPosition(v);
		Position<E> leftPos = (Position<E>) vv.getLeft();

		if (leftPos != null) {
			throw new InvalidPositionException("Node already has a left child");
		}

		BTPosition<E> ww = createNode(e, vv, null, null);
		vv.setLeft(ww);
		size++;
		return ww;
	}
	
	// Insere o filho a direita em um nodo.
	public Position<E> insertRight(Position<E> v, E e) throws InvalidPositionException {
		BTPosition<E> vv = checkPosition(v);
		Position<E> rightPos = (Position<E>) vv.getRight();

		if (rightPos != null) {
			throw new InvalidPositionException("Node already has a right child");
		}

		BTPosition<E> ww = createNode(e, vv, null, null);
		vv.setRight(ww);
		size++;
		return ww;
	}

	// Remove um nodo com zero ou um filho.
	public E remove(Position<E> v) throws InvalidPositionException {
		BTPosition<E> vv = checkPosition(v);
		BTPosition<E> leftPos = vv.getLeft();
		BTPosition<E> rightPos = vv.getRight();

		if (leftPos != null && rightPos != null) {
			throw new InvalidPositionException("Cannot remove node with two children");
		}

		BTPosition<E> ww;

		if (leftPos != null) {
			ww = leftPos;
		}
		else if (rightPos != null) {
			ww = rightPos;
		}
		else{
			ww = null;
		}

		if (vv == root) { 
			if (ww != null) {
				ww.setParent(null);
			}

			root = ww;
		} 
		else { 
			BTPosition<E> uu = vv.getParent();

			if (vv == uu.getLeft()) {
				uu.setLeft(ww);
			}
			else {
				uu.setRight(ww);
			}

			if (ww != null) {
				ww.setParent(uu);
			}
		}

		size--;
		return v.element();
	}

	// Conecta duas árvores para serem subárvores de um nodo externo.
	public void attach(Position<E> v, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException {
		BTPosition<E> vv = checkPosition(v);

		if (isInternal(v)) {
			throw new InvalidPositionException("Cannot attach from internal node");
		}

		if (!T1.isEmpty()) {
			BTPosition<E> r1 = checkPosition(T1.root());
			vv.setLeft(r1);
			r1.setParent(vv);
		}

		if (!T2.isEmpty()) {
			BTPosition<E> r2 = checkPosition(T2.root());
			vv.setRight(r2);
			r2.setParent(vv);
		}
	}

	// Se v é um nodo de árvore binária, converte para BTPosition, caso contrário lança exceção
	protected BTPosition<E> checkPosition(Position<E> v) throws InvalidPositionException {
		if (v == null || !(v instanceof BTPosition)) {
			throw new InvalidPositionException("The position is invalid");
		}

		return (BTPosition<E>) v;
	}
		
	// Cria um novo nodo de árvore binária
	protected BTPosition<E> createNode(E element, BTPosition<E> parent, BTPosition<E> left, BTPosition<E> right) {
		return new BTNode<E>(element, parent, left, right);
	}
		
	// Cria uma lista que armazena os nodos da subárvore de um nodo ordenados de acordo com o 
	// caminhamento prefixado da subárvore.
	protected void preorderPositions(Position<E> v, PositionList<Position<E>> pos) throws InvalidPositionException {
		pos.addLast(v);

		if (hasLeft(v)) {
			preorderPositions(left(v), pos);
		}

		if (hasRight(v)) {
			preorderPositions(right(v), pos);
		}
	}

	// Método estático que converte a árvore para String
	public static <E> String toString(LinkedBinaryTree<E> T) {
		String s = "";

		for (Iterator<E> it = T.iterator(); it.hasNext();) {
			s += ", " + it.next().toString();
		}

		s = (s.length() == 0 ? s : s.substring(2));
		return "[" + s + "]";

	}

	// Percorre a árvore com PreOrder
	public String binaryPreOrder(LinkedBinaryTree<E> T, Position<E> v) {

		LinkedBinaryTree<E> sub = new LinkedBinaryTree<E>();
		String f = "";
		f = T.checkPosition(v).element().toString();
		sub.addRoot(T.root().element());
		if (T.hasLeft(v)) {
			f += binaryPreOrder(sub, sub.left(v));
		}
		if (T.hasRight(v)) {
			f += binaryPreOrder(sub, sub.right(v));
		}

		return f;

	}

	// Percorre a árvore com PreOrder
	public String binaryPostOrder(LinkedBinaryTree<E> T, Position<E> v) {

		LinkedBinaryTree<E> sub = new LinkedBinaryTree<E>();
		String f = "";
		sub.addRoot(T.root().element());
		if (T.hasLeft(v)) {

			f += binaryPostOrder(sub, sub.left(v));
		}
		if (T.hasRight(v)) {
			f += binaryPostOrder(sub, sub.right(v));
		}

		f += T.checkPosition(v).element();
		return f;

	}

	// Avalia uma expressão algébrica
	public Double evaluateExpression(LinkedBinaryTree<E> T, Position<E> v) {		
		if (T.isInternal(v)) {
			Double x = evaluateExpression(T, T.left(v));
			Double y = evaluateExpression(T, T.right(v));
			switch (T.checkPosition(v).element().toString()) {
			case "+":
				return x + y;

			case "-":
				return x - y;

			case "/":
				return x / y;

			case "*":
				return x * y;
			}

		}
		return Double.parseDouble(T.checkPosition(v).element().toString());
	}

	// Percorre a árvore "da esquerda para a direita"
	public String inorder(LinkedBinaryTree<E> T, Position<E> v, String divisor) {
		String f = "";
		
		if (T.hasLeft(v) == true) {
			f += inorder(T, T.left(v), divisor);
		}

		f += T.checkPosition(v).element().toString() + divisor;
		if (T.hasRight(v) == true) {
			f += inorder(T, T.right(v), divisor);
		}

		return f;
	}

	// Retorna a estrutura da árvore
	public String parentheticRepresentation(Tree<E> T, Position<E> v) {
		String s = v.element().toString();

		if (T.isInternal(v)) {
			Boolean firstTime = true;

			for (Position<E> w : T.children(v)) {
				if (firstTime) {
					s += "(" + parentheticRepresentation(T, w);
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

	// Percorre a árvore "da esquerda para a direita"
	public String inorder(LinkedBinaryTree<E> T, Position<E> v) {
		String f = "";

		if (T.hasLeft(v) == true) {
			f += inorder(T, T.left(v));
		}

		f += T.checkPosition(v).element().toString();

		if (T.hasRight(v) == true) {
			f += inorder(T, T.right(v));
		}

		return f;
	}

	// Desenha uma árvore binária
	public void desenhaArvore(LinkedBinaryTree<E> T, Position<E> v, int qtdeNPercorrido, int profundidade ) {
		char pulo[] = new char[profundidade];
		Arrays.fill(pulo, '\n');
		String puloString = new String(pulo);
		
		char tab[] = new char[T.contEsquerda(T,T.left(T.root()))-qtdeNPercorrido];
		Arrays.fill(tab, '\t');
		String tabString = new String(tab);
		
		System.out.print(puloString+tabString+T.checkPosition(v).element().toString());
		
		if(T.hasLeft(v)) {
			desenhaArvore(T, T.left(v), qtdeNPercorrido+1, profundidade+1);
		}

		if(T.hasRight(v)) {
			desenhaArvore(T, T.right(v), qtdeNPercorrido+1, profundidade+1);
		}
	}

	// Percorre a árvore com eulerTour
	public String eulerTour(LinkedBinaryTree<E> T, Position<E> v) {
		String f = "";
		f += T.checkPosition(v).element();

		if (T.hasLeft(v)) {
			f += eulerTour(T, T.left(v));
		}

		f += T.checkPosition(v).element();

		if (T.hasRight(v)) {
			f += eulerTour(T, T.right(v));
		}

		f += T.checkPosition(v).element();

		return f;
	}

	// Exibe uma expressão algébrica
	public String printExpression(LinkedBinaryTree<E> T, Position<E> v) {
		String f = "";
		if (T.isInternal(v)) {
			f += "(";
		}
		if (T.hasLeft(v)) {
			f += printExpression(T, T.left(v));
		}
		if (T.isInternal(v)) {
			f += (T.checkPosition(v).element());
		} else {
			f += (T.checkPosition(v).element());
		}
		if (T.hasRight(v)) {
			f += printExpression(T, T.right(v));
		}
		if (T.isInternal(v)) {
			f += ")";
		}

		return f;
	}

	// Conta quantos nodos esquerdos e externos a árvore tem
	public int contEsquerda(LinkedBinaryTree<E> T, Position<E> v) {
		int f = 0;
		f = inorder(T, T.left(v)).length();
		return f;
	}

	// Conta quantos nodos direitos e externos a árvore tem
	public int contDireita(LinkedBinaryTree<E> T, Position<E> v) {
		int f = 0;
		f = inorder(T, T.right(v)).length();
		return f;
	}
}
