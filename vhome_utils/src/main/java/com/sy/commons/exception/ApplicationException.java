package com.sy.commons.exception;

public class ApplicationException extends RuntimeException {
	
	private static final long serialVersionUID = 83736152900599235L;

	public ApplicationException() {
	};

	public ApplicationException(String msg) {
		super(msg);
	}

	public ApplicationException(Throwable e) {
		super(e);
	}

	public ApplicationException(String msg, Throwable e) {
		super(msg, e);
	}

}
