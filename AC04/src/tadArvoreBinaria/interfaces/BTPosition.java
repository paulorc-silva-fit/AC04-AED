package tadArvoreBinaria.interfaces;

/**
 * @author Geovane Donizete Laera  - RA: 1902679 
 * @author Isaque Ribeiro dos Santos Junior - RA: 1903978
 * @author Marcelo Martinez Mesa Campos - RA: 1905076 
 * @author Paulo Ricardo Costa da Silva - RA: 1905013 
 * @author Vinícius da Cruz Pera - RA: 1903144
 * Data: 13/10/2021
 *
 */
public interface BTPosition<E> extends Position<E> {
	// Retorna o elemento armazenado nesta posição
	public E element();

	// Define o elemento armazenado nesta posição
	public void setElement(E o);

	// Retorna o filho da esquerda desta posição
	public BTPosition<E> getLeft();

	// Define o filho da esquerda desta posição
	public void setLeft(BTPosition<E> v);

	// Retorna o filho da direita desta posição
	public BTPosition<E> getRight();

	// Define o filho da direita desta posição
	public void setRight(BTPosition<E> v);

	// Retorna o pai desta posição
	public BTPosition<E> getParent();

	// Define o pai desta posição
	public void setParent(BTPosition<E> v);

	}