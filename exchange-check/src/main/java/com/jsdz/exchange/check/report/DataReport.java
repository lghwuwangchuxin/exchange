/**
 * %%
 * %%
 */
package com.jsdz.exchange.check.report;

import java.util.Date;
import java.util.List;

import com.jsdz.core.AbstractDTO;

/**
 * @类名: DataReport
 * @说明: 数据检验报告
 *        
 *
 * @author   leehom
 * @Date	 2017年8月3日 下午6:15:56
 * 修改记录：
 *
 * @see 	 
 */
public class DataReport extends AbstractDTO {
	
	private Long id;
	/** 报告对应数据批次任务*/
	private String uuid;
	/** 报告项*/
	private List<DataReportItem> items;
	/** 报告生成日期*/
	private Date createDate;
	/** 报告反馈，数据提供方填写*/
	private String feedBack;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public List<DataReportItem> getItems() {
		return items;
	}
	public void setItems(List<DataReportItem> items) {
		this.items = items;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getFeedBack() {
		return feedBack;
	}
	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}
	
	
}
