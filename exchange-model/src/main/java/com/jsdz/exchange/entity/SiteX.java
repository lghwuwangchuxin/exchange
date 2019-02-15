package com.jsdz.exchange.entity;

import com.jsdz.admin.org.model.Organization;
import com.jsdz.digitalevidence.site.model.Site;
import com.jsdz.exchange.common.ObjectTypeNotAcceptableException;

/**
 * 
 * @类名: Site
 * @说明: 站点
 *
 * @author   leehom
 * @Date	 2017年4月13日 下午5:08:12
 * 修改记录：
 *
 * @see
 */

public class SiteX extends ExchangeEntity {
	
	private Long id;
	/** 站点编号*/
	private String siteCode;
	/** 站点名称*/
	private String siteName; 
	/** 站点ip*/
	private String siteIP;
	/** 地址*/
	private String addr;
	/** 厂商*/
	private String verdor;
	/** 所属单位*/
	private String orgCode; 
	/** 状态*/
	private EquipmentState state;
	/** 说明*/
	private String remark;
	
	@Override
	public void assignFrom(Object from) {
		if(!from.getClass().isAssignableFrom(Site.class))
			throw new ObjectTypeNotAcceptableException();
		Site site = (Site)from;
		this.setId(site.getId());
		this.setSiteCode(site.getSiteNo());
		this.setSiteName(site.getSiteName());
		this.setSiteIP(site.getSiteIP());
		this.setAddr(site.getAddr());
		// vendor
		this.setOrgCode(site.getOrganization().getOrgCode());
		// state
		// remark
	}
	
	@Override
	public Object assignTo(Object to) {
		if(!to.getClass().isAssignableFrom(Site.class))
			throw new ObjectTypeNotAcceptableException();
		Site site = (Site)to;
		site.setId(this.getId());
		site.setSiteNo(this.getSiteCode());
		site.setSiteName(this.getSiteName());
		site.setSiteIP(this.getSiteIP());
		site.setAddr(this.getAddr());
		// vendor
		Organization org = new Organization();
		org.setOrgCode(this.getOrgCode());
		site.setOrganization(org);
		// state
		// remark		
		return site;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteIP() {
		return siteIP;
	}
	public void setSiteIP(String siteIP) {
		this.siteIP = siteIP;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getVerdor() {
		return verdor;
	}
	public void setVerdor(String verdor) {
		this.verdor = verdor;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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

}
