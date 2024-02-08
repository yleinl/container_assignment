package com.vu.exception;

import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.vu.model.R;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = ApiException.class)
    public R handlerApiException(ApiException exception){
        IErrorCode errorCode = exception.getErrorCode();
        if(errorCode!=null){
            return R.fail(errorCode) ;
        }
        return R.fail(exception.getMessage()) ;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public R handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        BindingResult bindingResult = exception.getBindingResult();
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError!=null){
                return R.fail(fieldError.getField()+fieldError.getDefaultMessage()) ;
            }
        }
        return R.fail(exception.getMessage()) ;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BindException.class)
    public R handlerBindException(BindException bindException){
        BindingResult bindingResult = bindException.getBindingResult();
        if(bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            if(fieldError!=null){
                return R.fail(fieldError.getField()+fieldError.getDefaultMessage()) ;
            }
        }
        return R.fail(bindException.getMessage()) ;
    }
}
