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
@TableName("company")
public class CompanyEntity implements Serializable {
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
	private String address;
	/**
	 * 
	 */
	private String contacts;
	/**
	 * 
	 */
	private Integer contactsNum;
	/**
	 * 
	 */
	private String email;
	/**
	 * 
	 */
	private String website;
	/**
	 * 
	 */
	private String intro;
	/**
	 * 
	 */
	private Integer pass;
	/**
	 * 
	 */
	private Integer del;

}
