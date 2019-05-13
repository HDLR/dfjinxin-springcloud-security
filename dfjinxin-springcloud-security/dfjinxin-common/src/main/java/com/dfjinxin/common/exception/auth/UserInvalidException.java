package com.dfjinxin.common.exception.auth;


import com.dfjinxin.common.constant.CommonConstants;
import com.dfjinxin.common.exception.BaseException;


public class UserInvalidException extends BaseException {
    public UserInvalidException(String message) {
        super(message, CommonConstants.EX_USER_PASS_INVALID_CODE);
    }
}
