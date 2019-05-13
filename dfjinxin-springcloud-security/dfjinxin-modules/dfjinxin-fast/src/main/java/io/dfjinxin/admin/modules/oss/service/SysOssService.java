/**
 * 2019 东方金信
 *
 *
 *
 *
 */

package io.dfjinxin.admin.modules.oss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.dfjinxin.admin.common.utils.PageUtils;
import io.dfjinxin.admin.modules.oss.entity.SysOssEntity;
import io.dfjinxin.admin.common.utils.PageUtils;
import io.dfjinxin.admin.modules.oss.entity.SysOssEntity;

import java.util.Map;

/**
 * 文件上传
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysOssService extends IService<SysOssEntity> {

	PageUtils queryPage(Map<String, Object> params);
}
