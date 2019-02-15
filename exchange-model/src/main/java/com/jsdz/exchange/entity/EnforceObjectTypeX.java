package com.jsdz.exchange.entity;

/**
 * 
 * @类名: EnforceObjectType
 * @说明: 执法对象类型
 *
 * @author   leehom
 * @Date	 2017年4月13日 下午5:46:34
 * 修改记录：
 *
 * @see
 */
public enum EnforceObjectTypeX {
	
	PERSON(1, "自然人"), LEGALPERSON(2, "法人"), OTHERS(3, "其他组织");
	
	public int index;
	public String name;
	
	private EnforceObjectTypeX(int index, String name) {
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
