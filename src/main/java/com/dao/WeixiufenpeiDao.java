package com.dao;

import com.entity.WeixiufenpeiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.WeixiufenpeiVO;
import com.entity.view.WeixiufenpeiView;


/**
 * 维修分配
 * 
 * @author 
 * @email 
 * @date 2022-03-15 15:27:22
 */
public interface WeixiufenpeiDao extends BaseMapper<WeixiufenpeiEntity> {
	
	List<WeixiufenpeiVO> selectListVO(@Param("ew") Wrapper<WeixiufenpeiEntity> wrapper);
	
	WeixiufenpeiVO selectVO(@Param("ew") Wrapper<WeixiufenpeiEntity> wrapper);
	
	List<WeixiufenpeiView> selectListView(@Param("ew") Wrapper<WeixiufenpeiEntity> wrapper);

	List<WeixiufenpeiView> selectListView(Pagination page,@Param("ew") Wrapper<WeixiufenpeiEntity> wrapper);
	
	WeixiufenpeiView selectView(@Param("ew") Wrapper<WeixiufenpeiEntity> wrapper);
	

}
