/**
 * 2019 东方金信
 *
 *
 *
 *
 */

package com.dfjinxin.auth.client.aspect;

import com.dfjinxin.auth.client.annotation.AuthorityPermission;
import com.dfjinxin.auth.client.feign.AdminServiceFeign;
import com.dfjinxin.auth.client.utils.SpringContextUtils;
import com.dfjinxin.common.context.BaseContextHandler;
import com.dfjinxin.common.exception.auth.AuthorizationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Set;


/**
 * 权限判断，切面处理类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Aspect
@Component
@ConditionalOnProperty(prefix="auth.permission",name = "allow", havingValue = "true")
public class PermissionAspect {

	@Autowired
	private AdminServiceFeign adminServiceFeign;

	@Pointcut("@annotation(com.dfjinxin.auth.client.annotation.AuthorityPermission)")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		//执行方法
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();
		AuthorityPermission authorityPermission = method.getAnnotation(AuthorityPermission.class);
		String perssion = authorityPermission.value();

		long beginTime = System.currentTimeMillis();
		//判断权限是否有权限
		Set<String> permissions = adminServiceFeign.permisson(BaseContextHandler.getUserID());
		if(!(null != permissions && permissions.contains(perssion))){
			throw new AuthorizationException("无此操作的权限，请联系管理员。");
		}

		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		return result;
	}

}
