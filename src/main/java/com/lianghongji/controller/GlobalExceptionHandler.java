package com.lianghongji.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.lianghongji.Exception.BusinessException;
import com.lianghongji.Exception.BusinessRuntimeException;
import com.lianghongji.Exception.CommonErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 统一异常处理
 * 
 * @author deng.huaiyu
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public CommonResult<String> jsonErrorHandler(Exception e) throws Exception {

		CommonResult<String> failResult = CommonResult.failResult();
		failResult.setMsg(e.getMessage());

		if (e instanceof BusinessException) {
			failResult.setResultCode(((BusinessException) e).getErrorCode());
		} else if (e instanceof BusinessRuntimeException) {
			failResult.setResultCode(((BusinessRuntimeException) e).getErrorCode());
		} else if (e instanceof HttpRequestMethodNotSupportedException) {
			failResult.setMsg("该方法不能使用这种请求方式，请按照文档调用");
			failResult.setResultCode(CommonErrorCode.UNKOWN.getCode());
			logger.error(" GlobalExceptionHandler "+ e.getMessage());
		} else if (e instanceof MissingServletRequestParameterException) {
			MissingServletRequestParameterException miss = (MissingServletRequestParameterException)e;
			failResult.setMsg("该方法调用缺少必要的参数 " + miss.getParameterName() + ", 请按照文档调用");
			failResult.setResultCode(CommonErrorCode.PRAMERROR.getCode());
			logger.error(" GlobalExceptionHandler " + e.getMessage());
		}else if (e instanceof InvalidFormatException) {
			failResult.setMsg("该方法参数类型错误，请按照文档调用");
			failResult.setResultCode(CommonErrorCode.PRAMERROR.getCode());
			logger.error(" GlobalExceptionHandler " + e.getMessage());
		}else if (e instanceof HttpMessageNotReadableException) {
			failResult.setMsg("该方法参数类型错误，请按照文档调用");
			failResult.setResultCode(CommonErrorCode.PRAMERROR.getCode());
			logger.error(" GlobalExceptionHandler " + e.getMessage()); 
		}else if (e instanceof HttpMediaTypeNotSupportedException) {
			failResult.setMsg("该方法Content-Type类型错误，请按照文档调用");
			failResult.setResultCode(CommonErrorCode.PRAMERROR.getCode());
			logger.error(" GlobalExceptionHandler " + e.getMessage()); 
		}else {
			failResult.setMsg(CommonErrorCode.UNKOWN.getMessage());
			failResult.setResultCode(CommonErrorCode.UNKOWN.getCode());
			logger.error(" GlobalExceptionHandler ", e);
		}
		return failResult;
	}
}
