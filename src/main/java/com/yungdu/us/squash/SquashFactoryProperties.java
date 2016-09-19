package com.yungdu.us.squash;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(value = "asyoms")
public class SquashFactoryProperties
{

	private String fileNamePattern;

	public String getFileNamePattern()
	{
		return fileNamePattern;
	}

	public void setFileNamePattern(final String fileNamePattern)
	{
		this.fileNamePattern = fileNamePattern;
	}

}
