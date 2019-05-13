/**
 * 2019 东方金信
 */

package io.dfjinxin.admin.modules.sys.service;

import java.util.Set;

public interface PermissonService {
    /**
     * 获取用户权限列表
     */
    Set<String> getUserPermissions(long userId);

}
