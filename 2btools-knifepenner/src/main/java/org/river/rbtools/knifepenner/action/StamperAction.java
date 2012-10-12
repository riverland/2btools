package org.river.rbtools.knifepenner.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.json.annotations.JSON;
import org.river.base.img.utils.ImageUtils;
import org.river.base.session.ISessionConnector;
import org.river.base.session.struts2.BaseAction;
import org.river.base.tools.string.StringUtils;
import org.river.base.tools.string.StringUtils.Agorithm;
import org.river.rbtools.knifepenner.service.IStamperService;
import org.river.rbtools.knifepenner.type.StampSeal;

/**
 * StamperAction class
 * @author river
 * @create 20120602
 */
public class StamperAction extends BaseAction {
	
	/**parameter  key **/
	private String canvasKey;	
	
	/**parameter  key **/
	private String resultKey;
	
	private File file;


	/**params sealList*/
	private List<StampSeal> sealList=new ArrayList<StampSeal>();
	
	/**result stream*/
	private InputStream canvasIns;
	
	private IStamperService stamperService;
	
	/**memcached connector*/
	private ISessionConnector memcachedSessionConnector;
	
	/**
	 * action method
	 * @return
	 * @throws Exception
	 */
	public String upload() throws Exception{
		if(file!=null){
			byte[] bytes=FileUtils.readFileToByteArray(file);
			resultKey=StringUtils.digest(bytes, Agorithm.MD5);
			memcachedSessionConnector.add(resultKey, 3*60, bytes);
		}
		return SUCCESS;
	}


	/**
	 * action method
	 * @return
	 * @throws Exception
	 */
	public String stamp() throws Exception{
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		
		ObjectOutputStream ois=new ObjectOutputStream(baos);
		if(canvasKey!=null){
			ois.write(canvasKey.getBytes());
		}
		ois.writeObject(sealList);
		resultKey=StringUtils.digest(baos.toByteArray(), Agorithm.MD5);
		if(memcachedSessionConnector.get(resultKey)==null&&canvasKey!=null){
			ByteArrayInputStream ins=new ByteArrayInputStream((byte[]) memcachedSessionConnector.get(canvasKey));
			BufferedImage canvas=ImageIO.read(ins);
			BufferedImage img=stamperService.stamp(canvas, sealList);
			byte[] bytes=ImageUtils.bufferedImg2ByteArray(img);
			memcachedSessionConnector.add(resultKey, 30, bytes);
		}
		
		return SUCCESS;
	}
	
	/**
	 * action method
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception{
		byte[] bytes=(byte[]) memcachedSessionConnector.get(resultKey);
		canvasIns=new ByteArrayInputStream(bytes);
		return SUCCESS;
	}

	
	public String getResultKey() {
		return resultKey;
	}


	public void setResultKey(String resultKey) {
		this.resultKey = resultKey;
	}

	public String getCanvasKey() {
		return canvasKey;
	}

	public void setCanvasKey(String canvasKey) {
		this.canvasKey = canvasKey;
	}

	public List<StampSeal> getSealList() {
		return sealList;
	}

	public void setSealList(List<StampSeal> sealList) {
		this.sealList = sealList;
	}

	@JSON(serialize=false)
	public InputStream getCanvasIns() {
		return canvasIns;
	}

	public void setCanvasIns(InputStream canvasIns) {
		this.canvasIns = canvasIns;
	}
	
	@JSON(serialize=false)
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	
	@JSON(serialize=false)
	public IStamperService getStamperService() {
		return stamperService;
	}

	public void setStamperService(IStamperService stamperService) {
		this.stamperService = stamperService;
	}
	
	@JSON(serialize=false)
	public ISessionConnector getMemcachedSessionConnector() {
		return memcachedSessionConnector;
	}


	public void setMemcachedSessionConnector(
			ISessionConnector memcachedSessionConnector) {
		this.memcachedSessionConnector = memcachedSessionConnector;
	}
}
