package com.sy.commons.exception;

public class UserAbsence extends RuntimeException {

	private static final long serialVersionUID = -5133398061892098372L;

	public UserAbsence() {
	}

	public UserAbsence(String msg) {

		super(msg);
	}

	public UserAbsence(Throwable e) {
		super(e);
	}

	public UserAbsence(String msg, Throwable e) {
		super(msg, e);
	}
}
