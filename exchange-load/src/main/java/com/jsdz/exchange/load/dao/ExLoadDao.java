package com.jsdz.exchange.load.dao;

import java.util.List;

import com.jsdz.exchange.marker.Marker;

/**
 * 
 * @类名: ExLoadDao
 * @说明: 交换平台  载入/载出dao
 *        *. 上级平台载入下级平台数据
 *        *. 作为下级平台，写入中间库
 *
 * @author   leehom
 * @Date	 2017年4月13日 下午6:29:56
 * 修改记录：
 *
 * @see
 */
public interface ExLoadDao {
	
	// 载入数据, 使用标记
	public List<Object> loadInDataByMarker(String sql, Class clazz, Marker marker);
	
	// 作为二级平台，写入数据中间库
	public Object loadOutData(Object entity, Class clazz);
	public List<Object> loadOutDataBatch(List entities, Class clazz);
		
}
