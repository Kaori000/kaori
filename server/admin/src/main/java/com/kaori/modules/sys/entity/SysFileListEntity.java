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
 * @date 2022-01-18 14:59:59
 */
@Data
@TableName("sys_file_list")
public class SysFileListEntity implements Serializable {
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
	private String fileUuidName;
	/**
	 * 
	 */
	private String filePerPath;
	/**
	 * 
	 */
	private String fileLastPath;
	/**
	 * 
	 */
	private Long fileSize;
	/**
	 * 
	 */
	private String suffix;
	/**
	 * 
	 */
	private String userId;
	/**
	 * 
	 */
	private String contentType;
	/**
	 * 
	 */
	private Date createTime;

}
