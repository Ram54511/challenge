/**
 * 
 */
package com.code.challenge.common.exception;

import com.code.challenge.common.local.LocalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.code.challenge.utils.ErrorCode;
import com.code.challenge.utils.ResponseCode;
import com.code.challenge.utils.ResponseHandler;

/**
 * @author Ram Thapa
 *
 */
@RestControllerAdvice
@Order(1)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private LocalService localeService;

	public GlobalExceptionHandler() {
		super();
	}

	private Logger logger1 = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * to handle Illegal Argument Exception
	 * 
	 * @param ex
	 * @param request
	 * @return ResponseEntity
	 */

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Object> handleCustomException(final CustomException ex, final WebRequest request) {
		logger1.error("IllegalArgumentException: {}", ex.getLocalizedMessage());
		return ResponseHandler.response(HttpStatus.BAD_REQUEST, true, ex.getMessage(), ErrorCode.ERROR,
				ResponseCode.ACKNOWLEDGE_OPTIONAL_RESPONSE_OBJECT);
	}

}
