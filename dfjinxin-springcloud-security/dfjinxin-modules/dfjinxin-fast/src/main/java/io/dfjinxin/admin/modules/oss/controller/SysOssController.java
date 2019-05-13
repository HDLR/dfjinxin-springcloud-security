/**
 * 2019 东方金信
 *
 *
 *
 *
 */

package io.dfjinxin.admin.modules.oss.controller;

import com.dfjinxin.common.msg.R;
import com.google.gson.Gson;
import com.dfjinxin.common.exception.auth.RRException;
import io.dfjinxin.admin.common.utils.ConfigConstant;
import io.dfjinxin.admin.common.utils.Constant;
import io.dfjinxin.admin.common.utils.PageUtils;
import io.dfjinxin.admin.common.validator.ValidatorUtils;
import io.dfjinxin.admin.common.validator.group.AliyunGroup;
import io.dfjinxin.admin.common.validator.group.QcloudGroup;
import io.dfjinxin.admin.common.validator.group.QiniuGroup;
import io.dfjinxin.admin.modules.oss.cloud.CloudStorageConfig;
import io.dfjinxin.admin.modules.oss.cloud.OSSFactory;
import io.dfjinxin.admin.modules.oss.entity.SysOssEntity;
import io.dfjinxin.admin.modules.sys.service.SysConfigService;
import io.dfjinxin.admin.common.utils.ConfigConstant;
import io.dfjinxin.admin.common.utils.Constant;
import io.dfjinxin.admin.common.utils.PageUtils;
import io.dfjinxin.admin.common.validator.ValidatorUtils;
import io.dfjinxin.admin.common.validator.group.AliyunGroup;
import io.dfjinxin.admin.common.validator.group.QcloudGroup;
import io.dfjinxin.admin.common.validator.group.QiniuGroup;
import io.dfjinxin.admin.modules.oss.cloud.CloudStorageConfig;
import io.dfjinxin.admin.modules.oss.cloud.OSSFactory;
import io.dfjinxin.admin.modules.oss.entity.SysOssEntity;
import io.dfjinxin.admin.modules.oss.service.SysOssService;
import io.dfjinxin.admin.modules.sys.service.SysConfigService;
import com.dfjinxin.auth.client.annotation.AuthorityPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 文件上传
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("sys/oss")
public class SysOssController {
	@Autowired
	private SysOssService sysOssService;
    @Autowired
    private SysConfigService sysConfigService;

    private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	@AuthorityPermission("sys:oss:all")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = sysOssService.queryPage(params);

		return R.ok().put("page", page);
	}


    /**
     * 云存储配置信息
     */
    @GetMapping("/config")
    @AuthorityPermission("sys:oss:all")
    public R config(){
        CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);

        return R.ok().put("config", config);
    }


	/**
	 * 保存云存储配置信息
	 */
	@PostMapping("/saveConfig")
	@AuthorityPermission("sys:oss:all")
	public R saveConfig(@RequestBody CloudStorageConfig config){
		//校验类型
		ValidatorUtils.validateEntity(config);

		if(config.getType() == Constant.CloudService.QINIU.getValue()){
			//校验七牛数据
			ValidatorUtils.validateEntity(config, QiniuGroup.class);
		}else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
			//校验阿里云数据
			ValidatorUtils.validateEntity(config, AliyunGroup.class);
		}else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
			//校验腾讯云数据
			ValidatorUtils.validateEntity(config, QcloudGroup.class);
		}

        sysConfigService.updateValueByKey(KEY, new Gson().toJson(config));

		return R.ok();
	}
	

	/**
	 * 上传文件
	 */
	@PostMapping("/upload")
	@AuthorityPermission("sys:oss:all")
	public R upload(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			throw new RRException("上传文件不能为空");
		}

		//上传文件
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String url = OSSFactory.build().uploadSuffix(file.getBytes(), suffix);

		//保存文件信息
		SysOssEntity ossEntity = new SysOssEntity();
		ossEntity.setUrl(url);
		ossEntity.setCreateDate(new Date());
		sysOssService.save(ossEntity);

		return R.ok().put("url", url);
	}


	/**
	 * 删除
	 */
	@PostMapping("/delete")
	@AuthorityPermission("sys:oss:all")
	public R delete(@RequestBody Long[] ids){
		sysOssService.removeByIds(Arrays.asList(ids));

		return R.ok();
	}

}
