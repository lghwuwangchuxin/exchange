/**
 * %项目描述%
 * %版本信息%
 */
package com.jsdz.test.exchange.marker;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.jsdz.exchange.marker.Mark;
import com.jsdz.exchange.marker.Marker;
import com.jsdz.exchange.marker.MarkerConsts;
import com.jsdz.exchange.marker.MarkerField;
import com.jsdz.exchange.marker.MarkerId;
import com.jsdz.exchange.marker.MarkerType;
import com.jsdz.utils.DateTimeUtils;

/**
 * @类名: MarkerHiberanteImplTest
 * @说明: 标记测试
 * 
 * @author leehom
 * @Date 2012-6-27 下午5:21:18
 * @修改记录：
 * 
 * @see
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@ContextConfiguration(
		locations={
				"/testApplicationContext-common.xml",
				"/testApplicationContext-dao.xml"}
		)
public class MarkerHiberanteImplTest {

	@Autowired
	private Mark mark;
	
	/**
	 * @说明：
	 * 
	 * @author leehom
	 * @throws java.lang.Exception
	 * 
	 * @异常：
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @说明：
	 * 
	 * @author leehom
	 * @throws java.lang.Exception
	 * 
	 * @异常：
	 */
	@After
	public void tearDown() throws Exception {
	}

	// 设置标记-组织机构
	@Test
	@Transactional
	public void testSetMark_Org(){
		Marker marker = new Marker();
		MarkerId id = new MarkerId("exchangeJob", "com.jsdz.exchange.entity.OrganizationX");
		MarkerField f = new MarkerField();
		f.setName("testField");
		f.setDvalue(DateTimeUtils.StringToDate("2017-12-01 00:00:00"));		
		f.setType(MarkerType.D);
		marker.setMaxD(DateTimeUtils.StringToDate("2017-12-01 23:59:59"));
		//
		marker.setId(id);
		marker.setMarkerField(f);
		marker.setBatchSize(1000);
		//
		mark.setMark(marker);
		//		
	}
	
	// 设置标记-采集站
	@Test
	@Transactional
	public void testSetMark_Site(){
		Marker marker = new Marker();
		MarkerId id = new MarkerId("exchangeJob", "com.jsdz.exchange.entity.SiteX");
		MarkerField f = new MarkerField();
		f.setName("createTime");
		f.setDvalue(DateTimeUtils.StringToDate("2017-12-01 00:00:00"));		
		f.setType(MarkerType.D);	
		//
		marker.setId(id);
		marker.setMarkerField(f);
		marker.setBatchSize(1000);
		//
		mark.setMark(marker);
		//		
	}
	
	// 设置标记-警员
	@Test
	@Transactional
	public void testSetMark_Police() {
		Marker marker = new Marker();
		MarkerId id = new MarkerId("exchangeJob", "com.jsdz.exchange.entity.PoliceX");
		MarkerField f = new MarkerField();
		f.setName("createTime");
		f.setDvalue(DateTimeUtils.StringToDate("2017-12-01 00:00:00"));
		f.setType(MarkerType.D);
		//
		marker.setId(id);
		marker.setMarkerField(f);
		marker.setBatchSize(1000);
		//
		mark.setMark(marker);
		//
	}
	
	// 设置标记-记录仪
	@Test
	@Transactional
	public void testSetMark_Recorder() {
		Marker marker = new Marker();
		MarkerId id = new MarkerId("exchangeJob", "com.jsdz.exchange.entity.RecorderX");
		MarkerField f = new MarkerField();
		f.setName("createTime");
		f.setDvalue(DateTimeUtils.StringToDate("2017-12-01 00:00:00"));
		f.setType(MarkerType.D);
		//
		marker.setId(id);
		marker.setMarkerField(f);
		marker.setBatchSize(1000);
		//
		mark.setMark(marker);
		//
	}
	
	// 设置标记-档案
	@Test
	@Transactional
	public void testSetMark_Document() {
		Marker marker = new Marker();
		MarkerId id = new MarkerId("exchangeJob", "com.jsdz.exchange.entity.DocumentX");
		MarkerField f = new MarkerField();
		f.setName("createTime");
		f.setDvalue(DateTimeUtils.StringToDate("2017-12-01 00:00:00"));
		f.setType(MarkerType.D);
		//
		marker.setId(id);
		marker.setMarkerField(f);
		marker.setBatchSize(1000);
		//
		mark.setMark(marker);
		//
	}
	
	// 更新标记阀值
	@Test
	@Transactional
	public void testUpdateMarker() {
		mark.updateMarker("exchangeJob", "com.jsdz.exchange.entity.OrganizationX", 
				DateTimeUtils.StringToDate("2017-12-01 9:00:00.234", MarkerConsts.DatePatternWithMills));
	
	}
	
	// 获取标记
	@Test
	public void testGetMarker() {
		Marker m = mark.getMarker("exchangeJob", "com.jsdz.exchange.entity.OrganizationX");
		System.out.println(m);
	}

	// 
	@Test
	public void testDateFormat() {
		String s = DateTimeUtils.DateToString(new Date(), MarkerConsts.DatePatternWithMills);
		System.out.println(s);

	}

}
