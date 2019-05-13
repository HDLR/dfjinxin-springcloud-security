/**
 * 2019 东方金信
 *
 *
 *
 *
 */

package io.dfjinxin.admin.modules.sys.controller;

import com.dfjinxin.auth.client.annotation.AuthorityPermission;
import com.dfjinxin.common.constant.UserConstant;
import com.dfjinxin.common.msg.R;
import io.dfjinxin.admin.common.utils.EncoderUtils;
import io.dfjinxin.admin.modules.sys.entity.SysUserEntity;
import io.dfjinxin.admin.modules.sys.form.PasswordForm;
import io.dfjinxin.admin.modules.sys.service.SysUserService;
import io.dfjinxin.admin.common.annotation.SysLog;
import io.dfjinxin.admin.common.utils.Constant;
import io.dfjinxin.admin.common.utils.PageUtils;
import io.dfjinxin.admin.common.validator.Assert;
import io.dfjinxin.admin.common.validator.ValidatorUtils;
import io.dfjinxin.admin.common.validator.group.AddGroup;
import io.dfjinxin.admin.common.validator.group.UpdateGroup;
import io.dfjinxin.admin.modules.sys.entity.SysUserEntity;
import io.dfjinxin.admin.modules.sys.form.PasswordForm;
import io.dfjinxin.admin.modules.sys.service.SysUserRoleService;
import io.dfjinxin.admin.modules.sys.service.SysUserService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;

	/**
	 * 所有用户列表
	 */
	@GetMapping("/list")
	@AuthorityPermission("sys:user:list")
	public R list(@RequestParam Map<String, Object> params){
		//只有超级管理员，才能查看所有管理员列表
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		PageUtils page = sysUserService.queryPage(params);

		return R.ok().put("page", page);
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	public R info(){
		return R.ok().put("user", getUser());
	}
	
	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@PostMapping("/password")
	public R password(@RequestBody PasswordForm form){
		Assert.isBlank(form.getNewPassword(), "新密码不为能空");
		
		//sha256加密
		String password = EncoderUtils.getInstance().encode(form.getPassword());
		//sha256加密
		String newPassword = EncoderUtils.getInstance().encode(form.getNewPassword());

		//更新密码
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(!flag){
			return R.error("原密码不正确");
		}
		
		return R.ok();
	}
	
	/**
	 * 用户信息
	 */
	@GetMapping("/info/{userId}")
	@AuthorityPermission("sys:user:info")
	public R info(@PathVariable("userId") Long userId){
		SysUserEntity user = sysUserService.getById(userId);
		
		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		
		return R.ok().put("user", user);
	}
	
	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@PostMapping("/save")
	@AuthorityPermission("sys:user:save")
	public R save(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, AddGroup.class);
		
		user.setCreateUserId(getUserId());
		sysUserService.saveUser(user);
		
		return R.ok();
	}
	
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@PostMapping("/update")
	@AuthorityPermission("sys:user:update")
	public R update(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);

		user.setCreateUserId(getUserId());
		sysUserService.update(user);
		
		return R.ok();
	}
	
	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@PostMapping("/delete")
	@AuthorityPermission("sys:user:delete")
	public R delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return R.error("当前用户不能删除");
		}
		
		sysUserService.deleteBatch(userIds);
		
		return R.ok();
	}

}
