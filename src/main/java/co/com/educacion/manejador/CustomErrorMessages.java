package co.com.educacion.manejador;

import java.util.List;

public class CustomErrorMessages {

	private String errorMessage;

	private List<Object> errores;

	public CustomErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public CustomErrorMessages(List<Object> errores) {
		this.errores = errores;
	}
	
	public CustomErrorMessages(String errorMessage, List<Object> errores) {
		super();
		this.errorMessage = errorMessage;
		this.errores = errores;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<Object> getErrores() {
		return errores;
	}

	public void setErrores(List<Object> errores) {
		this.errores = errores;
	}

	
	
}
