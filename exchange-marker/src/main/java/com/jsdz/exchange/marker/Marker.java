/**
 * %项目描述%
 * %版本信息%
 */
package com.jsdz.exchange.marker;

import java.util.Date;

import com.jsdz.core.AbstractDTO;

/**
 * @类名: Marker
 * @说明: 标记
 * 
 * @author leehom
 * 
 * @Date 2012-6-16 下午10:59:18
 * @修改记录：
 * 
 * @see
 */
public class Marker extends AbstractDTO {
		
	/** marker主键*/
	private MarkerId id;
	/** 批量规模*/
	private Integer batchSize;
	/** 作为标记的字段，其中vlues为标记的阀值*/
	private MarkerField markerField;
	/** 最大标记值，整数型&日期型&字符型*/
	private Long maxN;
	private Date maxD;
	private String maxS;
	
	// 获取当前值
	public Object getCurValue() {
		if(this.getType()==MarkerType.D)
			return this.getMarkerField().getDvalue();
		if(this.getType()==MarkerType.L)
			return this.getMarkerField().getNvalue();
		if(this.getType()==MarkerType.S)
			return this.getMarkerField().getSvalue();
		return null;
		
	}
	
	// 获取最大值
	public Object getMaxValue() {
		if(this.getType()==MarkerType.D)
			return this.getMaxD();
		if(this.getType()==MarkerType.L)
			return this.getMaxN();
		if(this.getType()==MarkerType.S)
			return this.getMaxS();
		return null;
		
	}
	
	public MarkerType getType() {
		return markerField.getType();
	}
	
	public Date getDValue() {
		return markerField.getDvalue();
	}
	/**
	 * @return the markerField
	 */
	public MarkerField getMarkerField() {
		return markerField;
	}
	/**
	 * @param markerField the markerField to set
	 */
	public void setMarkerField(MarkerField markerField) {
		this.markerField = markerField;
	}
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return id!=null ? id.getTableName() : null;
	}
	/**
	 * @return the owner
	 */
	public String getOwner() {
		return id!=null ? id.getOwner() : null;
	}
	/**
	 * @return the id
	 */
	public MarkerId getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(MarkerId id) {
		this.id = id;
	}
	/**
	 * @return the batchSize
	 */
	public Integer getBatchSize() {
		return batchSize;
	}
	/**
	 * @param batchSize the batchSize to set
	 */
	public void setBatchSize(Integer batchSize) {
		this.batchSize = batchSize;
	}
	public Long getMaxN() {
		return maxN;
	}
	public void setMaxN(Long maxN) {
		this.maxN = maxN;
	}
	public Date getMaxD() {
		return maxD;
	}
	public void setMaxD(Date maxD) {
		this.maxD = maxD;
	}
	public String getMaxS() {
		return maxS;
	}
	public void setMaxS(String maxS) {
		this.maxS = maxS;
	}

}
