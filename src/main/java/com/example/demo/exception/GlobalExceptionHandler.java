package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.dto.reponse.ResponseErrorCode;

import lombok.extern.log4j.Log4j2;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

	@ExceptionHandler(value = RuntimeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseErrorCode handleRunTimeException(RuntimeException e, WebRequest request) {
//		log.info("loi khong xac dinh",e.getCause());
		ResponseErrorCode errorCode = new ResponseErrorCode();
		errorCode.setCode(ErrorCode.UNCATEGORIZED.getErrorCode());
		errorCode.setMessage(e.toString());
	
		return errorCode;
	}
	
	@ExceptionHandler(value = AppException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseErrorCode handleAppException(AppException e, WebRequest request) {
		ErrorCode errorCode= e.getErrorCode();
		ResponseErrorCode responseErrorCode = new ResponseErrorCode();
		responseErrorCode.setCode(errorCode.getErrorCode());
		responseErrorCode.setMessage(errorCode.getMessage());
		return responseErrorCode;
	}


	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseErrorCode handleValidation(MethodArgumentNotValidException e, WebRequest request) {
		String enumErrorCode = e.getFieldError().getDefaultMessage();
		ErrorCode errorCode=ErrorCode.INVALID_CODE;
		
		try {
			errorCode = ErrorCode.valueOf(enumErrorCode);
		} catch (IllegalArgumentException e2) {
			// TODO: handle exception
		}

		ResponseErrorCode responseErrorCode = new ResponseErrorCode();

		responseErrorCode.setCode(errorCode.getErrorCode());
		responseErrorCode.setMessage(errorCode.getMessage());

		return responseErrorCode;
	}

}
