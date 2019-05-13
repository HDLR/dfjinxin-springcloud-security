/**
 * 2019 东方金信
 *
 *
 *
 *
 */

package io.dfjinxin.admin.modules.oss.cloud;


import io.dfjinxin.admin.common.utils.ConfigConstant;
import io.dfjinxin.admin.common.utils.Constant;
import com.dfjinxin.auth.client.utils.SpringContextUtils;
import io.dfjinxin.admin.modules.sys.service.SysConfigService;
import io.dfjinxin.admin.common.utils.ConfigConstant;
import io.dfjinxin.admin.common.utils.Constant;
import com.dfjinxin.auth.client.utils.SpringContextUtils;
import io.dfjinxin.admin.modules.sys.service.SysConfigService;

/**
 * 文件上传Factory
 *
 * @author Mark sunlightcs@gmail.com
 */
public final class OSSFactory {
    private static SysConfigService sysConfigService;

    static {
        OSSFactory.sysConfigService = (SysConfigService) SpringContextUtils.getBean("sysConfigService");
    }

    public static CloudStorageService build(){
        //获取云存储配置信息
        CloudStorageConfig config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
            return new AliyunCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
            return new QcloudCloudStorageService(config);
        }

        return null;
    }

}
