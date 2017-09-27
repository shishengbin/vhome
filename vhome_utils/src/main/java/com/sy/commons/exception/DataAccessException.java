package com.sy.commons.exception;

public class DataAccessException extends RuntimeException {

	private static final long serialVersionUID = 2844856477224739630L;

	public DataAccessException() {
	}

	public DataAccessException(String msg) {
		super(msg);
	}

	public DataAccessException(Throwable e) {
		super(e);
	}
	
	public DataAccessException(String msg,Throwable e) {
		super(msg, e);
	}
}
