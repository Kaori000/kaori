package com.kaori.modules.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author lfh
 * @email kaoriii@163.com
 * @date 2022-02-18 11:06:43
 */
@Data
@TableName("job")
public class JobEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.UUID)
	private String id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String intro;
	/**
	 * 
	 */
	private String jobRequirement;
	/**
	 * 
	 */
	private String professionalRequirements;
	/**
	 * 
	 */
	private Integer demandNum;
	/**
	 * 
	 */
	private String companyId;

}
