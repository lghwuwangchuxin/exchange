/**
 * %%
 * %%
 */
package com.jsdz.exchange.check.report;

import com.jsdz.core.AbstractDTO;

/**
 * @类名: DataReportItem
 * @说明: 数据检验报告项
 *        
 *
 * @author   leehom
 * @Date	 2017年8月3日 下午6:15:56
 * 修改记录：
 *
 * @see 	 
 */
public class DataReportItem<V> extends AbstractDTO {
	
	/** Id*/
	private Long id;
	/** 表*/
	private String table;
	/** 报告项*/
	private String item;
	/** 问题类型*/
	private IssueType type;
	/** 是否严重错误*/
	private boolean isFatal;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public IssueType getType() {
		return type;
	}
	public void setType(IssueType type) {
		this.type = type;
	}
	public boolean isFatal() {
		return isFatal;
	}
	public void setFatal(boolean isFatal) {
		this.isFatal = isFatal;
	}
	
}
