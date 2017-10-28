package com.anashamidkh.messenger.exception;

public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2047647168317268193L;
	
	public DataNotFoundException(String message) {
		super(message);
	}
}
