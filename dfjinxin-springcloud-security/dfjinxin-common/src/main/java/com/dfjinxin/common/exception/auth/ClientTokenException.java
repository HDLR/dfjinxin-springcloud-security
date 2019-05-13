package com.dfjinxin.common.exception.auth;


import com.dfjinxin.common.constant.CommonConstants;
import com.dfjinxin.common.exception.BaseException;


public class ClientTokenException extends BaseException {
    public ClientTokenException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
