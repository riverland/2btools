package org.river.rbtools.knifepenner.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.river.base.img.Area;
import org.river.base.img.resources.ArtFontCached;
import org.river.base.img.utils.FileUtils;
import org.river.base.img.utils.ImageUtils;
import org.river.base.img.utils.NumberUtils;
/**
 *
 *<?xml version="1.0" encoding="UTF-8"?>
 *<patterns patternPath="">
 *	<pattern id="" name="" width="" height="" dented="true">
 *		<font family="" size="" x="" y="" width="" height="" />
 *	</pattern>
 *</patterns>
 * @author river
 *
 */
public class SealTemplateCached {
	private Map<String,SealTemplate> cached=new HashMap<String,SealTemplate>();
	private Map<String,BufferedImage> imgCached=new HashMap<String,BufferedImage>();
	private ArtFontCached fontCached;
	
	
	public SealTemplate getPattern(String patternName){
		return this.cached.get(patternName);
	}
	
	public static SealTemplateCached load(String classPathPatternXml) throws Exception{
		SealTemplateCached instance=new SealTemplateCached();
		Document doc=parseXml(classPathPatternXml);
		Element root=doc.getRootElement();		
		String patternPath=root.attributeValue("patternPath");
		String fontPath=root.attributeValue("fontPath");
		loadPatternImgs(instance,patternPath);
		instance.fontCached=ArtFontCached.load(fontPath);
		
		@SuppressWarnings("unchecked")
		List<Element> patterns=root.elements();
		Iterator<Element> iter=patterns.iterator();
		while(iter.hasNext()){
			Element ptn=iter.next();
			SealTemplate sealPattern=parsePattern(instance,ptn);
			instance.cached.put(sealPattern.id, sealPattern);
		}
		
		return instance;
	}

	
	private static SealTemplateCached loadPatternImgs(SealTemplateCached cached,String imgPath) throws IOException{
		File dir = new File(imgPath);
		if (dir.exists() && dir.isDirectory()) {
			File[] imgs = dir.listFiles();
			for (File tmp : imgs) {
				//TODO file type validation
				String name = FileUtils.getBaseName(tmp);
				BufferedImage img=ImageIO.read(tmp);	
				cached.imgCached.put(name, img);
			}
		}
		return cached;
	}
	
	
	
	private static SealTemplate parsePattern(SealTemplateCached cached,Element ele){
		SealTemplate ptn=new SealTemplate();
		ptn.id=ele.attributeValue("id");
		ptn.name=ele.attributeValue("name");
		ptn.width=NumberUtils.parseInt(ele.attributeValue("width"), 0);
		ptn.height=NumberUtils.parseInt(ele.attributeValue("height"), 0);
		ptn.dented=Boolean.parseBoolean(ele.attributeValue("dented"));
		ptn.colorStr=ele.attributeValue("color");
		
		BufferedImage prototype=cached.imgCached.get(ptn.name);
		double sx=ptn.width*1.0/prototype.getWidth();
		double sy=ptn.height*1.0/prototype.getHeight();
		ptn.pattern=ImageUtils.scale(prototype, sx, sy);
		
		@SuppressWarnings("unchecked")
		List<Element> fonts=ele.elements("font");
		for(Element tmp:fonts){
			SealFont font=new SealFont();
			int x=NumberUtils.parseInt(tmp.attributeValue("x"), 0);
			int y=NumberUtils.parseInt(tmp.attributeValue("y"), 0);
			int w=NumberUtils.parseInt(tmp.attributeValue("width"), 0);
			int h=NumberUtils.parseInt(tmp.attributeValue("height"), 0);
			font.area=new Area(x,y,w,h);
			
			String family=tmp.attributeValue("family");
			int format=NumberUtils.parseInt(tmp.attributeValue("fontStyle"),0);
			int size=NumberUtils.parseInt(tmp.attributeValue("fontSize"),15);
			font.font=cached.fontCached.get(family, format, size);
			ptn.fonts.add(font);
		}
		return ptn;
	}
	
	
	
	private static Document parseXml(String classPathPatternXml) throws Exception{
		InputStream ins=SealTemplateCached.class.getClassLoader().getResourceAsStream(classPathPatternXml);
		SAXReader reader=new SAXReader();
		return reader.read(ins);
	}
	private SealTemplateCached(){};
}
