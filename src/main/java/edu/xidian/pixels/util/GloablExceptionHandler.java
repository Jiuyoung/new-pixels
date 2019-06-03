package edu.xidian.pixels.util;


import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * GloablExceptionHandler
 */
@ControllerAdvice
public class GloablExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        String message = e.getMessage();
        ResponseObject o;
        if(StringUtils.isEmpty(message) || StringUtils.isBlank(message)) {
            o = ResponseObject.getFailResponse();
        }
        else {
            o = ResponseObject.getFailResponse(message);
        }
        return o;
    }
}