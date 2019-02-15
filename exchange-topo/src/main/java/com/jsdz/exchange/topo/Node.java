/**
 * %%
 * %%
 */
package com.jsdz.exchange.topo;

import java.util.List;

import com.jsdz.core.AbstractDTO;

/**
 * @类名: Node
 * @说明: 实体拓扑节点
 *
 * @author   leehom
 * @Date	 2017年9月8日 上午11:26:34
 * 修改记录：
 *
 * @see 	 
 */
@SuppressWarnings("serial")
public class Node extends AbstractDTO {
	
	private Long id;
	/** */
	private String name;
	/** 标记字段*/
	private String markerField;
	/** 实体类型*/
	private Class<?> clazz;
	/** 关联实体*/
	private List<Link> links;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Class<?> getClazz() {
		return clazz;
	}
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
	public String getMarkerField() {
		return markerField;
	}
	public void setMarkerField(String markerField) {
		this.markerField = markerField;
	}
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
}
