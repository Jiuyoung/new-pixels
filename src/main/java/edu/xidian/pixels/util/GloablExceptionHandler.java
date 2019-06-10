package edu.xidian.pixels.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.xidian.pixels.VO.ResponseObject;

/**
 * GloablExceptionHandler
 */
@RestController
@ControllerAdvice
public class GloablExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseObject validException(Exception e) {
        ResponseObject o = ResponseObject.getFailResponse();
        MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        StringBuffer errorMsg = new StringBuffer("错误信息: ");
        for (ObjectError error : errors) {
            errorMsg.append(error.getDefaultMessage()).append(";");

        }
        o.setMessage(errorMsg.toString());
        return o;
    }
    // @ExceptionHandler(Exception.class)
    // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    // public Object handleException(Exception e) {
    //     String message = e.getMessage();
    //     ResponseObject o;
    //     if(StringUtils.isEmpty(message) || StringUtils.isBlank(message)) {
    //         o = ResponseObject.getFailResponse();
    //     }
    //     else {
    //         o = ResponseObject.getFailResponse(message);
    //     }
    //     return o;
    // }
}