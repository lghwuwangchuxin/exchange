/**
 * %项目描述%
 * %版本信息%
 */
package com.jsdz.exchange.marker;

import java.util.Date;

import com.jsdz.core.AbstractDTO;

/**
 * @类名: TableField
 * @说明: 表字段
 * 
 * @author leehom
 * @Date 2012-6-4 下午04:58:38
 * @修改记录：
 * 
 * @see
 */
public class MarkerField extends AbstractDTO {
	
	/** 名称&值*/
	private String name;
	/** 整数值&日期值&字符型值*/
	private Long nvalue;
	private Date dvalue;
	private String svalue;
	/** 说明*/
	private String comments;
	/** 类型*/
	private MarkerType type;
	/** 是否自增*/
	private boolean isAutoInc;
	
	
	public MarkerField() {
		super();
	}

	public MarkerField(String name, Long nvalue) {
		super();
		this.name = name;
		this.nvalue = nvalue;
	}
	
	public MarkerField(String name, Date dvalue) {
		super();
		this.name = name;
		this.dvalue = dvalue;
	}
	
	public MarkerField(String name, String svalue) {
		super();
		this.name = name;
		this.svalue = svalue;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the isAutoInc
	 */
	public boolean isAutoInc() {
		return isAutoInc;
	}
	/**
	 * @param isAutoInc the isAutoInc to set
	 */
	public void setAutoInc(boolean isAutoInc) {
		this.isAutoInc = isAutoInc;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getDvalue() {
		return dvalue;
	}
	public void setDvalue(Date dvalue) {
		this.dvalue = dvalue;
	}
	public Long getNvalue() {
		return nvalue;
	}
	public void setNvalue(Long nvalue) {
		this.nvalue = nvalue;
	}

	public String getSvalue() {
		return svalue;
	}

	public void setSvalue(String svalue) {
		this.svalue = svalue;
	}

	public MarkerType getType() {
		return type;
	}

	public void setType(MarkerType type) {
		this.type = type;
	}
	
}

