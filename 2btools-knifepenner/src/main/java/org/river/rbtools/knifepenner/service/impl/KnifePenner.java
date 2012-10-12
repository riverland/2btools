package org.river.rbtools.knifepenner.service.impl;

import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.util.List;

import org.river.base.img.utils.ColorUtils;
import org.river.base.img.utils.ImageUtils;
import org.river.rbtools.knifepenner.service.IKnifePenner;
import org.river.rbtools.knifepenner.utils.SealFont;
import org.river.rbtools.knifepenner.utils.SealTemplate;
import org.river.rbtools.knifepenner.utils.SealTemplateCached;

public class KnifePenner implements IKnifePenner {
	
	private SealTemplateCached sealPatternCached;

	private String sealConfig;



	@Override
	public BufferedImage carve(String pattern, String text) throws Exception {
		SealTemplate sealPattern=sealPatternCached.getPattern(pattern);
		if(sealPattern==null){
			throw new Exception("pattern:"+sealPattern+"non exist");
		}		
		
		return doCarve(sealPattern,text);
	}
	
	private BufferedImage doCarve(SealTemplate pattern, String text){
		BufferedImage ori=new BufferedImage(pattern.getWidth(),pattern.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics2D g=ori.createGraphics();
		BufferedImage dest=g.getDeviceConfiguration().createCompatibleImage(pattern.getWidth(),pattern.getHeight(), Transparency.TRANSLUCENT);
		g.dispose();
		Graphics2D g2d=dest.createGraphics();
		g2d.setColor(ColorUtils.parse(pattern.getColorStr()));
		g2d.drawImage(pattern.getPattern(), 0, 0, null);
		List<SealFont> fonts=pattern.getFonts();
		for(int i=0;i<text.length()&&i<fonts.size();i++){
			g2d.setFont(fonts.get(i).getFont());
			g2d.drawString(text.substring(i, i+1),fonts.get(i).getArea().x, fonts.get(i).getArea().y);
		}
		
		if(pattern.isDented()){
			dest=ImageUtils.alpha(dest, pattern.getColorStr(), 0);
		}
		g2d.dispose();
		return dest;
	}
	
	@SuppressWarnings("unused")
	private void initialize() throws Exception{
		sealPatternCached=SealTemplateCached.load(sealConfig);
	}	


	public SealTemplateCached getSealPatternCached() {
		return sealPatternCached;
	}


	public void setSealPatternCached(SealTemplateCached sealPatternCached) {
		this.sealPatternCached = sealPatternCached;
	}

	public String getSealConfig() {
		return sealConfig;
	}


	public void setSealConfig(String sealConfig) {
		this.sealConfig = sealConfig;
	}


}
