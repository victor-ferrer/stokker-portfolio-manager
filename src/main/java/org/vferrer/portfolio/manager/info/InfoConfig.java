package org.vferrer.portfolio.manager.info;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.InfoEndpoint;
import org.springframework.boot.bind.PropertiesConfigurationFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * This class will override the default info endpoint
 * @author Victor
 *
 */
@Configuration
public class InfoConfig 
{
	@Autowired
	private ConfigurableEnvironment env;
	
	@Value("classpath:git.properties")
	private Resource gitProperties;
	
	
	@Bean
	public InfoEndpoint infoEndpoint() throws Exception
	{
		MutablePropertySources propertySources = env.getPropertySources();
		
		PropertiesConfigurationFactory<Map<String,Object>> propFactory = new PropertiesConfigurationFactory(new HashMap<String,Object>());
		
		propFactory.setTargetName("info");
		propFactory.setPropertySources(propertySources);
		
		HashMap<String, Object> info = new HashMap<String,Object>();
		info.put("info", propFactory.getObject());
		
		Properties gitProps = new Properties();
		PropertiesLoaderUtils.fillProperties(gitProps, gitProperties);
		
		PropertiesConfigurationFactory<Properties> gitPropFactory = new PropertiesConfigurationFactory(new HashMap<String,Object>());
		gitPropFactory.setTargetName("git");
		gitPropFactory.setProperties(gitProps);
		info.put("git", gitPropFactory.getObject());
		
		return new InfoEndpoint(info);
	}
	
}
