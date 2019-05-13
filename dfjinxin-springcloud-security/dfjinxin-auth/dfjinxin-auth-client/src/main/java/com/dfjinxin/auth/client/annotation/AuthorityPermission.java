/**
 * 2019 东方金信
 *
 *
 *
 *
 */

package com.dfjinxin.auth.client.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author Mark sunlightcs@gmail.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthorityPermission {

	String value() default "";
}
