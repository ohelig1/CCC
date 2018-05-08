package com.klu.error;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.klu.utility.Constants;

@ControllerAdvice
public class CCCExceptionHandler {
	
	@ExceptionHandler(CCCError.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<Object> cccException(Exception exception, HttpServletRequest request, CCCError cccError){
		Map<String,String> errorMap = new HashMap<String,String>();
		errorMap.put("id", String.valueOf(cccError.getErrorCode()));
		errorMap.put("reason",cccError.getErrorMessage());
		errorMap.put("url",request.getRequestURI());
		BodyBuilder bodyBuilder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
		ResponseEntity<Object> responseEntity = bodyBuilder.body(errorMap);
		return responseEntity;
	}
	
	@ExceptionHandler(UnknownHostException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<Object> cccUnknownException(Exception exception){
		Map<String,String> errorMap = new HashMap<String,String>();
		errorMap.put("id", String.valueOf(Constants.DEVICE_NOT_FOUND));
		errorMap.put("reason",exception.getMessage());
		errorMap.put("url","");
		BodyBuilder bodyBuilder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
		ResponseEntity<Object> responseEntity = bodyBuilder.body(errorMap);
		return responseEntity;
	}
}
