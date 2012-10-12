package org.river.rbtools.artword.bean;

import java.io.Serializable;

public class ImageBean implements Serializable {

	private static final long serialVersionUID = -6729543737761999453L;
	public static final String DIRECTION_H="H";
	public static final String DIRECTION_V="V";
	
	private String key;
	private String url;
	private String content;
	private String filterName;
	private int width;
	private int height;
	private String direction;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
}
