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

import com.entity.ZhuhuEntity;
import com.entity.view.ZhuhuView;

import com.service.ZhuhuService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.EmailregistercodeService;
import com.entity.EmailregistercodeEntity;

/**
 * 住户
 * 后端接口
 * @author 
 * @email 
 * @date 2022-03-15 15:27:22
 */
@RestController
@RequestMapping("/zhuhu")
public class ZhuhuController {
    @Autowired
    private ZhuhuService zhuhuService;
    @Autowired
    private EmailregistercodeService emailregistercodeService;


    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		ZhuhuEntity user = zhuhuService.selectOne(new EntityWrapper<ZhuhuEntity>().eq("zhuhuzhanghao", username));
		if(user==null || !user.getMima().equals(password)) {
			return R.error("账号或密码不正确");
		}
		
		String token = tokenService.generateToken(user.getId(), username,"zhuhu",  "住户" );
		return R.ok().put("token", token);
	}
	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody ZhuhuEntity zhuhu, @RequestParam(required = false) String emailcode){
    	//ValidatorUtils.validateEntity(zhuhu);
    	ZhuhuEntity user = zhuhuService.selectOne(new EntityWrapper<ZhuhuEntity>().eq("zhuhuzhanghao", zhuhu.getZhuhuzhanghao()));
		if(user!=null) {
			return R.error("注册用户已存在");
		}
                //校验邮箱验证码
                user =zhuhuService.selectOne(new EntityWrapper<ZhuhuEntity>().eq("email", zhuhu.getEmail()));
                if(user!=null) {
                        return R.error("邮箱已被注册");
                }
                List<EmailregistercodeEntity> emailregistercodeList = emailregistercodeService.selectList(
                                                                                new EntityWrapper<EmailregistercodeEntity>().eq("role", "用户")
                                                                                .eq("email", zhuhu.getEmail()).orderBy("addtime", false));
                boolean emailValidate = false;
                if(emailregistercodeList!=null && emailregistercodeList.size()>0) {
                        if(emailregistercodeList.get(0).getCode().equals(emailcode)) {
                                emailValidate = true;
                        }
                }
                if(!emailValidate) return R.error("邮箱验证码不正确");
		Long uId = new Date().getTime();
		zhuhu.setId(uId);
        zhuhuService.insert(zhuhu);
        return R.ok();
    }

        /**
     * 发送邮件
     */
        @IgnoreAuth
    @RequestMapping("/sendemail")
    public R sendemail(@RequestParam String email){
                String code = CommonUtil.getRandomNumber(4);
                EmailregistercodeEntity emailregistercode = new EmailregistercodeEntity();
                emailregistercode.setCode(code);
                emailregistercode.setRole("用户");
                emailregistercode.setEmail(email);
                emailregistercodeService.insert(emailregistercode);
                CommonUtil.sendEmail(email, "用户注册","您的注册验证码是【"+code+"】，请不要把验证码泄漏给其他人，如非本人请勿操作。");
        return R.ok();
    }
	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        ZhuhuEntity user = zhuhuService.selectById(id);
        return R.ok().put("data", user);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	ZhuhuEntity user = zhuhuService.selectOne(new EntityWrapper<ZhuhuEntity>().eq("zhuhuzhanghao", username));
    	if(user==null) {
    		return R.error("账号不存在");
    	}
        user.setMima("123456");
        zhuhuService.updateById(user);
        return R.ok("密码已重置为：123456");
    }


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZhuhuEntity zhuhu,
		HttpServletRequest request){
        EntityWrapper<ZhuhuEntity> ew = new EntityWrapper<ZhuhuEntity>();
		PageUtils page = zhuhuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhuhu), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZhuhuEntity zhuhu, 
		HttpServletRequest request){
        EntityWrapper<ZhuhuEntity> ew = new EntityWrapper<ZhuhuEntity>();
		PageUtils page = zhuhuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhuhu), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZhuhuEntity zhuhu){
       	EntityWrapper<ZhuhuEntity> ew = new EntityWrapper<ZhuhuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zhuhu, "zhuhu")); 
        return R.ok().put("data", zhuhuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhuhuEntity zhuhu){
        EntityWrapper< ZhuhuEntity> ew = new EntityWrapper< ZhuhuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zhuhu, "zhuhu")); 
		ZhuhuView zhuhuView =  zhuhuService.selectView(ew);
		return R.ok("查询住户成功").put("data", zhuhuView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZhuhuEntity zhuhu = zhuhuService.selectById(id);
        return R.ok().put("data", zhuhu);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZhuhuEntity zhuhu = zhuhuService.selectById(id);
        return R.ok().put("data", zhuhu);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZhuhuEntity zhuhu, HttpServletRequest request){
    	zhuhu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(zhuhu);
    	ZhuhuEntity user = zhuhuService.selectOne(new EntityWrapper<ZhuhuEntity>().eq("zhuhuzhanghao", zhuhu.getZhuhuzhanghao()));
		if(user!=null) {
			return R.error("用户已存在");
		}
		zhuhu.setId(new Date().getTime());
        zhuhuService.insert(zhuhu);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZhuhuEntity zhuhu, HttpServletRequest request){
    	zhuhu.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(zhuhu);
    	ZhuhuEntity user = zhuhuService.selectOne(new EntityWrapper<ZhuhuEntity>().eq("zhuhuzhanghao", zhuhu.getZhuhuzhanghao()));
		if(user!=null) {
			return R.error("用户已存在");
		}
		zhuhu.setId(new Date().getTime());
        zhuhuService.insert(zhuhu);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ZhuhuEntity zhuhu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhuhu);
        zhuhuService.updateById(zhuhu);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zhuhuService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<ZhuhuEntity> wrapper = new EntityWrapper<ZhuhuEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = zhuhuService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	







}
