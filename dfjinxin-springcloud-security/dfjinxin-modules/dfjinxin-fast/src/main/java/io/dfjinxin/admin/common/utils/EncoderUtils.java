package io.dfjinxin.admin.common.utils;

import com.dfjinxin.common.constant.UserConstant;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncoderUtils {

    public static BCryptPasswordEncoder getInstance() {
        return EncoderEnum.INSTANCE.getInstance();
    }

    private enum EncoderEnum {
        INSTANCE;
        private BCryptPasswordEncoder encoder;

        EncoderEnum() {
            encoder = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT);
        }

        public BCryptPasswordEncoder getInstance() {
            return encoder;
        }
    }
}
