package com.jsdz.exchange.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @类名: Organization
 * @说明: 组织机构
 *
 * @author: leehom
 * @Date	2017年4月24日
 * 修改记录：
 *
 * @see
 */
public class ExchangeEntity implements Serializable {

	/** */
	private Date createTime;
	private Date lastUpdateTime;
	
	// 从对象from获取属性值，即内转外
	public void assignFrom(Object from) {
		
	}
	
	// 赋值给to对象，即外传内
	public Object assignTo(Object to) {
		return to;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	
}
