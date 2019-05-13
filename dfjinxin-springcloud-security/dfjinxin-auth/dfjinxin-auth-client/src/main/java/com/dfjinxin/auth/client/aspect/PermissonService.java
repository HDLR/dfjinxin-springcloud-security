/**
 * 2019 东方金信
 */

package com.dfjinxin.auth.client.aspect;

import java.util.Set;

public interface PermissonService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

}
