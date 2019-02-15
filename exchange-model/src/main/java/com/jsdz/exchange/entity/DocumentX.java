package com.jsdz.exchange.entity;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.jsdz.admin.security.model.Employees;
import com.jsdz.digitalevidence.document.model.Audio;
import com.jsdz.digitalevidence.document.model.ContentTypeDocClassMapper;
import com.jsdz.digitalevidence.document.model.Document;
import com.jsdz.digitalevidence.document.model.DocumentCate;
import com.jsdz.digitalevidence.document.model.EnforceType;
import com.jsdz.digitalevidence.document.model.ImportantLevel;
import com.jsdz.digitalevidence.document.model.Picture;
import com.jsdz.digitalevidence.document.model.Video;
import com.jsdz.digitalevidence.site.model.Recorder;
import com.jsdz.exchange.common.ObjectTypeNotAcceptableException;

/**
 * @类名: DocumentX
 * @说明: 交换 执法档案
 *
 * @author Vincent
 * @Date 2017年4月13日 下午4:22:41 修改记录：
 *
 * @see
 */
public class DocumentX extends ExchangeEntity {
	/** */
	private Long id;
	/** 拍摄警员警号*/
	private String policeCode;
	/** 拍摄记录仪编号*/
	private String recCode;
	/** 文档名称*/
	private String fileName;
	/** 内容类型*/
	private String type;
	/** 档案类型*/
	private DocumentCateX cate;
	/** 存放uri*/
	private String url;
	/** 拍摄*/
	private String lon;
	private String lat;
	private String enforceType;
	/** 大小*/
	private Long fileSize;
	/** 时长，类型音频，视频有效*/
	private Long duration;
	/** 缩略图路径*/
	private String thumbnail;
	/** 
	 * 图像分辨率 高度/宽度，视频，图片有效
	 */
	private Long imageWidth;
	private Long imageHeight;
	/** 重要级别*/
	private ImportantLevel impLevel;
	/** 资料拍摄时间 */
	private Date shootingTime;
	/** 上传时间 */
	private Date uploadTime;
	/** 过期时间 */
	private Date expiredTime;
	/** 描述*/
	private String remark;
	
	@Override
	public void assignFrom(Object from) {
		if(!Document.class.isAssignableFrom(from.getClass()))
			throw new ObjectTypeNotAcceptableException();
		Document doc = (Document)from;
		this.setId(doc.getId());	
		this.setPoliceCode(doc.getPolice().getWorkNumber());
		this.setRecCode(doc.getRec().getCode());
		this.setFileName(doc.getName());
		this.setCate(fromCate(doc.getCate()));
		this.setUrl(doc.getUri());
		// lon
		// lat
		if(doc.getEnforceType()!=null)
			this.setEnforceType(doc.getEnforceType().getCode());
		this.setType(doc.getType());
		this.setFileSize(doc.getSize());
		//
		if(doc instanceof Audio)
			this.setDuration(((Audio)doc).getDuration());
		if(doc instanceof Video)
			this.setDuration(((Video)doc).getDuration());
		this.setThumbnail(doc.getThumbnail());
		if(doc instanceof Audio) {
			Picture pic = (Picture)doc;
			this.setImageHeight(pic.getHeight());
			this.setImageWidth(pic.getWidth());
		}
		if(doc instanceof Video) {
			Video v = (Video)doc;
			this.setImageHeight(v.getHeight());
			this.setImageWidth(v.getWidth());
		}
		this.setImpLevel(doc.getImpLevel());
		this.setShootingTime(doc.getCreateTime());
		this.setUploadTime(doc.getUploadTime());
		this.setExpiredTime(doc.getExpiryTime());
		// remark
		
	}
	
	@Override
	public Object assignTo(Object to) {
		if(!Document.class.isAssignableFrom(to.getClass()))
			throw new ObjectTypeNotAcceptableException();
		Document doc = (Document)to;
		doc.setId(this.getId());
		doc.setOriginalDocId(this.getId());
		if(this.getPoliceCode()!=null) {
			Employees police = new Employees();
			police.setWorkNumber(this.getPoliceCode());
			doc.setPolice(police);
		}
		if(this.getRecCode()!=null) {
			Recorder rec = new Recorder();
			rec.setCode(this.getRecCode());
			doc.setRec(rec);
		}
		doc.setName(this.getFileName());
		// doc.setCate(toCate(this.getCate()));
		doc.setUri(this.getUrl());
		// lon
		// lat
		if(this.getEnforceType()!=null) {
			EnforceType e = new EnforceType();
			e.setCode(this.getEnforceType());
			doc.setEnforceType(e);			
		}
		//
		if(doc instanceof Audio) {
			Audio a = new Audio();
			a.setDuration(this.getDuration());
		}
		if(doc instanceof Video) {
			Video v = (Video)doc;
			v.setDuration(this.getDuration());
		}
		doc.setThumbnail(this.getThumbnail());
		if(doc instanceof Picture) {
			Picture pic = (Picture)doc;
			pic.setHeight(this.getImageHeight());
			pic.setWidth(this.getImageWidth());
		}
		if(doc instanceof Video) {
			Video v = (Video)doc;
			v.setHeight(this.getImageHeight());
			v.setWidth(this.getImageWidth());
		}
		doc.setImpLevel(this.getImpLevel());
		doc.setCreateTime(this.getShootingTime());
		doc.setUploadTime(this.getUploadTime());
		doc.setExpiryTime(doc.getExpiryTime());
		// remark		
		return doc;
	}
	
	public DocumentCate toCate(DocumentCateX cateX) {
		if(cateX==DocumentCateX.VEDIO)
			return DocumentCate.VEDIO;
		if(cateX==DocumentCateX.AUDIO)
			return DocumentCate.AUDIO;
		if(cateX==DocumentCateX.PIC)
			return DocumentCate.PIC;
		if(cateX==DocumentCateX.LOG)
			return DocumentCate.TXT;
		return null;
	}
	
	public DocumentCateX fromCate(DocumentCate cate) {
		if(cate==DocumentCate.VEDIO)
			return DocumentCateX.VEDIO;
		if(cate==DocumentCate.AUDIO)
			return DocumentCateX.AUDIO;
		if(cate==DocumentCate.PIC)
			return DocumentCateX.PIC;
		if(cate==DocumentCate.TXT)
			return DocumentCateX.LOG;
		return null;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPoliceCode() {
		return policeCode;
	}
	public void setPoliceCode(String policeCode) {
		this.policeCode = policeCode;
	}
	public String getRecCode() {
		return recCode;
	}
	public void setRecCode(String recCode) {
		this.recCode = recCode;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public DocumentCateX getCate() {
		return cate;
	}
	public void setCate(DocumentCateX cate) {
		this.cate = cate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getEnforceType() {
		return enforceType;
	}
	public void setEnforceType(String enforceType) {
		this.enforceType = enforceType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public ImportantLevel getImpLevel() {
		return impLevel;
	}
	public void setImpLevel(ImportantLevel impLevel) {
		this.impLevel = impLevel;
	}
	public Date getShootingTime() {
		return shootingTime;
	}
	public void setShootingTime(Date shootingTime) {
		this.shootingTime = shootingTime;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public Date getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Long getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(Long imageWidth) {
		this.imageWidth = imageWidth;
	}

	public Long getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(Long imageHeight) {
		this.imageHeight = imageHeight;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
}
