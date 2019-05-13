//package com.dfjinxin.gate.feign;
//
//import com.dfjinxin.api.vo.authority.PermissionInfo;
//import com.dfjinxin.gate.fallback.UserServiceFallback;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.util.List;
//
//
//@FeignClient(value = "dfjinxin-fast",fallback = UserServiceFallback.class)
//public interface IUserService {
//  @RequestMapping(value="/api/user/un/{username}/permissions",method = RequestMethod.GET)
//  public List<PermissionInfo> getPermissionByUsername(@PathVariable("username") String username);
//  @RequestMapping(value="/api/permissions",method = RequestMethod.GET)
//  List<PermissionInfo> getAllPermissionInfo();
//}
