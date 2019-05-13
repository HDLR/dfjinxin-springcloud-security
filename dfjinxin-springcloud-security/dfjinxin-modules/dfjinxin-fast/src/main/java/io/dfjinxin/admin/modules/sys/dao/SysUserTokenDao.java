/**
 * 2019 东方金信
 *
 *
 *
 *
 */

package io.dfjinxin.admin.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.dfjinxin.admin.modules.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserTokenEntity> {

    SysUserTokenEntity queryByToken(String token);
	
}
