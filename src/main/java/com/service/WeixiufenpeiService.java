package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.WeixiufenpeiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.WeixiufenpeiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.WeixiufenpeiView;


/**
 * 维修分配
 *
 * @author 
 * @email 
 * @date 2022-03-15 15:27:22
 */
public interface WeixiufenpeiService extends IService<WeixiufenpeiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<WeixiufenpeiVO> selectListVO(Wrapper<WeixiufenpeiEntity> wrapper);
   	
   	WeixiufenpeiVO selectVO(@Param("ew") Wrapper<WeixiufenpeiEntity> wrapper);
   	
   	List<WeixiufenpeiView> selectListView(Wrapper<WeixiufenpeiEntity> wrapper);
   	
   	WeixiufenpeiView selectView(@Param("ew") Wrapper<WeixiufenpeiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<WeixiufenpeiEntity> wrapper);
   	

}

