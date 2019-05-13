/**
 * 2019 东方金信
 *
 *
 *
 *
 */

package io.dfjinxin.admin.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.dfjinxin.admin.common.utils.PageUtils;
import io.dfjinxin.admin.modules.sys.entity.SysLogEntity;
import io.dfjinxin.admin.common.utils.PageUtils;
import io.dfjinxin.admin.modules.sys.entity.SysLogEntity;

import java.util.Map;


/**
 * 系统日志
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
