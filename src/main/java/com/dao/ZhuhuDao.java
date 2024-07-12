package com.dao;

import com.entity.ZhuhuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ZhuhuVO;
import com.entity.view.ZhuhuView;


/**
 * 住户
 * 
 * @author 
 * @email 
 * @date 2022-03-15 15:27:22
 */
public interface ZhuhuDao extends BaseMapper<ZhuhuEntity> {
	
	List<ZhuhuVO> selectListVO(@Param("ew") Wrapper<ZhuhuEntity> wrapper);
	
	ZhuhuVO selectVO(@Param("ew") Wrapper<ZhuhuEntity> wrapper);
	
	List<ZhuhuView> selectListView(@Param("ew") Wrapper<ZhuhuEntity> wrapper);

	List<ZhuhuView> selectListView(Pagination page,@Param("ew") Wrapper<ZhuhuEntity> wrapper);
	
	ZhuhuView selectView(@Param("ew") Wrapper<ZhuhuEntity> wrapper);
	

}
