package org.river.rbtools.knifepenner;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.river.base.db.test.HibernateTxTestCase;
import org.river.rbtools.knifepenner.service.IStamperService;
import org.river.rbtools.knifepenner.type.StampSeal;

public class StamperServiceTestCase extends HibernateTxTestCase {
	private IStamperService stamperService;
	
	public void testStamp() throws Exception{
		List<StampSeal> sealList=new ArrayList<StampSeal>();
		StampSeal seal01=new StampSeal();
		seal01.setHeight(60);
		seal01.setWidth(60);
		seal01.setSealName("长形印章01");
		seal01.setX(289);
		seal01.setY(239);
		sealList.add(seal01);
		BufferedImage cavas=ImageIO.read(new File("/mnt/workspace/workspace/work-src/style/2btools/modules/stamper/css/text.png"));
		BufferedImage img=stamperService.stamp(cavas, sealList);
		ImageIO.write(img, "png", new File("/mnt/testdir/1.png"));
	}

	public IStamperService getStamperService() {
		return stamperService;
	}

	public void setStamperService(IStamperService stamperService) {
		this.stamperService = stamperService;
	}
}
