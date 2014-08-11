package PatrickCahill;

public class CustomException extends Exception {

	private String message;

	public CustomException(String message) throws CustomException {

		super(message);

	}
}
