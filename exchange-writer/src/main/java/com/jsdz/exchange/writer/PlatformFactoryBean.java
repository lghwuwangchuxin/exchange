package com.jsdz.exchange.writer;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jsdz.digitalevidence.site.dao.PlatformDao;
import com.jsdz.digitalevidence.site.model.Platform;

@Component
public class PlatformFactoryBean implements FactoryBean<Platform> {
	
	@Value("${platformId}")
	private Long platformId;
	private Platform platform;
	@Autowired
	private PlatformDao platformDao;
	
	@Override
	public Platform getObject() throws Exception {
		if(platform!=null)
			return platform;
		platform = platformDao.get(platformId);
		return platform;
	}

	@Override
	public Class<Platform> getObjectType() {
		return Platform.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}


}
