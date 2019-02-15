/**
 * %项目描述%
 * %版本信息%
 */
package com.jsdz.test.exchange.load;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.jsdz.admin.org.model.Organization;
import com.jsdz.admin.org.service.OrganizationService;
import com.jsdz.admin.security.model.Employees;
import com.jsdz.admin.security.service.EmployeesService;
import com.jsdz.digitalevidence.document.model.Document;
import com.jsdz.digitalevidence.document.service.DocumentService;
import com.jsdz.digitalevidence.site.model.Site;
import com.jsdz.digitalevidence.site.model.SiteType;
import com.jsdz.digitalevidence.site.service.SiteService;
import com.jsdz.exchange.entity.DocumentX;
import com.jsdz.exchange.entity.OrganizationX;
import com.jsdz.exchange.entity.PoliceX;
import com.jsdz.exchange.entity.SiteX;
import com.jsdz.exchange.load.dao.ExLoadDao;
import com.jsdz.exchange.marker.Mark;
import com.jsdz.exchange.marker.Marker;
import com.jsdz.exchange.marker.MarkerId;

/**
 * @类名: ExLoadDaoTest
 * @说明: 交换 加载dao测试
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
		locations={"/testApplicationContext-common.xml",
				"/testApplicationContext-dynsql.xml",
				"/testApplicationContext-documenttype.xml",
				"/testApplicationContext-scheduler.xml",
				"/testApplicationContext-dao.xml"})
public class ExLoadDaoTest {

	@Autowired
	private Mark mark;
	@Autowired
	private ExLoadDao loadDao;
	
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

	//@Autowired
	private OrganizationService orgService;
	// 组织机构，写入中间表测试
	@Test
	@Transactional
	public void testLoadOutData(){
		// 获取Organization
		Organization org = orgService.locateOrganizationById(85L);
		// 转换为OrganizationX
		OrganizationX orgX = new OrganizationX();
		orgX.assignFrom(org);
		// 写入中间表
		orgX = (OrganizationX)loadDao.loadOutData(orgX, OrganizationX.class);
		
	}
	
	// 写入中间表测试-批量
	@Test
	@Transactional
	public void testLoadOutDataBatch(){
	
	}
	
	// 组织机构 载入数据测试， 即从中间表抽取数据
	@SuppressWarnings("rawtypes")
	@Test
	@Transactional
	public void testLoadInData(){
		MarkerId mId = new MarkerId("PIS-CCTC", OrganizationX.class.getName());
		Marker marker = mark.get(mId);
		String loadHql = "from com.jsdz.exchange.load.entity.OrganizationX o "
				+ "where o.createTime >= :curValue "
				+ "order by o.createTime ";
		List orgxs = loadDao.loadInDataByMarker(loadHql, com.jsdz.exchange.entity.OrganizationX.class, marker);
		for(Object obj : orgxs) {
			OrganizationX orgx = (OrganizationX)obj;
			System.out.println(orgx);			
		}
	}	 
	
	/********采集站*********/
	//@Autowired
	private SiteService siteService;
	// 采集站，写入中间表测试
	@Test
	@Transactional
	public void testLoadOutData_Site(){
		// 获取Organization
		Site site = siteService.findSiteById(10L, SiteType.COLLECT_SITE);
		// 转换为OrganizationX
		SiteX siteX = new SiteX();
		siteX.assignFrom(site);
		// 写入中间表
		siteX = (SiteX)loadDao.loadOutData(siteX, SiteX.class);
		
	}
	
	// 组织机构 载入数据测试， 即从中间表抽取数据
	@SuppressWarnings("rawtypes")
	@Test
	@Transactional
	public void testLoadInData_Site(){
		MarkerId mId = new MarkerId("PIS-CCTC", SiteX.class.getName());
		Marker marker = mark.get(mId);
		String loadHql = "from com.jsdz.exchange.load.entity.SiteX o "
				+ "where o.createTime >= :curValue "
				+ "order by o.createTime ";
		List sitexs = loadDao.loadInDataByMarker(loadHql, com.jsdz.exchange.entity.SiteX.class, marker);
		for(Object obj : sitexs) {
			SiteX sitex = (SiteX)obj;
			System.out.println(sitex);			
		}
	}	 	
	
	// 写入中间表测试-批量
	@Test
	@Transactional
	public void testLoadOutDataBatch_Site() {

	}
	
	/*********警员*********/
	//@Autowired
	private EmployeesService empService;
	// 警员写入中间表测试
	@Test
	@Transactional
	public void testLoadOutData_Police(){
		// 获取Organization
		Employees emp = empService.locateEmployeesById(11L);
		// 转换为OrganizationX
		PoliceX policeX = new PoliceX();
		policeX.assignFrom(emp);
		// 写入中间表
		policeX = (PoliceX)loadDao.loadOutData(policeX, PoliceX.class);
		
	}
	
	// 警员载入数据测试， 即从中间表抽取数据
	@SuppressWarnings("rawtypes")
	@Test
	@Transactional
	public void testLoadInData_Police(){
		MarkerId mId = new MarkerId("PIS-exchangeJob", PoliceX.class.getName());
		Marker marker = mark.get(mId);
		String loadHql = "from com.jsdz.exchange.load.entity.PoliceX o "
				+ "where o.createTime >= :curValue "
				+ "order by o.createTime ";
		List policexs = loadDao.loadInDataByMarker(loadHql, com.jsdz.exchange.entity.PoliceX.class, marker);
		for(Object obj : policexs) {
			PoliceX policex = (PoliceX)obj;
			System.out.println(policex);			
		}
	}	 	
	
	// 写入中间表测试-批量
	@Test
	@Transactional
	public void testLoadOutDataBatch_Police() {

	}
	
	/*********执法档案*********/
	//@Autowired
	private DocumentService docService;
	// 警员写入中间表测试
	@Test
	@Transactional
	public void testLoadOutData_Doc(){
		// 
		Document doc = docService.getDocument(27L);
		// 转换为OrganizationX
		DocumentX docX = new DocumentX();
		docX.assignFrom(doc);
		// 写入中间表
		docX = (DocumentX)loadDao.loadOutData(docX, DocumentX.class);
		
	}
	
	// 警员载入数据测试， 即从中间表抽取数据
	@SuppressWarnings("rawtypes")
	@Test
	@Transactional
	public void testLoadInData_Doc(){
		MarkerId mId = new MarkerId("exchangeJob", DocumentX.class.getName());
		Marker marker = mark.get(mId);
		String loadHql = "from com.jsdz.exchange.entity.DocumentX o "
				+ "where o.createTime >= :curValue "
				+ "order by o.createTime ";
		List docxs = loadDao.loadInDataByMarker(loadHql, com.jsdz.exchange.entity.DocumentX.class, marker);
		for(Object obj : docxs) {
			DocumentX docx = (DocumentX)obj;
			System.out.println(docx);			
		}
	}	 	
	
	// 写入中间表测试-批量
	@Test
	@Transactional
	public void testLoadOutDataBatch_Doc() {

	}
	
	
}
