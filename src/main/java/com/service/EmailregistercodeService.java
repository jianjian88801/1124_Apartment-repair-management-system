package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.EmailregistercodeEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.EmailregistercodeVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.EmailregistercodeView;


/**
 * 邮箱验证码
 *
 * @author 
 * @email 
 * @date 2022-03-15 15:27:22
 */
public interface EmailregistercodeService extends IService<EmailregistercodeEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<EmailregistercodeVO> selectListVO(Wrapper<EmailregistercodeEntity> wrapper);
   	
   	EmailregistercodeVO selectVO(@Param("ew") Wrapper<EmailregistercodeEntity> wrapper);
   	
   	List<EmailregistercodeView> selectListView(Wrapper<EmailregistercodeEntity> wrapper);
   	
   	EmailregistercodeView selectView(@Param("ew") Wrapper<EmailregistercodeEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<EmailregistercodeEntity> wrapper);
   	

}

