package ch.hszt.sp.exceptions;

public class DataAccessException extends Exception {

	private static final long serialVersionUID = 7709630845645945323L;

	public DataAccessException() {
		super();
	}

	/**
	 * @param message
	 * @param throwable
	 */
	public DataAccessException(String message, Throwable throwable) {
		super(message, throwable);
	}

	/**
	 * @param message
	 */
	public DataAccessException(String message) {
		super(message);
	}

	/**
	 * @param throwable
	 */
	public DataAccessException(Throwable throwable) {
		super(throwable);
	}
}