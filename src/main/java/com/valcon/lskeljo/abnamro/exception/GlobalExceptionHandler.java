package com.valcon.lskeljo.abnamro.exception;

import com.valcon.lskeljo.abnamro.dto.ErrorResponse;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	@ResponseStatus(BAD_REQUEST)
	public ErrorResponse handle(MethodArgumentNotValidException e) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : e.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		return new ErrorResponse(LocalDateTime.now(), BAD_REQUEST.value(), "Validation Failed", details);
	}

	@ExceptionHandler
	@ResponseStatus(BAD_REQUEST)
	public ErrorResponse handle(BadRequestException e) {
		return new ErrorResponse(
				LocalDateTime.now(),
				BAD_REQUEST.value(),
				"Validation Failed",
				Collections.singletonList(e.getMessage()));
	}

	@ExceptionHandler
	@ResponseStatus(NOT_FOUND)
	public ErrorResponse handle(NotFoundException e) {
		return new ErrorResponse(
				LocalDateTime.now(),
				NOT_FOUND.value(),
				"Record Not Found",
				Collections.singletonList(e.getMessage()));
	}
}
