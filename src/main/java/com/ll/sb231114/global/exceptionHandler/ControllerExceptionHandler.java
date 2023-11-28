package com.ll.sb231114.global.exceptionHandler;


import com.ll.sb231114.global.rq.Rq;
import com.ll.sb231114.standard.util.Ut;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
@RequiredArgsConstructor
public class ControllerExceptionHandler {
    private final Rq rq;

    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException ex) {

        return rq.historyBack(ex);
    }
}
