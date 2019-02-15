package com.jsdz.exchange.entity;

/**
 * 
 * @类名: SiteState
 * @说明: 采集站状态
 *
 * @author   leehom
 * @Date	 2017年4月13日 下午5:46:34
 * 修改记录：
 *
 * @see
 */
public enum EquipmentState {
	
	NORMAL(1, "正常"), REPAIR(2, "报修"), LOST(3, "遗失"), SCRAPPING(4, "报废"), UNABLED(5, "未启用"), UNKNOWN(6, "未明"), UNKNOWN2(7, "未明");
	
	public int index;
	public String name;
	
	private EquipmentState(int index, String name) {
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
