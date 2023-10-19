package de.neuefische.cgnjava233springdata.car.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CarNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage handleCarNotFoundException(CarNotFoundException exception){
		return new ErrorMessage(exception.getMessage());
	}

	@ExceptionHandler(CarAlreadyExistException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorMessage handleCarAlreadyExistException(CarAlreadyExistException exception){
		return new ErrorMessage(exception.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleAllOtherExceptions(Exception exception){
		return new ErrorMessage("An Error occurred: " + exception.getMessage());
	}

}
