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
import io.dfjinxin.admin.common.validator.ValidatorUtils;
import io.dfjinxin.admin.modules.job.entity.ScheduleJobEntity;
import io.dfjinxin.admin.modules.job.service.ScheduleJobService;
import io.dfjinxin.admin.common.annotation.SysLog;
import io.dfjinxin.admin.common.utils.PageUtils;
import io.dfjinxin.admin.common.validator.ValidatorUtils;
import io.dfjinxin.admin.modules.job.entity.ScheduleJobEntity;
import io.dfjinxin.admin.modules.job.service.ScheduleJobService;
import com.dfjinxin.auth.client.annotation.AuthorityPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 定时任务
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {
	@Autowired
	private ScheduleJobService scheduleJobService;
	
	/**
	 * 定时任务列表
	 */
	@RequestMapping("/list")
	@AuthorityPermission("sys:schedule:list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = scheduleJobService.queryPage(params);

		return R.ok().put("page", page);
	}
	
	/**
	 * 定时任务信息
	 */
	@RequestMapping("/info/{jobId}")
	@AuthorityPermission("sys:schedule:info")
	public R info(@PathVariable("jobId") Long jobId){
		ScheduleJobEntity schedule = scheduleJobService.getById(jobId);
		
		return R.ok().put("schedule", schedule);
	}
	
	/**
	 * 保存定时任务
	 */
	@SysLog("保存定时任务")
	@RequestMapping("/save")
	@AuthorityPermission("sys:schedule:save")
	public R save(@RequestBody ScheduleJobEntity scheduleJob){
		ValidatorUtils.validateEntity(scheduleJob);
		
		scheduleJobService.saveJob(scheduleJob);
		
		return R.ok();
	}
	
	/**
	 * 修改定时任务
	 */
	@SysLog("修改定时任务")
	@RequestMapping("/update")
	@AuthorityPermission("sys:schedule:update")
	public R update(@RequestBody ScheduleJobEntity scheduleJob){
		ValidatorUtils.validateEntity(scheduleJob);
				
		scheduleJobService.update(scheduleJob);
		
		return R.ok();
	}
	
	/**
	 * 删除定时任务
	 */
	@SysLog("删除定时任务")
	@RequestMapping("/delete")
	@AuthorityPermission("sys:schedule:delete")
	public R delete(@RequestBody Long[] jobIds){
		scheduleJobService.deleteBatch(jobIds);
		
		return R.ok();
	}
	
	/**
	 * 立即执行任务
	 */
	@SysLog("立即执行任务")
	@RequestMapping("/run")
	@AuthorityPermission("sys:schedule:run")
	public R run(@RequestBody Long[] jobIds){
		scheduleJobService.run(jobIds);
		
		return R.ok();
	}
	
	/**
	 * 暂停定时任务
	 */
	@SysLog("暂停定时任务")
	@RequestMapping("/pause")
	@AuthorityPermission("sys:schedule:pause")
	public R pause(@RequestBody Long[] jobIds){
		scheduleJobService.pause(jobIds);
		
		return R.ok();
	}
	
	/**
	 * 恢复定时任务
	 */
	@SysLog("恢复定时任务")
	@RequestMapping("/resume")
	@AuthorityPermission("sys:schedule:resume")
	public R resume(@RequestBody Long[] jobIds){
		scheduleJobService.resume(jobIds);
		
		return R.ok();
	}

}
