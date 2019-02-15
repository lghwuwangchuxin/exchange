package com.jsdz.exchange.entity;

/**
 * 
 * @类名: Sex
 * @说明: 性别
 *
 * @author   leehom
 * @Date	 2017年4月13日 下午5:46:34
 * 修改记录：
 *
 * @see
 */
public enum Sex {
	
	MALE(1, "男"), FEMALE(2, "女");
	
	public int index;
	public String name;
	
	private Sex(int index, String name) {
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
