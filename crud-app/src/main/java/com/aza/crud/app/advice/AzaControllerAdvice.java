package com.aza.crud.app.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.aza.crud.app.custom.exception.BusinessException;
import com.aza.crud.app.custom.exception.DBException;
import com.aza.crud.app.custom.exception.EmptyInputException;
import com.aza.crud.app.custom.exception.NullRecordException;

@ControllerAdvice
public class AzaControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInput(EmptyInputException exc){
		return new ResponseEntity<String>("Input field not provided", HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(DBException.class)
	public ResponseEntity<String> handleDBRelatedError(DBException ex){
		return new ResponseEntity<String>("does not exist in the database.", HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	@ExceptionHandler(NullRecordException.class)
	public ResponseEntity<String> handleNullRecord(NullRecordException ne){
		return new ResponseEntity<String>("record is null", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<String> handleServiceLayerIssue(BusinessException b){
		return new ResponseEntity<String>("service layer issue", HttpStatus.EXPECTATION_FAILED);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		return new ResponseEntity<Object>("Please change http method type ", HttpStatus.FORBIDDEN );
	}
	
	
}
