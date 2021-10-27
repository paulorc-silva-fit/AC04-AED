package tadArvoreBinaria.source;

import tadArvoreBinaria.exceptions.EmptyStackException;
import tadArvoreBinaria.exceptions.FullStackException;
import tadArvoreBinaria.interfaces.Stack;

/**
 * @author Geovane Donizete Laera  - RA: 1902679 
 * @author Isaque Ribeiro dos Santos Junior - RA: 1903978
 * @author Marcelo Martinez Mesa Campos - RA: 1905076 
 * @author Paulo Ricardo Costa da Silva - RA: 1905013 
 * @author Vinícius da Cruz Pera - RA: 1903144
 * Data: 13/10/2021
 *
 */
public class ArrayStack<E> implements Stack<E> {
	// Capacidade real do arranjo da pilha
    protected int capacity; 
    
    // Capacidade default
    public static final int CAPACITY = 1000;
    
    // Arranjo usado para implementar a pilha
    protected E S[];
    
    // Índice para o topo da pilha
    protected int top = -1;

    // Método construtor
    public ArrayStack() {
        this(CAPACITY); 
    }

    // O compilador deve gerar um aviso, mas está ok
    @SuppressWarnings("unchecked")
    public ArrayStack(int cap) {
        capacity = cap;
        S = (E[]) new Object[capacity];
    }
    
    // Retorna o tamanho da pilha
    public int size() {
        return (top + 1);
    }
    
    // Retorna se a pilha está vazia
    public boolean isEmpty() {
        return (top < 0);
    }
    
    // Empilha um elemento na pilha
    public void push(E element) throws FullStackException {
        if (size() == capacity) {
            throw new FullStackException("Stack is full.");
        }
        
        S[++top] = element;
    }
    
    // Retorna o último elemento da pilha
    public E top() throws EmptyStackException {
        if (isEmpty()) {
        	throw new EmptyStackException("Stack is empty.");
        }
        
        return S[top];
    }

    // Desempilha um elemento na pilha
    public E pop() throws EmptyStackException {
        E element;
        
        if (isEmpty()) {
        	throw new EmptyStackException("Stack is empty.");
        }
        
        element = S[top];
        S[top--] = null;
        return element;
    }

	// Método que converte a pilha para String
    public String toString() {
        String s;
        s = "[";
        if (size() > 0) {
        	s += S[0];
        }
        
        if (size() > 1) {
            for (int i = 1; i <= size() - 1; i++) {
                s += ", " + S[i];
            }
        }
        
        return s + "]";
    }
}