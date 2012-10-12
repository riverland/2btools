package org.river.rbtools.knifepenner.service.impl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import org.river.base.img.utils.ImageUtils;
import org.river.rbtools.knifepenner.service.ISealPatternService;
import org.river.rbtools.knifepenner.service.IStamperService;
import org.river.rbtools.knifepenner.type.StampSeal;
import org.river.rbtools.knifepenner.utils.SealTemplateCached;
/**
 * StamperServiceImpl
 * @author river
 * @create 20120607
 */
public class StamperServiceImpl implements IStamperService {
	
	private SealTemplateCached sealPatternCached;
	
	private ISealPatternService sealPatternService;

	private String sealConfig;

	@Override
	public BufferedImage stamp(BufferedImage cavas, List<StampSeal> sealList) throws Exception {
		if(cavas==null||sealList==null||sealList.size()==0) return cavas;
		Graphics2D g2d=cavas.createGraphics();
		for(StampSeal tmp:sealList){
			BufferedImage seal=sealPatternService.getPattern(tmp.getSealName());
			double scaleRate=ImageUtils.calcMinScaleRate(seal, tmp.getWidth(), tmp.getHeight());
			seal=ImageUtils.scale(seal, scaleRate, scaleRate);
			g2d.drawImage(seal, tmp.getX()-seal.getWidth(), tmp.getY()-seal.getHeight(), null);
		}
		g2d.dispose();
		return cavas;
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

	public ISealPatternService getSealPatternService() {
		return sealPatternService;
	}

	public void setSealPatternService(ISealPatternService sealPatternService) {
		this.sealPatternService = sealPatternService;
	}

}
