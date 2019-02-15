/**
 * 
 * @类名: ExchangeTask.java
 * @说明: 
 *
 * @author: leehom
 * @Date	2017年12月15日 上午10:48:32
 * 修改记录：
 *
 * @see
 */
package com.jsdz.exchange.task;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jsdz.exchange.entity.ExchangeEntity;
import com.jsdz.exchange.load.dao.ExLoadDao;
import com.jsdz.exchange.marker.Mark;
import com.jsdz.exchange.marker.Marker;
import com.jsdz.exchange.marker.MarkerId;
import com.jsdz.exchange.topo.EntityTopology;
import com.jsdz.exchange.topo.Node;
import com.jsdz.exchange.topo.service.EntityTopologyService;
import com.jsdz.exchange.writer.ExWriterHandler;
import com.jsdz.exchange.writer.ExWriterHandlerRegister;
import com.jsdz.scheduler.job.AbstractJob;

/**
 * @类名: ExchangeTask
 * @说明: 交换任务，接入调度任务
 *          载入依赖拓扑
 *          协调载入，写入
 *          并行策略
 *          
 *
 * @author: leehom
 * @Date	2017年12月15日 上午10:49:18
 * 修改记录：
 *
 * @see
 * 
 * TODO
 *   数据依赖拓扑
 *     链式，图
 *     
 */
public class ExchangeJob extends AbstractJob {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
 
	@Autowired
	private ExLoadDao exloadDao;
	@Autowired
	private EntityTopologyService entityTopoService;
	@Autowired
	private Mark mark;	
	@Autowired
	private ExWriterHandlerRegister handlerReg;
	
	/** */
	private String hqlPattern = "from {0} e where e.{1} > :curValue "
							+ "order by e.{1} ";
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// 获取标记拥有者
		Map<String, Object> params = this.getParams();
		String markerOwner = (String)params.get(TaskConsts.TASK_PARAM_KEY_MARKER_OWNER);
		String topoKey = (String)params.get(TaskConsts.TASK_PARAM_KEY_TOPO_KEY);
		// 获取数据拓扑
		EntityTopology topo = entityTopoService.getEntityTopology(topoKey);
		// begin 遍历拓扑
		Node head = topo.getHeaderNode();
		Node node = head;
		do {
			// 获取标记
			// 作业名称，实体类型
			MarkerId mId = new MarkerId(markerOwner, node.getClazz().getName());
			Marker marker = mark.get(mId);
			// 加载实体
			String hql = MessageFormat.format(hqlPattern, node.getClazz().getName(), node.getMarkerField());
			// 
			while(true) {
				// 分页获取
				List<Object> entities = exloadDao.loadInDataByMarker(hql, node.getClass(), marker);
				// 没有数据了
				if(entities.size()==0)
					break;
				// 遍历
				for (Object entity : entities) {
					// 
					ExWriterHandler handler = handlerReg.getHandler(entity.getClass().getName());
					// 实在存在检验
					Object inEntity = handler.getInEntity(entity, null);					
					// 关联检验
					Map<String, Object> refs = handler.getAssociation(entity, node);
					if(refs==null) {
						// 生成检验报告项，并抛异常退出
						// ...
						log.error("实体找不到必需的关联：{}", entity.toString());
						// throw new JobExecutionException("实体找不到必需的关联：" + entity.toString());
					}
					// 实体已存在，重复推送
					if (inEntity != null) {
						// 生成检验报告项
						// ...
						try {
							handler.updateInEntity(inEntity, entity, refs, node);
						} catch (Exception e) {
							log.error("实体更新错误：{0}", e.getMessage());
						}
						continue;
					}
					// 写入实体, 包括实体关系
					try {
						handler.writeInEntity(entity, refs, node);
					} catch (Exception e) {
						log.error("实体入库错误：{0}", e.getMessage());
					}
				}
				// 
				if(entities.size()>0) {
					ExchangeEntity lastEntity = (ExchangeEntity)entities.get(entities.size()-1);
					// 更新标记
					mark.updateMarker(marker, lastEntity.getCreateTime());
				}

			}
			//
			List<Node> nodes = topo.getNextNodes(node);
			if(nodes.size()!=0)
				node = nodes.get(0); // 目前最多只有一个
			else
				node = null;
		} while (node != null);
		// end 遍历拓扑

	}

	public String getHqlPattern() {
		return hqlPattern;
	}

	public void setHqlPattern(String hqlPattern) {
		this.hqlPattern = hqlPattern;
	}
	
	

}