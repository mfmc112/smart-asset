package com.smartasset.asset.exception;

public class JsonException extends Exception {

	private static final long serialVersionUID = -5719665204892367343L;
	private Exception nextedException = null;
	
	public JsonException(){
		super("JSONException ocurrend");
	}
	
	public JsonException(String message){
		super(message);
	}
	
	public JsonException(String message, Exception e){
		super(message, e);
		this.nextedException = e;
	}
	
	public Exception getNestedException(){
		return this.nextedException;
	}
}
