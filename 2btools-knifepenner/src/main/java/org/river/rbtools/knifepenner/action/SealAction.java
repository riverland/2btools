package org.river.rbtools.knifepenner.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.struts2.json.annotations.JSON;
import org.river.base.img.utils.ImageUtils;
import org.river.base.session.ISessionConnector;
import org.river.base.session.struts2.BaseAction;
import org.river.base.tools.string.StringUtils.Agorithm;
import org.river.rbtools.knifepenner.service.IKnifePenner;

/**
 * SealAction class
 * @author river
 * @create 20120602
 */
public class SealAction extends BaseAction {
	/**params text to carve in the seal */
	private String text;
	
	/**the seal pattern id*/
	private String pattern;
	
	/**result stream*/
	private InputStream sealIns;	
	
	/**result or parameter  key **/
	private String sealKey;
	
	/**memcached connector*/
	private ISessionConnector memcachedSessionConnector;

	/**service*/
	private IKnifePenner knifePenner;
	
	/**
	 * action method
	 * @return
	 * @throws Exception
	 */
	public String carve()throws Exception{
		sealKey=org.river.base.tools.string.StringUtils.digest(pattern+text, Agorithm.MD5);
		if(memcachedSessionConnector.get(sealKey)==null){
			BufferedImage sealImg=knifePenner.carve(pattern, text);
			byte[] sealBytes=ImageUtils.bufferedImg2ByteArray(sealImg);
			memcachedSessionConnector.add(sealKey, 30, sealBytes);
		}

		return SUCCESS;
	}
	
	/**
	 * show the seal store in the cached
	 * @return
	 * @throws Exception
	 */
	public String showSeal()throws Exception{
		byte[] sealBytes=(byte[]) memcachedSessionConnector.get(sealKey);
		if(sealBytes!=null){
			sealIns=new ByteArrayInputStream(sealBytes);
		}
		return SUCCESS;
	}
	
	@JSON(serialize=false)
	public ISessionConnector getMemcachedSessionConnector() {
		return memcachedSessionConnector;
	}

	public void setMemcachedSessionConnector(
			ISessionConnector memcachedSessionConnector) {
		this.memcachedSessionConnector = memcachedSessionConnector;
	}	
	
	@JSON(serialize=false)
	public IKnifePenner getKnifePenner() {
		return knifePenner;
	}

	public void setKnifePenner(IKnifePenner knifePenner) {
		this.knifePenner = knifePenner;
	}

	@JSON(serialize=false)
	public InputStream getSealIns() {
		return sealIns;
	}

	public void setSealIns(InputStream sealIns) {
		this.sealIns = sealIns;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	public String getSealKey() {
		return sealKey;
	}

	public void setSealKey(String sealKey) {
		this.sealKey = sealKey;
	}
	
}
