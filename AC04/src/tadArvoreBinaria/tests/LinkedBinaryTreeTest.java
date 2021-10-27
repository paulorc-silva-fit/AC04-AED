package tadArvoreBinaria.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import tadArvoreBinaria.interfaces.Position;
import tadArvoreBinaria.source.ArrayStack;
import tadArvoreBinaria.source.LinkedBinaryTree;

class LinkedBinaryTreeTest {
	LinkedBinaryTree<String> T = BuildExpression("((((3+1)*3)/((9-5)+2))-((3*(7-4))+6))");

	@Test
	void binaryPreorderTest() {
		assertEquals("-/*+313+-952+*3-746", T.binaryPreOrder(T, T.root()));
	}

	@Test
	void binaryPostorderTest() {
		assertEquals("31+3*95-2+/374-*6+-", T.binaryPostOrder(T, T.root()));
	}

	@Test
	void evaluateExpression() {
		assertEquals(-13.0, T.evaluateExpression(T, T.root()));
	}

	@Test
	void inorderTest() {
		assertEquals("3+1*3/9-5+2-3*7-4+6", T.inorder(T, T.root()));
	}

	@Test
	void makeBTSSearchTest() {
		LinkedBinaryTree<Integer> BTS = makerBTSearch();

		assertEquals("12, 25, 31, 36, 42, 58, 62, 75, 90",
				BTS.inorder(BTS, BTS.root(), ", ").substring(0, BTS.inorder(BTS, BTS.root(), ", ").length() - 2));
	}

	@Test
	void desenharArvore() {
		T.desenhaArvore(T, T.left(T.root()), 0, 0);
	}
	
	@Test
	void eulerTourTest() {
		assertEquals("-/*+333+111+*333*/+-999-555-+222+/-+*333*-777-444-*+666+-", T.eulerTour(T, T.root()));
	}
	
	@Test
	void printExpressionTest() {
		assertEquals("((((3+1)*3)/((9-5)+2))-((3*(7-4))+6))", T.printExpression(T, T.root()));
	}

	@Test
	void contarNodosEsquerdaTest() {
		assertEquals(11, T.contEsquerda(T, T.root()));
	}

	@Test
	void contarNodosDireitaTest() {
		assertEquals(7, T.contDireita(T, T.root()));
	}
	
	public LinkedBinaryTree<String> BuildExpression(String E) {
		ArrayStack<LinkedBinaryTree<String>> S = new ArrayStack<LinkedBinaryTree<String>>();

		for (int i = 0; i < E.length(); i++) {
			if (E.charAt(i) != ')' && E.charAt(i) != '(') {
				LinkedBinaryTree<String> T = new LinkedBinaryTree<String>();
				T.addRoot(Character.toString(E.charAt(i)));
				S.push(T);
			} 
            else if (E.charAt(i) == '(') {

			} 
            else if (E.charAt(i) == ')') {
				LinkedBinaryTree<String> T2 = new LinkedBinaryTree<String>();
				T2 = S.pop();
				LinkedBinaryTree<String> T = new LinkedBinaryTree<String>();
				T = S.pop();
				LinkedBinaryTree<String> T1 = new LinkedBinaryTree<String>();
				T1 = S.pop();
				T.attach(T.root(), T1, T2);
				S.push(T);
			}
		}

		return S.pop();
	}

	public LinkedBinaryTree<Integer> makerBTSearch() {
		LinkedBinaryTree<Integer> novo = new LinkedBinaryTree<Integer>();

		Position<Integer> raiz, esquerda, direita;
		novo.addRoot(58);
		raiz = novo.root();
		esquerda = novo.root();
		direita = novo.root();

		esquerda = novo.insertLeft(raiz, 31);
		direita = novo.insertRight(esquerda, 42);
		esquerda = novo.insertLeft(esquerda, 25);
		novo.insertLeft(direita, 36);
		novo.insertLeft(esquerda, 12);

		direita = novo.insertRight(raiz, 90);
		esquerda = novo.insertLeft(direita, 62);
		novo.insertRight(esquerda, 75);

		return novo;
	}
}
