package de.neuefische.cgnjava233springdata.car.exception;

public class CarNotFoundException extends RuntimeException{
	public CarNotFoundException(String message){
		super(message);
	}
}
