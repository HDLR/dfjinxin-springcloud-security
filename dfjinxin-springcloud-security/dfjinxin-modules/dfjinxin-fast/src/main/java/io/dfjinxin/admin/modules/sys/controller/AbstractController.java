/**
 * 2019 东方金信
 *
 *
 *
 *
 */

package io.dfjinxin.admin.modules.sys.controller;

import com.dfjinxin.auth.common.util.jwt.JWTInfo;
import com.dfjinxin.common.context.BaseContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected Long getUserId() {
		return Long.parseLong(BaseContextHandler.getUserID());
	}

	protected JWTInfo getUser() {
		return new JWTInfo(BaseContextHandler.getUsername(), BaseContextHandler.getUserID(), BaseContextHandler.getName());
	}
}
