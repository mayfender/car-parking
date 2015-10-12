package com.may.ple.parking.center.exception;

public class CustomerException extends Exception {
	private static final long serialVersionUID = -2778152939502565089L;
	public int errCode;
	
	public CustomerException(int errCode, String msg) {
		super(msg);
		this.errCode = errCode;
	}
	
}
