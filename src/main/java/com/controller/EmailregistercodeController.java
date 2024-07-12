package com.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.EmailregistercodeEntity;
import com.entity.view.EmailregistercodeView;

import com.service.EmailregistercodeService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 邮箱验证码
 * 后端接口
 * @author 
 * @email 
 * @date 2022-03-15 15:27:22
 */
@RestController
@RequestMapping("/emailregistercode")
public class EmailregistercodeController {
    @Autowired
    private EmailregistercodeService emailregistercodeService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,EmailregistercodeEntity emailregistercode,
		HttpServletRequest request){
        EntityWrapper<EmailregistercodeEntity> ew = new EntityWrapper<EmailregistercodeEntity>();
		PageUtils page = emailregistercodeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, emailregistercode), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,EmailregistercodeEntity emailregistercode, 
		HttpServletRequest request){
        EntityWrapper<EmailregistercodeEntity> ew = new EntityWrapper<EmailregistercodeEntity>();
		PageUtils page = emailregistercodeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, emailregistercode), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( EmailregistercodeEntity emailregistercode){
       	EntityWrapper<EmailregistercodeEntity> ew = new EntityWrapper<EmailregistercodeEntity>();
      	ew.allEq(MPUtil.allEQMapPre( emailregistercode, "emailregistercode")); 
        return R.ok().put("data", emailregistercodeService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(EmailregistercodeEntity emailregistercode){
        EntityWrapper< EmailregistercodeEntity> ew = new EntityWrapper< EmailregistercodeEntity>();
 		ew.allEq(MPUtil.allEQMapPre( emailregistercode, "emailregistercode")); 
		EmailregistercodeView emailregistercodeView =  emailregistercodeService.selectView(ew);
		return R.ok("查询邮箱验证码成功").put("data", emailregistercodeView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        EmailregistercodeEntity emailregistercode = emailregistercodeService.selectById(id);
        return R.ok().put("data", emailregistercode);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        EmailregistercodeEntity emailregistercode = emailregistercodeService.selectById(id);
        return R.ok().put("data", emailregistercode);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody EmailregistercodeEntity emailregistercode, HttpServletRequest request){
    	emailregistercode.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(emailregistercode);
        emailregistercodeService.insert(emailregistercode);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody EmailregistercodeEntity emailregistercode, HttpServletRequest request){
    	emailregistercode.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(emailregistercode);
        emailregistercodeService.insert(emailregistercode);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody EmailregistercodeEntity emailregistercode, HttpServletRequest request){
        //ValidatorUtils.validateEntity(emailregistercode);
        emailregistercodeService.updateById(emailregistercode);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        emailregistercodeService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);
		
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date()); 
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}
		
		Wrapper<EmailregistercodeEntity> wrapper = new EntityWrapper<EmailregistercodeEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = emailregistercodeService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	







}
