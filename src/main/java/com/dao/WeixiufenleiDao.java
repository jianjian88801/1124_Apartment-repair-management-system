package com.dao;

import com.entity.WeixiufenleiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.WeixiufenleiVO;
import com.entity.view.WeixiufenleiView;


/**
 * 维修分类
 * 
 * @author 
 * @email 
 * @date 2022-03-15 15:27:22
 */
public interface WeixiufenleiDao extends BaseMapper<WeixiufenleiEntity> {
	
	List<WeixiufenleiVO> selectListVO(@Param("ew") Wrapper<WeixiufenleiEntity> wrapper);
	
	WeixiufenleiVO selectVO(@Param("ew") Wrapper<WeixiufenleiEntity> wrapper);
	
	List<WeixiufenleiView> selectListView(@Param("ew") Wrapper<WeixiufenleiEntity> wrapper);

	List<WeixiufenleiView> selectListView(Pagination page,@Param("ew") Wrapper<WeixiufenleiEntity> wrapper);
	
	WeixiufenleiView selectView(@Param("ew") Wrapper<WeixiufenleiEntity> wrapper);
	

}
