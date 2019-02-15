package com.jsdz.exchange.marker;

/**
 * 
 * @类名: MarkerType
 * @说明: 标记类型
 *
 * @author   leehom
 * @Date	 2017年4月13日 下午5:46:34
 * 修改记录：
 *
 * @see
 */
public enum MarkerType {
	
	D(1, "日期"), L(2, "长整数"), S(3, "字符串");
	
	public int index;
	public String name;
	
	private MarkerType(int index, String name) {
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
