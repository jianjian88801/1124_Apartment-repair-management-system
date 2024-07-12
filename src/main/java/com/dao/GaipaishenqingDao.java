package com.dao;

import com.entity.GaipaishenqingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.GaipaishenqingVO;
import com.entity.view.GaipaishenqingView;


/**
 * 改派申请
 * 
 * @author 
 * @email 
 * @date 2022-03-15 15:27:22
 */
public interface GaipaishenqingDao extends BaseMapper<GaipaishenqingEntity> {
	
	List<GaipaishenqingVO> selectListVO(@Param("ew") Wrapper<GaipaishenqingEntity> wrapper);
	
	GaipaishenqingVO selectVO(@Param("ew") Wrapper<GaipaishenqingEntity> wrapper);
	
	List<GaipaishenqingView> selectListView(@Param("ew") Wrapper<GaipaishenqingEntity> wrapper);

	List<GaipaishenqingView> selectListView(Pagination page,@Param("ew") Wrapper<GaipaishenqingEntity> wrapper);
	
	GaipaishenqingView selectView(@Param("ew") Wrapper<GaipaishenqingEntity> wrapper);
	

}
