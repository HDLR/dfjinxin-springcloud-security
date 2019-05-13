package com.dfjinxin.common.exception.auth;


import com.dfjinxin.common.constant.CommonConstants;
import com.dfjinxin.common.exception.BaseException;


public class UserTokenException extends BaseException {
    public UserTokenException(String message) {
        super(message, CommonConstants.EX_USER_INVALID_CODE);
    }
}
