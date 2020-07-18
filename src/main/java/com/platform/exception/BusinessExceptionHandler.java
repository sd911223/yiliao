package com.platform.exception;

import com.platform.common.RestResponse;
import com.platform.common.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理器
 *
 * @author shitou
 */
@Slf4j
@RestControllerAdvice
public class BusinessExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BusinessException.class)
    public RestResponse handleBusinessException(BusinessException e) {
        RestResponse restResponse = new RestResponse();
        restResponse.setStatus(e.getCode());
        restResponse.setMsg(e.getMessage());

        return restResponse;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public RestResponse handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return ResultUtil.error(HttpStatus.NOT_FOUND.value(), "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public RestResponse handleDuplicateKeyException(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return ResultUtil.error(HttpStatus.NOT_FOUND.value(), "数据库中已存在该记录");
    }

    @ExceptionHandler(Exception.class)
    public RestResponse handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResultUtil.error();
    }

    /**
     * 参数校验异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public RestResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        log.error("参数校验异常，原因为：{}，=================== 码值为:{} 堆栈信息为：{}", ex.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.NOT_FOUND.value(), ex);
        return ResultUtil.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getBindingResult().getFieldError().getDefaultMessage());
    }
}
