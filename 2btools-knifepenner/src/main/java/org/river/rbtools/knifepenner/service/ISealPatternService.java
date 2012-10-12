package org.river.rbtools.knifepenner.service;

import java.awt.image.BufferedImage;

import org.river.base.db.pagination.IPagination;
import org.river.rbtools.knifepenner.db.bo.SealPattern;

/**
 * seal pattern service interface
 * @author river
 * @create 20120602
 */
public interface ISealPatternService {
	
	/**
	 * list the patterns with page
	 * @param from
	 * @param scope
	 * @return
	 * @throws Exception 
	 */
	public IPagination<SealPattern> list(int from,int scope) throws Exception;
	
	/**
	 * get the pattern
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public BufferedImage getPattern(String name) throws Exception;
}
