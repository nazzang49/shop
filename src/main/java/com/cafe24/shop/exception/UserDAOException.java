package com.cafe24.shop.exception;

public class UserDAOException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UserDAOException() {
		super("UserDAOException called");
		
	}
	
	public UserDAOException(String msg) {
		super(msg);
	}
	
}
