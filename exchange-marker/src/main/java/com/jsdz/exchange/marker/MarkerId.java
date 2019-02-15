/**
 * %项目描述%
 * %版本信息%
 */
package com.jsdz.exchange.marker;

import com.jsdz.core.AbstractDTO;

/**
 * @类名: MarkerId
 * @说明: marker主键类
 * 
 * @author leehom
 * @Date 2012-6-27 下午4:16:49
 * @修改记录：
 * 
 * @see
 */
public class MarkerId extends AbstractDTO {

	/** marker拥有者*/
	private String owner;
	/** 表名称*/
	private String tableName;
	
	public MarkerId() {
		super();

	}
	
	/**
	 * @param owner
	 * @param tableName
	 */
	public MarkerId(String owner, String tableName) {
		super();
		this.owner = owner;
		this.tableName = tableName;
	}
	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
	
}
