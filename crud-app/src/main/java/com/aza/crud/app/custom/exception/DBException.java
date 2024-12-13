package com.aza.crud.app.custom.exception;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Component
@Setter
@Getter
@AllArgsConstructor
public class DBException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private String errorMessage;
	
	public DBException() {
	}

}
