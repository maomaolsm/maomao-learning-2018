package com.maomao.controller;

import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by maomao on 2018/12/9.
 */
@ControllerAdvice
public class GeneralControllerAdvice {

    /**
     * 处理404
     *
     * 但 spring boot 使用了 {@link BasicErrorController}
     * 破坏原有的 spring web mvc 统一的处理方式
     *
     * @param throwable
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public String handle404(Throwable throwable) {
        return "haha";
    }

}
