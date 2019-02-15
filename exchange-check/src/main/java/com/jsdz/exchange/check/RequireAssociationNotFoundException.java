/**
 * %交换平台%
 * %v1.0%
 */
package com.jsdz.exchange.check;

import com.jsdz.exchange.check.report.DataReport;

/**
 * 
 * @类名: RequireAssociationNotFoundException.java
 * @说明: 没找到必需的关联异常
 *
 * @author: leehom
 * @Date	2017年12月20日 下午2:02:54
 * 修改记录：
 *
 * @see
 */
public class RequireAssociationNotFoundException extends DataCheckException {

	public RequireAssociationNotFoundException(DataReport report) {
		super(report);
		// TODO Auto-generated constructor stub
	}

	public RequireAssociationNotFoundException(String arg0, DataReport report) {
		super(arg0, report);
		// TODO Auto-generated constructor stub
	}
	
	
}
