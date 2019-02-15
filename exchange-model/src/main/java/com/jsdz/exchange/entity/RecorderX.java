package com.jsdz.exchange.entity;

import com.jsdz.admin.security.model.Employees;
import com.jsdz.digitalevidence.site.model.Recorder;
import com.jsdz.digitalevidence.site.model.Site;
import com.jsdz.exchange.common.ObjectTypeNotAcceptableException;

/**
 * 
 * @类名: Recorder
 * @说明: 记录仪
 *
 * @author   leehom
 * @Date	 2017年4月13日 下午5:17:00
 * 修改记录：
 *
 * @see
 */
public class RecorderX extends ExchangeEntity {
	
	/** id*/
	private Long id;
	/** 编号*/
	private String recCode;
	/** 型号*/
	private String recModel;
	/** 厂商*/
	private String vendor;
	/** 关联警员编号*/
	private String policeCode;
	/** 状态*/
	private EquipmentState state;
	/** 所属站点编号*/
	private String siteCode;
	/** 说明*/
	private String remark;
		
	@Override
	public void assignFrom(Object from) {
		if(!from.getClass().isAssignableFrom(Recorder.class))
			throw new ObjectTypeNotAcceptableException();
		Recorder rec = (Recorder)from;
		this.setId(rec.getId());
		this.setRecCode(rec.getCode());
		this.setRecModel(rec.getModel());
		this.setVendor(rec.getModel());
		Employees police = rec.getPolice();
		if(police!=null)
			this.setPoliceCode(police.getWorkNumber());
		// state
		Site site = rec.getSite();
		if(site!=null)
			this.setSiteCode(site.getSiteNo());
		// remark
	}
	
	@Override
	public Object assignTo(Object to) {
		if(!to.getClass().isAssignableFrom(Recorder.class))
			throw new ObjectTypeNotAcceptableException();
		Recorder rec = (Recorder)to;
		rec.setId(this.getId());
		rec.setCode(this.getRecCode());
		rec.setVendor(this.getVendor());
		rec.setModel(this.getRecModel());
		// state
		Site site = new Site();
		site.setSiteNo(this.getSiteCode());
		//
		Employees police = new Employees();
		police.setWorkNumber(this.getPoliceCode());
		rec.setPolice(police);
		// remark		
		return rec;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRecCode() {
		return recCode;
	}
	public void setRecCode(String recCode) {
		this.recCode = recCode;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getRecModel() {
		return recModel;
	}
	public void setRecModel(String recModel) {
		this.recModel = recModel;
	}
	public EquipmentState getState() {
		return state;
	}
	public void setState(EquipmentState state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getPoliceCode() {
		return policeCode;
	}

	public void setPoliceCode(String policeCode) {
		this.policeCode = policeCode;
	}
	
}
