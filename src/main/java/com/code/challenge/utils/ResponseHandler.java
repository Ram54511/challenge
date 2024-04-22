/**
 * 
 */
package com.code.challenge.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Ram Thapa
 *
 */

public class ResponseHandler {
	private ResponseHandler() {

	}
	
	public static ResponseEntity<Object> generateResponse(HttpStatus status, boolean error, String message,
			Object responseObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("timestamp", new Date().getTime());
			map.put("status", status.value());
			map.put("isSuccess", error);
			map.put("message", message);
			map.put("data", responseObj);

			return new ResponseEntity<Object>(map, status);
		} catch (Exception e) {
			map.clear();
			map.put("timestamp", new Date().getTime());
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("isSuccess", false);
			map.put("message", e.getMessage());
			map.put("data", null);
			return new ResponseEntity<Object>(map, status);
		}
	}
	
	public static ResponseEntity<Object> generateValidationResponse(HttpStatus status, boolean error,
			String responseObj) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("timestamp", new Date().getTime());
			map.put("status", status.value());
			map.put("isSuccess", error);
			map.put("message", responseObj);

			return new ResponseEntity<Object>(map, status);
		} catch (Exception e) {
			map.clear();
			map.put("timestamp", new Date().getTime());
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("isSuccess", false);
			map.put("message", e.getMessage());
			map.put("data", null);
			return new ResponseEntity<Object>(map, status);
		}
	}

	/**
	 * 
	 * @param httpStatus
	 * @param isError
	 * @param message
	 * @param errorCode
	 * @param responseObject
	 * @return
	 */
	public static ResponseEntity<Object> response(HttpStatus httpStatus, Boolean isError, String message,
                                                  ErrorCode errorCode, ResponseCode responseCode) {

		Map<String, Object> map = new HashMap<>();
		map.put("timestamp", new Date().getTime());
		map.put("httpStatus", httpStatus.value());
		map.put("isError", isError);

		if (isError) {
			Error error = new Error(message, errorCode, message);
			map.put("error", error);
			map.put("errorStatus", errorCode.value());
		}
		map.put("message", message);
		map.put("responseCode", responseCode.value());
		return new ResponseEntity<>(map, httpStatus);

	}

	/**
	 * 
	 * @param httpStatus
	 * @param isError
	 * @param message
	 * @param errorCode
	 * @param responseObject
	 * @param responseCode
	 * @return
	 */
	public static ResponseEntity<Object> response(HttpStatus httpStatus, Boolean isError, String message,
			ErrorCode errorCode, ResponseCode responseCode, Object responseObject) {
		Map<String, Object> map = new HashMap<>();
		map.put("timestamp", new Date().getTime());
		map.put("httpStatus", httpStatus.value());
		map.put("isError", isError);
		if (isError) {
			Error error = new Error(message, errorCode, message);
			map.put("error", error);
			map.put("errorStatus", errorCode.value());
		}
		map.put("message", message);
		map.put("responseCode", responseCode.value());
		map.put("responseObject", responseObject);
		return new ResponseEntity<>(map, httpStatus);

	}
	//====================================coin-zoom==============================================
	/**
	 * 
	 * @param httpStatus
	 * @param isError
	 * @param message
	 * @param errorCode
	 * @param responseCode
	 * @param responseObject
	 * @return
	 */
	public static ResponseEntity<Object> responseWithData(HttpStatus httpStatus, Boolean isSuccess, String message,
			ErrorCode errorCode, ResponseCode responseCode, Object responseObject) {
		Map<String, Object> map = new HashMap<>();
		map.put("timestamp", new Date().getTime());
		map.put("httpStatus", httpStatus.value());
		map.put("isSuccess", isSuccess);
		if (!isSuccess) {
			Error error = new Error(message, errorCode, message);
			map.put("error", error);
			map.put("errorStatus", errorCode.value());
		}
		map.put("message", message);
		map.put("responseCode", responseCode.value());
		map.put("responseObject", responseObject);
		return new ResponseEntity<>(map, httpStatus);

	}
	/**
	 * 
	 * @param httpStatus
	 * @param isError
	 * @param message
	 * @param errorCode
	 * @param responseCode
	 * @return
	 */
	public static ResponseEntity<Object> responseWithoutData(HttpStatus httpStatus, Boolean isSuccess, String message,
			ErrorCode errorCode, ResponseCode responseCode) {

		Map<String, Object> map = new HashMap<>();
		map.put("timestamp", new Date().getTime());
		map.put("httpStatus", httpStatus.value());
		map.put("isSuccess", isSuccess);

		if (!isSuccess) {
			Error error = new Error(message, errorCode, message);
			map.put("error", error);
			map.put("errorStatus", errorCode.value());
		}
		map.put("message", message);
		map.put("responseCode", responseCode.value());
		return new ResponseEntity<>(map, httpStatus);

	}


}
