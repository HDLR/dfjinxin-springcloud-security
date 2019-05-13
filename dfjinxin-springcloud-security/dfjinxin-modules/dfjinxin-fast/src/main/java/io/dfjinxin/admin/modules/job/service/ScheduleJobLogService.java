/**
 * 2019 东方金信
 *
 *
 *
 *
 */

package io.dfjinxin.admin.modules.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.dfjinxin.admin.common.utils.PageUtils;
import io.dfjinxin.admin.modules.job.entity.ScheduleJobLogEntity;
import io.dfjinxin.admin.common.utils.PageUtils;
import io.dfjinxin.admin.modules.job.entity.ScheduleJobLogEntity;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

	PageUtils queryPage(Map<String, Object> params);
	
}
