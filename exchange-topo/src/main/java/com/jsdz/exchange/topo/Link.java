/**
 * %%
 * %%
 */
package com.jsdz.exchange.topo;

import com.jsdz.core.AbstractDTO;

/**
 * @类名: Link
 * @说明: 实体拓扑链接
 *
 * @author   leehom
 * @Date	 2017年9月8日 上午11:26:34
 * 修改记录：
 *
 * @see 	 
 */
@SuppressWarnings("serial")
public class Link extends AbstractDTO {
	
	/** 链接名称*/
	private String name;
	/** 关联节点名称*/
	private String nodeName;
	/** 是否必需*/
	private boolean isRequire;
	
	public Link() {
		super();
	}
	
	public Link(String name, String nodeName, boolean isRequire) {
		super();
		this.name = name;
		this.nodeName = nodeName;
		this.isRequire = isRequire;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public boolean isRequire() {
		return isRequire;
	}
	public void setRequire(boolean isRequire) {
		this.isRequire = isRequire;
	}
	
}
