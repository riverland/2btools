package org.river.rbtools.knifepenner;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.river.base.db.test.SpringTestCase;
import org.river.rbtools.knifepenner.service.IKnifePenner;

public class KnifePennerTestCase extends SpringTestCase {
	private IKnifePenner knifePenner;
	
	public void testCarve() throws Exception{
		BufferedImage img=knifePenner.carve("黑印01", "吉祥如意");
		ImageIO.write(img, "png", new File("/mnt/testdir/carve.png"));
	}

	public IKnifePenner getKnifePenner() {
		return knifePenner;
	}

	public void setKnifePenner(IKnifePenner knifePenner) {
		this.knifePenner = knifePenner;
	}
}
