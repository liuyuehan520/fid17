package com.fdi17.common.exception;

import com.fdi17.api.common.enums.BaseErrorCode;
import com.fdi17.api.common.mmon.SingleResponse;
import com.fdi17.api.common.utls.BeanUtils;
import com.fdi17.common.domain.HttpStatus;
import com.fdi17.common.domain.ResultMsg;
import com.fdi17.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 * 
 * @author wuming
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/**
	 * 权限校验异常
	 */
	@ExceptionHandler(AccessDeniedException.class)
	public ResultMsg handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		log.error("请求地址'{}',权限校验失败'{}'", requestURI, e.getMessage());
		return ResultMsg.error(HttpStatus.FORBIDDEN, "没有权限，请联系管理员授权");
	}

	/**
	 * 请求方式不支持
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResultMsg handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
			HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		log.error("请求地址'{}',不支持'{}'请求-----:{}", requestURI, e.getMethod(),e.getMessage());
		return ResultMsg.error(BaseErrorCode.COMMON_ERROR.code(),"请求方式不支持");
	}

	/**
	 * 业务异常
	 */
	@ExceptionHandler(ServiceException.class)
	public ResultMsg handleServiceException(ServiceException e, HttpServletRequest request) {
		log.error(e.getMessage(), e);
		Integer code = e.getCode();
		return StringUtils.isNotNull(code) ? ResultMsg.error(code, e.getMessage()) : ResultMsg.error(e.getMessage());
	}


	/**
	 * 系统异常
	 */
//	@ExceptionHandler(Exception.class)
//	public ResultMsg handleException(Exception e, HttpServletRequest request) {
//		String requestURI = request.getRequestURI();
//		log.error("请求地址'{}',发生系统异常.", requestURI, e);
//		return ResultMsg.error(e.getMessage());
//	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity<SingleResponse> processException(Exception ex) {

		ResponseStatus responseStatus = AnnotationUtils
				.findAnnotation(ex.getClass(), ResponseStatus.class);
		ResponseEntity.BodyBuilder builder = ResponseEntity.status(200);
		log.error("error,Exception={}", ExceptionUtils.getStackTrace(ex));
		if (responseStatus != null) {
			log.error("系统错误：code:{}, ** Exception-Message: {}", responseStatus.reason(), ex.getCause());
		}
		BaseErrorCode baseErrorCode = BeanUtils.convertBean(ex, BaseErrorCode.class);
		if (!"0".equals(baseErrorCode.getCode())) {
			return builder.body(SingleResponse.buildFailure(String.valueOf(baseErrorCode.getCode()), baseErrorCode.getMsg()));
		}
		log.error("系统错误：code:{}, ** Exception-Message: {}", ex.getCause(), ex.getMessage());
		return builder.body(SingleResponse.buildFailure("1", BaseErrorCode.COMMON_ERROR.getMsg()));
	}



	@ExceptionHandler(RongTaskException.class)
	@ResponseBody
	public ResponseEntity<SingleResponse> processValidationError(RongTaskException ex) {
		ResponseEntity.BodyBuilder builder = ResponseEntity.status(200);
		log.error("系统业务异常：{}", ex.getMessage());
		BaseErrorCode loginErrorCodeEnum = BaseErrorCode.findByCode(String.valueOf(ex.getCode()));
		if (loginErrorCodeEnum != null) {
			return builder.body(SingleResponse.buildFailure(String.valueOf(ex.getCode()), ex.getMessage()));
		}
		return builder.body(SingleResponse.buildFailure("1", ex.getMessage()));
	}


	/**
	 * 拦截未知的运行时异常
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResultMsg handleRuntimeException(RuntimeException e, HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		log.error("请求地址'{}',发生未知异常.", requestURI, e);
		return ResultMsg.error(e.getMessage());
	}



	/**
	 * 自定义验证异常
	 */
	@ExceptionHandler(BindException.class)
	public ResultMsg handleBindException(BindException e) {
		log.error(e.getMessage(), e);
		String message = e.getAllErrors().get(0).getDefaultMessage();
		return ResultMsg.error(message);
	}

	/**
	 * 自定义验证异常
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error(e.getMessage(), e);
		String message = e.getBindingResult().getFieldError().getDefaultMessage();
		return ResultMsg.error(message);
	}

	/**
	 * 演示模式异常
	 */
	@ExceptionHandler(DemoModeException.class)
	public ResultMsg handleDemoModeException(DemoModeException e) {
		return ResultMsg.error("演示模式，不允许操作");
	}
}
