package exception;


public class LoginException extends RuntimeException{	
	private String url;
	
	public LoginException(String url, String msg) {
		super(msg); //RuntimeException의 getMessage(msg)
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
}
