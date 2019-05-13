/**
 * 2019 东方金信
 *
 *
 *
 *
 */

package io.dfjinxin.admin.modules.job.controller;

import com.dfjinxin.common.msg.R;
import io.dfjinxin.admin.common.utils.PageUtils;
import io.dfjinxin.admin.modules.job.entity.ScheduleJobLogEntity;
import io.dfjinxin.admin.modules.job.service.ScheduleJobLogService;
import io.dfjinxin.admin.common.utils.PageUtils;
import io.dfjinxin.admin.modules.job.entity.ScheduleJobLogEntity;
import io.dfjinxin.admin.modules.job.service.ScheduleJobLogService;
import com.dfjinxin.auth.client.annotation.AuthorityPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
	@Autowired
	private ScheduleJobLogService scheduleJobLogService;
	
	/**
	 * 定时任务日志列表
	 */
	@RequestMapping("/list")
	@AuthorityPermission("sys:schedule:log")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = scheduleJobLogService.queryPage(params);
		
		return R.ok().put("page", page);
	}
	
	/**
	 * 定时任务日志信息
	 */
	@RequestMapping("/info/{logId}")
	public R info(@PathVariable("logId") Long logId){
		ScheduleJobLogEntity log = scheduleJobLogService.getById(logId);
		
		return R.ok().put("log", log);
	}
}
