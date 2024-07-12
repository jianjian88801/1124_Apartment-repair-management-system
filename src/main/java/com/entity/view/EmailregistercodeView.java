package com.entity.view;

import com.entity.EmailregistercodeEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 邮箱验证码
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2022-03-15 15:27:22
 */
@TableName("emailregistercode")
public class EmailregistercodeView  extends EmailregistercodeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public EmailregistercodeView(){
	}
 
 	public EmailregistercodeView(EmailregistercodeEntity emailregistercodeEntity){
 	try {
			BeanUtils.copyProperties(this, emailregistercodeEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
