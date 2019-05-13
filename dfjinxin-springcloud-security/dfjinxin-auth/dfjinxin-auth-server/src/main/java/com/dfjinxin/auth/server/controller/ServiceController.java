package com.dfjinxin.auth.server.controller;

import com.dfjinxin.auth.server.biz.ClientBiz;
import com.dfjinxin.auth.server.entity.Client;
import com.dfjinxin.auth.server.entity.ClientService;
import com.dfjinxin.common.rest.BaseController;
import org.springframework.web.bind.annotation.*;
import com.dfjinxin.common.msg.R;


@RestController
@RequestMapping("service")
public class ServiceController extends BaseController<ClientBiz,Client>{

    @RequestMapping(value = "/{id}/client", method = RequestMethod.PUT)
    @ResponseBody
    public R modifyUsers(@PathVariable int id, String clients){
        baseBiz.modifyClientServices(id, clients);
        return R.ok();
    }

    @RequestMapping(value = "/{id}/client", method = RequestMethod.GET)
    @ResponseBody
    public R getUsers(@PathVariable int id){
        return R.ok().put("data", baseBiz.getClientServices(id));
    }

}
