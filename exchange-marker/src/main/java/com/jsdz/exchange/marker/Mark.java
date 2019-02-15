/**
 * %项目描述%
 * %版本信息%
 */
package com.jsdz.exchange.marker;

import com.jsdz.core.dao.GenericORMEntityDAO;


/**
 * @类名: Mark
 * @说明: 标记，记录表数据访问位置
 * 
 * @author leehom
 * @Date 2012-6-16 下午10:41:35
 * @修改记录：
 * 
 * @see
 */
public interface Mark extends GenericORMEntityDAO<Marker, MarkerId> {
	
	// 设置标记
	public Marker setMark(Marker marker);
	
	/**
	 * 说明：更新标记
	 *
	 * @author leehom
	 * @param owner
	 * @param tableName
	 * @param value  标记当前值，可以是整形或日期型
	 * @param maxV   标记最大值，可以是整形或日期型
	 * 
	 */
	public void updateMarker(String owner, String tableName, Object value);
	public void updateMarker(Marker marker, Object value);
	public void updateMarker(String owner, String tableName, Object value, Object maxV);
	// 获取标记
	public Marker getMarker(String owner, String tableName);
	
}
