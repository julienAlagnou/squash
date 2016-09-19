/**
 * 
 */
package com.yungdu.us.squash.data;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;



@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Category
{
	@Id
	private String id;
	
	private String name;
	
	private String description;

	public Category()
	{
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
