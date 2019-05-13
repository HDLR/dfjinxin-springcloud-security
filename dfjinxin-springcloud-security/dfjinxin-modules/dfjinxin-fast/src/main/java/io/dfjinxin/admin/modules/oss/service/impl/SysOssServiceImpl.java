/**
 * 2019 东方金信
 *
 *
 *
 *
 */

package io.dfjinxin.admin.modules.oss.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.dfjinxin.admin.common.utils.PageUtils;
import io.dfjinxin.admin.common.utils.Query;
import io.dfjinxin.admin.modules.oss.dao.SysOssDao;
import io.dfjinxin.admin.modules.oss.entity.SysOssEntity;
import io.dfjinxin.admin.common.utils.PageUtils;
import io.dfjinxin.admin.common.utils.Query;
import io.dfjinxin.admin.modules.oss.dao.SysOssDao;
import io.dfjinxin.admin.modules.oss.entity.SysOssEntity;
import io.dfjinxin.admin.modules.oss.service.SysOssService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("sysOssService")
public class SysOssServiceImpl extends ServiceImpl<SysOssDao, SysOssEntity> implements SysOssService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		IPage<SysOssEntity> page = this.page(
			new Query<SysOssEntity>().getPage(params)
		);

		return new PageUtils(page);
	}
	
}
