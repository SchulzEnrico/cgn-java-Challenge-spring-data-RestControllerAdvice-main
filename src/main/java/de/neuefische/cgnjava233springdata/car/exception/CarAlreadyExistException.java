package de.neuefische.cgnjava233springdata.car.exception;

public class CarAlreadyExistException extends RuntimeException{
	public CarAlreadyExistException(String message){
		super(message);
	}
}
