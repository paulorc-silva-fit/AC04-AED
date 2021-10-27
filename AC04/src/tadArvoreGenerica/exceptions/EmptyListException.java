package tadArvoreGenerica.exceptions;

/**
 * @author Geovane Donizete Laera  - RA: 1902679 
 * @author Isaque Ribeiro dos Santos Junior - RA: 1903978
 * @author Marcelo Martinez Mesa Campos - RA: 1905076 
 * @author Paulo Ricardo Costa da Silva - RA: 1905013 
 * @author Vinícius da Cruz Pera - RA: 1903144
 * Data: 06/10/2021
 *
 */
@SuppressWarnings("serial")
public class EmptyListException extends RuntimeException {
	public EmptyListException(String err) { 
		super(err); 
	}
}
