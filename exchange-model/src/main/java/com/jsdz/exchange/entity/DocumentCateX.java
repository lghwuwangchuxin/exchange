package com.jsdz.exchange.entity;

import com.jsdz.digitalevidence.document.model.DocumentCate;

/**
 * 
 * @类名: DocumentCate
 * @说明: 资料分类
 *
 * @author   leehom
 * @Date	 2017年4月13日 下午5:46:34
 * 修改记录：
 *
 * @see
 */
public enum DocumentCateX {
	
	VEDIO(1, "视频"), AUDIO(2, "音频"), PIC(3, "图片"),  LOG(4, "日志");
	
	public int index;
	public String name;
	
	private DocumentCateX(int index, String name) {
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
