package org.river.rbtools.knifepenner.service;

import java.awt.image.BufferedImage;

/**
 * service KnifePenner provide
 * @author river
 */
public interface IKnifePenner {
	/**
	 * generate the Seal {@link BufferedImage}
	 * @param pattern
	 * @param text
	 * @return
	 * @throws Exception 
	 */
	public BufferedImage carve(String pattern,String text) throws Exception;

}
