package com.dfjinxin.gate.feign;

import com.dfjinxin.api.vo.log.LogInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("${admin.application.name}")
public interface ILogService {
  @RequestMapping(value="/api/log/save",method = RequestMethod.POST)
  void saveLog(LogInfo info);
}
