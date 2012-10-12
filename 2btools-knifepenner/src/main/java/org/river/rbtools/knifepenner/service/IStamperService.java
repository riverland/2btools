package org.river.rbtools.knifepenner.service;

import java.awt.image.BufferedImage;
import java.util.List;

import org.river.rbtools.knifepenner.type.StampSeal;

/**
 * IStamperService interface
 * @author river
 * @create 20120607
 */
public interface IStamperService {
	/**
	 * stamp
	 * @param cavas
	 * @param sealList
	 * @return
	 * @throws Exception 
	 */
	public BufferedImage stamp(BufferedImage cavas,List<StampSeal> sealList) throws Exception;
}
