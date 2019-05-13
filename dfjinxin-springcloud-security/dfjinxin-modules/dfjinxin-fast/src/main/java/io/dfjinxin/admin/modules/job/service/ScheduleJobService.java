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
import io.dfjinxin.admin.modules.job.entity.ScheduleJobEntity;
import io.dfjinxin.admin.common.utils.PageUtils;
import io.dfjinxin.admin.modules.job.entity.ScheduleJobEntity;

import java.util.Map;

/**
 * 定时任务
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface ScheduleJobService extends IService<ScheduleJobEntity> {

	PageUtils queryPage(Map<String, Object> params);

	/**
	 * 保存定时任务
	 */
	void saveJob(ScheduleJobEntity scheduleJob);
	
	/**
	 * 更新定时任务
	 */
	void update(ScheduleJobEntity scheduleJob);
	
	/**
	 * 批量删除定时任务
	 */
	void deleteBatch(Long[] jobIds);
	
	/**
	 * 批量更新定时任务状态
	 */
	int updateBatch(Long[] jobIds, int status);
	
	/**
	 * 立即执行
	 */
	void run(Long[] jobIds);
	
	/**
	 * 暂停运行
	 */
	void pause(Long[] jobIds);
	
	/**
	 * 恢复运行
	 */
	void resume(Long[] jobIds);
}
