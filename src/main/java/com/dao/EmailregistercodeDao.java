package com.dao;

import com.entity.EmailregistercodeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.EmailregistercodeVO;
import com.entity.view.EmailregistercodeView;


/**
 * 邮箱验证码
 * 
 * @author 
 * @email 
 * @date 2022-03-15 15:27:22
 */
public interface EmailregistercodeDao extends BaseMapper<EmailregistercodeEntity> {
	
	List<EmailregistercodeVO> selectListVO(@Param("ew") Wrapper<EmailregistercodeEntity> wrapper);
	
	EmailregistercodeVO selectVO(@Param("ew") Wrapper<EmailregistercodeEntity> wrapper);
	
	List<EmailregistercodeView> selectListView(@Param("ew") Wrapper<EmailregistercodeEntity> wrapper);

	List<EmailregistercodeView> selectListView(Pagination page,@Param("ew") Wrapper<EmailregistercodeEntity> wrapper);
	
	EmailregistercodeView selectView(@Param("ew") Wrapper<EmailregistercodeEntity> wrapper);
	

}
