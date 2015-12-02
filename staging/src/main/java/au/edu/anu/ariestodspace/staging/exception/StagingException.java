package au.edu.anu.ariestodspace.staging.exception;

public class StagingException extends Exception {
	private static final long serialVersionUID = 1L;

	public StagingException(String message, Throwable cause) {
		super(message, cause);
	}

	public StagingException(String message) {
		super(message);
	}
}
