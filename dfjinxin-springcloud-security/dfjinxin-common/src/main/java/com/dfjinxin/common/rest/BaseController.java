package com.dfjinxin.common.rest;

import com.dfjinxin.common.biz.BaseBiz;
import com.dfjinxin.common.context.BaseContextHandler;
import com.dfjinxin.common.msg.R;
import com.dfjinxin.common.util.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Slf4j
public class BaseController<Biz extends BaseBiz,Entity> {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected Biz baseBiz;

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public R add(@RequestBody Entity entity){
        baseBiz.insertSelective(entity);
        return R.ok();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public R get(@PathVariable int id){
        Object o = baseBiz.selectById(id);
        return R.ok().put("data", o);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public R update(@RequestBody Entity entity){
        baseBiz.updateSelectiveById(entity);
        return R.ok();
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public R remove(@PathVariable int id){
        baseBiz.deleteById(id);
        return R.ok();
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public List<Entity> all(){
        return baseBiz.selectListAll();
    }
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        return R.ok().put("data", query);
    }

    public String getCurrentUserName(){
        return BaseContextHandler.getUsername();
    }
}
