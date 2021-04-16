package com.cg.AmusementPark.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Global exception for resource not found
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(),
				request.getDescription(false), new Date());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);

	}

//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> globleExcpetionHandler(Exception exception, WebRequest request) {
//
//		ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(),
//				request.getDescription(false), new Date());
//		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//
//	}

	/**
	 * Custom exceptions handlers of Customer entity
	 */
	@ExceptionHandler({ CustomerNotFoundException.class })
	public final ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException exception,
			WebRequest req) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(),
				"The customer which you are trying to perform operation on is not present in the database", new Date());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler({ CustomerExistsException.class })
	public final ResponseEntity<Object> handleCustomerExistsException(CustomerExistsException exception,
			WebRequest req) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(),
				"The customer which you are trying to perform operation is already present in the database",
				new Date());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.CONFLICT);

	}

	/**
	 * Custom exceptions handlers of Activity entity
	 */
	@ExceptionHandler({ ActivityNotFoundException.class })
	public final ResponseEntity<Object> handleActivityNotFoundException(ActivityNotFoundException exception,
			WebRequest req) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(),
				"The Activity which you are trying to perform operation is not present in the database", new Date());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler({ ActivityExistsException.class })
	public final ResponseEntity<Object> handleActivityExistsException(ActivityExistsException exception,
			WebRequest req) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(),
				"The Activity which you are trying to perform operation is already present in the database",
				new Date());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.CONFLICT);

	}

	/**
	 * Custom exceptions handlers of Ticket Booking entity
	 */
	@ExceptionHandler({ TicketBookingNotFoundException.class })
	public final ResponseEntity<Object> handleTicketBookingNotFoundException(TicketBookingNotFoundException exception,
			WebRequest req) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(),
				"The Ticket Booking Request which you are trying to perform operation is not present in the database",
				new Date());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);

	}

}
