package com.entity.view;

import com.entity.WeixiufenleiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 维修分类
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2022-03-15 15:27:22
 */
@TableName("weixiufenlei")
public class WeixiufenleiView  extends WeixiufenleiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public WeixiufenleiView(){
	}
 
 	public WeixiufenleiView(WeixiufenleiEntity weixiufenleiEntity){
 	try {
			BeanUtils.copyProperties(this, weixiufenleiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
