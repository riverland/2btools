package org.river.rbtools.knifepenner;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.river.base.db.orm.hibernate.HibernateDao;
import org.river.base.db.pagination.IPagination;
import org.river.base.db.test.HibernateTxTestCase;
import org.river.rbtools.knifepenner.db.bo.SealPattern;
import org.river.rbtools.knifepenner.service.ISealPatternService;

public class PatternServiceTestCase extends HibernateTxTestCase {
	HibernateDao<SealPattern,Long> sealPatternDao;
	
	ISealPatternService sealPatternService;

	public void testList() throws Exception{
		IPagination<SealPattern> page=sealPatternService.list(0, 30);
		System.out.println(page.getItems().size());
	}

	public void testAdd() throws IOException{
		String path="/mnt/workspace/artword/seal";
		File file=new File(path);
		String names[]=file.list();
		for(String name:names){
			System.out.println(path+"/"+name);
			SealPattern tmpPattern=new SealPattern();
			tmpPattern.setPatternUrl("seal/"+name);
			
			int point=name.lastIndexOf(".");
			if(point>0){
				tmpPattern.setName(name.substring(0,point));
			}
			
			BufferedImage img=ImageIO.read(new File(path+"/"+name));
			tmpPattern.setWidth(img.getWidth());
			tmpPattern.setHeight(img.getHeight());
			sealPatternDao.save(tmpPattern);
		}
		
		setComplete();
	}	
	
	public HibernateDao<SealPattern, Long> getSealPatternDao() {
		return sealPatternDao;
	}

	public void setSealPatternDao(HibernateDao<SealPattern, Long> sealPatternDao) {
		this.sealPatternDao = sealPatternDao;
	}	


	public ISealPatternService getSealPatternService() {
		return sealPatternService;
	}

	public void setSealPatternService(ISealPatternService sealPatternService) {
		this.sealPatternService = sealPatternService;
	}
}
