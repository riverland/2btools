package org.river.rbtools.knifepenner.utils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SealTemplate {
	
	String id;
	
	String name;
	
	int width;
	
	int height;
	
	String colorStr;
	
	BufferedImage pattern;
	
	List<SealFont> fonts=new ArrayList<SealFont>();
	
	boolean dented=true;
	
	public String getId() {		
		return id;
	}
	public String getName() {
		return name;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public String getColorStr() {
		return colorStr;
	}
	public BufferedImage getPattern() {
		return pattern;
	}
	public List<SealFont> getFonts() {
		return fonts;
	}
	public boolean isDented() {
		return dented;
	}	
}
