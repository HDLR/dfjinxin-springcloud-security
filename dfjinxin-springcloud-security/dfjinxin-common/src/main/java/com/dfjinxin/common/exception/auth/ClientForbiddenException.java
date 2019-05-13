package com.dfjinxin.common.exception.auth;


import com.dfjinxin.common.constant.CommonConstants;
import com.dfjinxin.common.exception.BaseException;


public class ClientForbiddenException extends BaseException {
    public ClientForbiddenException(String message) {
        super(message, CommonConstants.EX_CLIENT_FORBIDDEN_CODE);
    }

}
