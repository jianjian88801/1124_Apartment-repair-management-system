package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.GaipaishenqingEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.GaipaishenqingVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.GaipaishenqingView;


/**
 * 改派申请
 *
 * @author 
 * @email 
 * @date 2022-03-15 15:27:22
 */
public interface GaipaishenqingService extends IService<GaipaishenqingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<GaipaishenqingVO> selectListVO(Wrapper<GaipaishenqingEntity> wrapper);
   	
   	GaipaishenqingVO selectVO(@Param("ew") Wrapper<GaipaishenqingEntity> wrapper);
   	
   	List<GaipaishenqingView> selectListView(Wrapper<GaipaishenqingEntity> wrapper);
   	
   	GaipaishenqingView selectView(@Param("ew") Wrapper<GaipaishenqingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<GaipaishenqingEntity> wrapper);
   	

}

