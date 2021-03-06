package com.xixi.springbootannocation.advice;

import com.xixi.springbootannocation.exceptions.CheckException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

/**
 * 异常处理切面
 */
@ControllerAdvice
public class CheckAdvice {

    /**
     * 处理数据校验异常
     *
     * @param e 异常
     * @return ResponseEntity
     */
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<String> handleBindException(
            WebExchangeBindException e) {
        return new ResponseEntity<String>(toStr(e), HttpStatus.BAD_REQUEST);
    }

    /**
     * 处理自定义异常
     *
     * @param e 异常
     * @return ResponseEntity
     */
    @ExceptionHandler(CheckException.class)
    public ResponseEntity<String> handleCheckException(
            CheckException e) {
        return new ResponseEntity<String>(toStr(e), HttpStatus.BAD_REQUEST);
    }

    /**
     * 转化为异常字符串，返回给前端
     *
     * @param e 异常
     * @return 异常字符串
     */
    private String toStr(CheckException e) {
        return e.getFieldName() + ": 错误的值 " + e.getFieldValue();
    }

    /**
     * 把校验异常转换为字符串
     *
     * @param ex 异常
     * @return 异常字符串
     */
    private String toStr(WebExchangeBindException ex) {
        return ex.getFieldErrors().stream()
                .map(e -> e.getField() + ":" + e.getDefaultMessage())
                .reduce("", (s1, s2) -> s1 + "\n" + s2);
    }

}
