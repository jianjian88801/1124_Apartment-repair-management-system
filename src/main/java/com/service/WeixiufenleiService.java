package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.WeixiufenleiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.WeixiufenleiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.WeixiufenleiView;


/**
 * 维修分类
 *
 * @author 
 * @email 
 * @date 2022-03-15 15:27:22
 */
public interface WeixiufenleiService extends IService<WeixiufenleiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<WeixiufenleiVO> selectListVO(Wrapper<WeixiufenleiEntity> wrapper);
   	
   	WeixiufenleiVO selectVO(@Param("ew") Wrapper<WeixiufenleiEntity> wrapper);
   	
   	List<WeixiufenleiView> selectListView(Wrapper<WeixiufenleiEntity> wrapper);
   	
   	WeixiufenleiView selectView(@Param("ew") Wrapper<WeixiufenleiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<WeixiufenleiEntity> wrapper);
   	

}

