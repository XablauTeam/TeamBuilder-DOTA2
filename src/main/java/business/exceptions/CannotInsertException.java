package business.exceptions;

public class CannotInsertException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CannotInsertException(String erro) {
	
		super(erro);
		
	}
}