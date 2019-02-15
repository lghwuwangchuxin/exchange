package com.jsdz.exchange.verify.report;

/**
 * 
 * @类名: IssueType
 * @说明: 问题类型
 *
 * @author   leehom
 * @Date	 2017年4月13日 下午5:46:34
 * 修改记录：
 *
 * @see
 */
public enum IssueType {
	
	FK(1, "外键约束");
	
	public int index;
	public String name;
	
	private IssueType(int index, String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
