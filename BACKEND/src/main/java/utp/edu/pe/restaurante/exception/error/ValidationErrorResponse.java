package utp.edu.pe.restaurante.exception.error;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse extends ErrorResponse {

	 private List<FieldError> errors = new ArrayList<>();

	    public ValidationErrorResponse(int status, String error, String message, String path) {
	        super(status, error, message, path);
	    }

	    public void addFieldError(String field, String message) {
	        this.errors.add(new FieldError(field, message));
	    }

	    public List<FieldError> getErrors() { return errors; }
	    public void setErrors(List<FieldError> errors) { this.errors = errors; }

	    // Clase interna para errores de campo
	    public static class FieldError {
	        private String field;
	        private String message;

	        public FieldError(String field, String message) {
	            this.field = field;
	            this.message = message;
	        }

	        public String getField() { return field; }
	        public void setField(String field) { this.field = field; }

	        public String getMessage() { return message; }
	        public void setMessage(String message) { this.message = message; }
	    }
}
