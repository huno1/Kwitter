package exp;

public class SystemException extends RuntimeException {
	public SystemException (String mes, Throwable cause){
		super(mes, cause);
	}
}