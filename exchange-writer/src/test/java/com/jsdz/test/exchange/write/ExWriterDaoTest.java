/**
 * %项目描述%
 * %版本信息%
 */
package com.jsdz.test.exchange.write;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.jsdz.exchange.entity.ExchangeEntity;
import com.jsdz.exchange.entity.OrganizationX;
import com.jsdz.exchange.load.dao.ExLoadDao;
import com.jsdz.exchange.marker.Mark;
import com.jsdz.exchange.marker.Marker;
import com.jsdz.exchange.marker.MarkerId;
import com.jsdz.exchange.writer.ExWriterHandler;
import com.jsdz.exchange.writer.ExWriterHandlerRegister;

/**
 * @类名: ExLoadDaoTest
 * @说明: 交换 加载dao测试
 * 
 * @author leehom
 * @Date 2012-6-27 下午5:21:18 @修改记录：
 * 
 * @see
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@ContextConfiguration(locations = {
		"/testApplicationContext-common.xml", 
		"/testApplicationContext-documenttype.xml", 
		"/testApplicationContext-topo.xml",
		"/testApplicationContext-exwriterhandler.xml",
		"/testApplicationContext-dao.xml" })
public class ExWriterDaoTest {

	@Autowired
	private Mark mark;
	@Autowired
	private ExLoadDao exloadDao;
	@Autowired
	private ExWriterHandlerRegister handlerReg;

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

	// 组织机构，写入中间表测试
	@Test
	public void testWriterData() {
		// 作业名称，实体类型
		MarkerId mId = new MarkerId("exchangeJob", OrganizationX.class.getName());
		Marker marker = mark.get(mId);
		String loadHql = "from com.jsdz.exchange.entity.OrganizationX o "
				+ "where o.createTime >= :curValue "
				+ "order by o.createTime ";
		//
		while (true) {
			// 分页获取
			List<Object> entities = exloadDao.loadInDataByMarker(loadHql, OrganizationX.class, marker);
			// 没有数据了
			if (entities.size() == 0)
				break;
			// 遍历
			for (Object entity : entities) {
				//
				ExWriterHandler handler = handlerReg.getHandler(entity.getClass().getName());
				// 实在存在检验
				Object inEntity = handler.getInEntity(entity, null);
				// 实体已存在，重复推送
				if (inEntity != null) {
					// 生成检验报告项
					// ...
					continue;
				}
				// 关联检验
				Map<String, Object> refs = handler.getAssociation(entity, null);
				if (refs == null) {
					// 生成检验报告项，并抛异常退出
					// ...
					break;
				}
				// 写入实体, 包括实体关系
				try {
					handler.writeInEntity(entity, refs, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//
			if (entities.size() > 0) {
				ExchangeEntity lastEntity = (ExchangeEntity) entities.get(entities.size() - 1);
				// 更新标记
				mark.updateMarker(marker, lastEntity.getCreateTime());
			}

		}
	}

}
