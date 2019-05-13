//package com.dfjinxin.gate.fallback;
//
//import com.dfjinxin.api.vo.authority.PermissionInfo;
//import com.dfjinxin.gate.feign.IUserService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PathVariable;
//import java.util.List;
//
//@Service
//@Slf4j
//public class UserServiceFallback implements IUserService {
//    @Override
//    public List<PermissionInfo> getPermissionByUsername(@PathVariable("username") String username) {
//        log.error("调用{}异常{}","getPermissionByUsername",username);
//        return null;
//    }
//
//    @Override
//    public List<PermissionInfo> getAllPermissionInfo() {
//        log.error("调用{}异常","getPermissionByUsername");
//        return null;
//    }
//}
