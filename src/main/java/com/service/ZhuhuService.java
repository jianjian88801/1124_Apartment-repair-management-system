package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ZhuhuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ZhuhuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhuhuView;


/**
 * 住户
 *
 * @author 
 * @email 
 * @date 2022-03-15 15:27:22
 */
public interface ZhuhuService extends IService<ZhuhuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZhuhuVO> selectListVO(Wrapper<ZhuhuEntity> wrapper);
   	
   	ZhuhuVO selectVO(@Param("ew") Wrapper<ZhuhuEntity> wrapper);
   	
   	List<ZhuhuView> selectListView(Wrapper<ZhuhuEntity> wrapper);
   	
   	ZhuhuView selectView(@Param("ew") Wrapper<ZhuhuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZhuhuEntity> wrapper);
   	

}

