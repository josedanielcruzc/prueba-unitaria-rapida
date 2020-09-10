package co.com.educacion.negocio.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import co.com.educacion.manejador.CustomErrorMessages;

public class GeneralRest {

	public GeneralRest() {
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	protected ResponseEntity<Object> manejarValidacionException(MethodArgumentNotValidException ex) {
		List<Object> errors = new ArrayList<Object>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
//			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.add(errorMessage);
		});

		return new ResponseEntity<Object>(new CustomErrorMessages("Argumentos no validos", errors),
				HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ ConstraintViolationException.class })
	protected ResponseEntity<Object> manejarValidacionPersonalizada(ConstraintViolationException ex) {
		List<Object> errors = new ArrayList<Object>();
		for (ConstraintViolation violation : ex.getConstraintViolations() ) {
			errors.add( violation.getInvalidValue() +  " : "  + violation.getMessage() );
		}
		return new ResponseEntity<Object>(new CustomErrorMessages("Argumentos no validos", errors),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ HttpMessageNotReadableException.class })
	protected ResponseEntity<Object> manejarPaseJsonException(HttpMessageNotReadableException ex) {
		return new ResponseEntity<Object>(new CustomErrorMessages("Objeto no reconocido en la peticion"),
				HttpStatus.BAD_REQUEST);
	}

}
