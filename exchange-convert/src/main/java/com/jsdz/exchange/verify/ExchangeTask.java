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
package com.jsdz.exchange.verify;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

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
public class ExchangeTask extends AbstractJob {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// 获取数据拓扑
		// ...
		
		// 遍历拓扑
		// 获取标记 作业名称(标记拥有者)，实体类型
		// ...
		
		// 加载数据
		// ...
		
		// 检验数据
		// ...
		
		// 预处理、转换
		// ..
		
		// 写入数据(批量)
		// ...
		
		// 更新标记
		// ..
		
	}

}