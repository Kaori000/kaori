package com.kaori.modules.sys.entity;

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
 * @date 2022-01-18 14:59:58
 */
@Data
@TableName("sys_login_log")
public class SysLoginLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.UUID)
	private String id;
	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	private String nickName;
	/**
	 * 
	 */
	private Date loginTime;
	/**
	 * 
	 */
	private String ip;

}
