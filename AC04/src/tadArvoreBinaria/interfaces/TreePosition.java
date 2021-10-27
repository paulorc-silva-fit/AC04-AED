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
public interface TreePosition<E> extends Position<E> {
	// Define o elemento a ser armazenado nesta posição
	public void setElement(E o);

	// Retorna o elemento armazenado nesta posição
	public E getElement();

	// Retorna os filhos desta posição
	public PositionList<Position<E>> getChildren();

	// Define os filhos desta posição
	public void setChildren(PositionList<Position<E>> c);

	// Retorna o pai desta posição
	public TreePosition<E> getParent();

	// Define o pai desta posição
	public void setParent(TreePosition<E> v);
}
