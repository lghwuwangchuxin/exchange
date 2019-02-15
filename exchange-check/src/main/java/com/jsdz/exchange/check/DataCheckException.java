/**
 * %交换平台%
 * %v1.0%
 */
package com.jsdz.exchange.check;

import com.jsdz.exchange.check.report.DataReport;

/**
 * 
 * @类名: DataCheckException.java
 * @说明: 数据检验异常
 *
 * @author: leehom
 * @Date	2017年12月20日 下午2:04:46
 * 修改记录：
 *
 * @see
 */
public class DataCheckException extends Exception {

	/** 检验报告*/
	protected DataReport report;

	public DataCheckException(DataReport report) {
		super();
		// TODO Auto-generated constructor stub
	}

	public DataCheckException(String arg0, DataReport report) {
		super();
		// TODO Auto-generated constructor stub
	}

	public DataReport getReport() {
		return report;
	}
	
}
