package com.dfjinxin.common.handler;

import com.dfjinxin.common.constant.CommonConstants;
import com.dfjinxin.common.exception.BaseException;
import com.dfjinxin.common.exception.auth.*;
//import com.dfjinxin.common.msg.BaseResponse;
import com.dfjinxin.common.msg.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by ace on 2017/9/8.
 */
@ControllerAdvice("com.dfjinxin")
@ResponseBody
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ClientTokenException.class)
    public R clientTokenExceptionHandler(HttpServletResponse response, ClientTokenException ex) {
        response.setStatus(403);
        logger.error(ex.getMessage(),ex);
//        return new BaseResponse(ex.getStatus(), ex.getMessage());
        return R.error(403, ex.getMessage());
    }

    @ExceptionHandler(UserTokenException.class)
    public R userTokenExceptionHandler(HttpServletResponse response, UserTokenException ex) {
        response.setStatus(200);
        logger.error(ex.getMessage(),ex);
        return R.error(200, ex.getMessage());
    }

    @ExceptionHandler(UserInvalidException.class)
    public R userInvalidExceptionHandler(HttpServletResponse response, UserInvalidException ex) {
        response.setStatus(200);
        logger.error(ex.getMessage(),ex);
        return R.error(200, ex.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    public R baseExceptionHandler(HttpServletResponse response, BaseException ex) {
        logger.error(ex.getMessage(),ex);
        response.setStatus(500);
        return R.error(500, ex.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class)
    public R authorizationExceptionHandler(AuthorizationException ex) {
        return R.error(500, "无此操作的权限，请联系管理员。");
    }

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(RRException.class)
    public R handleRRException(RRException e){
        R r = new R();
        r.put("code", e.getCode());
        r.put("msg", e.getMessage());

        return r;
    }

//    @ExceptionHandler(Exception.class)
//    public R otherExceptionHandler(HttpServletResponse response, Exception ex) {
//        response.setStatus(500);
//        logger.error(ex.getMessage(),ex);
//        return R.error(CommonConstants.EX_OTHER_CODE, ex.getMessage());
//    }

    @ExceptionHandler(Exception.class)
    public R handleException(Exception e){
        logger.error(e.getMessage(), e);
        return R.error();
    }

}
