/**
 * 2019 东方金信
 *
 *
 *
 *
 */

package io.dfjinxin.admin.modules.sys.controller;

import com.dfjinxin.api.vo.user.UserInfo;
import com.dfjinxin.auth.client.annotation.IgnoreUserToken;
import com.dfjinxin.common.constant.UserConstant;
import com.dfjinxin.common.msg.R;
import io.dfjinxin.admin.modules.sys.entity.SysUserEntity;
import io.dfjinxin.admin.modules.sys.service.SysUserService;
import io.dfjinxin.admin.modules.sys.entity.SysUserEntity;
import io.dfjinxin.admin.modules.sys.form.SysLoginForm;
import io.dfjinxin.admin.modules.sys.service.SysCaptchaService;
import io.dfjinxin.admin.modules.sys.service.SysUserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 登录相关
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
public class SysLoginController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysCaptchaService sysCaptchaService;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT);

	/**
	 * 验证码
	 */
	@IgnoreUserToken
	@GetMapping("captcha.jpg")
	public void captcha(HttpServletResponse response, String uuid)throws IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		//获取图片验证码
		BufferedImage image = sysCaptchaService.getCaptcha(uuid);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	/**
	 * 登录
	 */
	@IgnoreUserToken
	@PostMapping("/api/user/validate")
	public Map<String, Object> login(@RequestBody Map<String,String> body)throws IOException {
//		boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
//		if(!captcha){
//			return R.error("验证码不正确");
//		}

		//用户信息
		SysUserEntity user = sysUserService.queryByUserName(body.get("username"));

		//账号不存在、密码错误
		if(user == null || !(encoder.matches(body.get("password"), user.getPassword()))) {
			return R.error("账号或密码不正确");
		}

		//账号锁定
		if(user.getStatus() == 0){
			return R.error("账号已被锁定,请联系管理员");
		}

		UserInfo userInfo = new UserInfo();
		userInfo.setId(String.valueOf(user.getUserId()));
		userInfo.setUsername(user.getUsername());
		userInfo.setName(user.getUsername());
		userInfo.setPassword(user.getPassword());
//		userInfo.setDescription();
		R r = R.ok().put("data", userInfo);
		return r;
	}
}
