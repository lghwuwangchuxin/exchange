/**
 * %交换平台%
 * %v1.0%
 */
package com.jsdz.exchange.topo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jsdz.exchange.topo.EntityTopology;
import com.jsdz.serializer.Serializer;
import com.jsdz.serializer.SeriallizeException;
import com.jsdz.utils.ByteAndStreamUtils;
import com.jsdz.utils.ResourceLoaderUtil;

/**
 * 
 * @类名: EntityTopologyServiceImpl.java
 * @说明: 实体拓扑服务实现
 *
 * @author: leehom
 * @Date	2017年12月18日 下午6:11:28
 * 修改记录：
 *
 * @see
 */
@Service
public class EntityTopologyServiceImpl implements EntityTopologyService {
	
	/** 拓扑模板路径*/
	@Value("${exchange.topo.confpath}")
	private String confPath;
	@Resource(name="entityTopoSer")
	private Serializer ser;
	
	private Map<String, EntityTopology> topos = new HashMap<String, EntityTopology>();
	
	@PostConstruct
	protected void init() {
		try {
			//
			File file = ResourceLoaderUtil.getFile(confPath);
			File[] files;;
			//
			if(file.isDirectory()) {
				files = file.listFiles(new FilenameFilter() {
					// xml
					@Override
					public boolean accept(File dir, String fileName) {
						int index = fileName.lastIndexOf(".");
						String ext = fileName.substring(index+1, fileName.length());
						if(!"xml".equals(ext))
							return false;
						return true;
					}
					
				});
				
			} else {
				files = new File[]{file};
			}
			
			for(File f : files) {
				InputStream is = new FileInputStream(f);
				byte[] bs = ByteAndStreamUtils.StreamToBytes(is);
				//
				EntityTopology t = (EntityTopology)ser.Unmarshal(bs);
				topos.put(t.getKey(), t);
			}
		} catch (SeriallizeException | IOException | URISyntaxException e) {
			throw new RuntimeException("初始化实体拓扑异常：" + e.getMessage());
		}

	}

	/* (non-Javadoc)
	 * @see com.jsdz.exchange.topo.service.EntityTopologyService#getEntityTopology(java.lang.String)
	 */
	@Override
	public EntityTopology getEntityTopology(String key) {
		return topos.get(key);
	}

}
