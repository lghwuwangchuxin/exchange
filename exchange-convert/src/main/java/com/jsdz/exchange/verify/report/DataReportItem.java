/**
 * %%
 * %%
 */
package com.jsdz.exchange.verify.report;

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
	private Long alertItemId;
	/** 表*/
	private String table;
	/** */
	private String item;
	/** 问题类型*/
	private IssueType type;
	
}
