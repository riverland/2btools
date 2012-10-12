package org.river.rbtools.knifepenner.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.river.base.db.orm.hibernate.HibernateDao;
import org.river.base.db.pagination.IPagination;
import org.river.base.entity.EntityFilter;
import org.river.base.entity.util.Sort;
import org.river.rbtools.knifepenner.db.bo.SealPattern;
import org.river.rbtools.knifepenner.service.ISealPatternService;
/**
 * SealPatternServiceImpl
 * @author river
 * @create 20120603
 */
public class SealPatternServiceImpl implements ISealPatternService {	
	private Map<String,BufferedImage> cached=new HashMap<String,BufferedImage>();
	
	private HibernateDao<SealPattern,Long> sealPatternDao;
	
	private String patternBaseDir;
	
	private boolean initialized=false;


	@Override
	public IPagination<SealPattern> list(int from, int scope) throws Exception {
		return sealPatternDao.search(EntityFilter.newInstance(), from, scope, Sort.newInstance().desc("createTime"));
	}
	

	@Override
	public BufferedImage getPattern(String name) throws Exception {
		if(!initialized) {
			this.initialize();
			initialized=true;
		}
		return cached.get(name);
	}
	
	public void initialize() throws Exception{
		List<SealPattern> all=sealPatternDao.all(Sort.newInstance().asc("id"));
		for(SealPattern tmp:all){
			String fileName=patternBaseDir+tmp.getPatternUrl();
			BufferedImage img=ImageIO.read(new File(fileName));
			cached.put(tmp.getName(), img);
		}
	}

	public HibernateDao<SealPattern, Long> getSealPatternDao() {
		return sealPatternDao;
	}

	public void setSealPatternDao(HibernateDao<SealPattern, Long> sealPatternDao) {
		this.sealPatternDao = sealPatternDao;
	}		


	public String getPatternBaseDir() {
		return patternBaseDir;
	}

	public void setPatternBaseDir(String patternBaseDir) {
		this.patternBaseDir = patternBaseDir;
	}

}
