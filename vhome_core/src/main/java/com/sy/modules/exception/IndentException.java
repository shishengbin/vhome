package com.sy.modules.exception;

public class IndentException extends RuntimeException {

	private static final long serialVersionUID = -5133398061892098372L;

	public IndentException() {
	}

	public IndentException(String msg) {

		super(msg);
	}

	public IndentException(Throwable e) {
		super(e);
	}

	public IndentException(String msg, Throwable e) {
		super(msg, e);
	}
}
