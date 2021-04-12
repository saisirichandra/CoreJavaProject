package com.lms.library;

public class InvalidChoiceException extends RuntimeException
{
	@Override
	public String toString()
	{
		return "Given choice is invalid... enter the choice";
	}
}
