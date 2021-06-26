package com.CodingBootCamp.customException;

public class InvalidEmailFormatException extends Exception{

	public InvalidEmailFormatException(String s) {
		super(s);
	}
}
