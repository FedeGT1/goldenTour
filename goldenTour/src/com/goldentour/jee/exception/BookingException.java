package com.goldentour.jee.exception;

public class BookingException extends Exception {
	
	public BookingException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BookingException() {
		super();
	}

	public BookingException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BookingException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BookingException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
