package com.expert.demo.dbswitcher.exception;

import com.expert.demo.dbswitcher.dto.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AppException.class)
    public R handleAppException(HttpServletRequest request, Exception ex) {
        log.error("AppException at {}", request.getRequestURI(), ex);
        return R.error(ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public R handleException(HttpServletRequest request, Exception ex) {
        log.error("Unknown Exception at {}", request.getRequestURI(), ex);
        return R.error(ex.getMessage());
    }
}
