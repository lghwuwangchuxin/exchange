/**
 * %交换平台%
 * %v1.0%
 */
package com.jsdz.exchange.writer;

import java.util.Map;

/**
 * 
 * @类名: ExWriteHandlerRegister.java
 * @说明: 交换入库处理器注册器
 *
 * @author: leehom
 * @Date	2017年12月19日 下午3:01:56
 * 修改记录：
 *
 * @see
 */
public class ExWriterHandlerRegister {

	private Map<String, ExWriterHandler> handlers;
	
	public ExWriterHandler getHandler(String clazz) {
		return handlers.get(clazz);
	}
	
	public Map<String, ExWriterHandler> getHandlers() {
		return handlers;
	}

	public void setHandlers(Map<String, ExWriterHandler> handlers) {
		this.handlers = handlers;
	}
	
}
