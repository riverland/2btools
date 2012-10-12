package org.river.rbtools.knifepenner.db.dbo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.river.rbtools.db.model.BaseEntity;

@MappedSuperclass
public abstract class SealPatternEntity extends BaseEntity {
	/**the seal pattern name*/
	protected String name;
	
	/**pattern width*/
	protected Integer width;
	
	/**pattern height*/
	protected Integer height;
	
	/**the seal pattern image url*/
	protected String patternUrl;
	
	/**description of pattern*/
	protected String description;


	@Basic
	@Column(name="NAME",length=64)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Basic
	@Column(name="WIDTH")
	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}
	
	@Basic
	@Column(name="HEIGHT")
	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	@Basic
	@Column(name="PTN_URL",length=128)
	public String getPatternUrl() {
		return patternUrl;
	}

	public void setPatternUrl(String patternUrl) {
		this.patternUrl = patternUrl;
	}

	@Basic
	@Column(name="PTN_DESC",length=255)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
}
