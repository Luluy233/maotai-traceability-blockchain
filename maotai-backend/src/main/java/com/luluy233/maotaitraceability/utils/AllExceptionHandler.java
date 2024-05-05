package com.luluy233.maotaitraceability.utils;

import com.luluy233.maotaitraceability.vo.ErrorCode;
import com.luluy233.maotaitraceability.vo.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AllExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response doException(Exception ex) {
        ex.printStackTrace();
        return Response.fail(ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMsg());
    }
}
