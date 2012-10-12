package org.river.rbtools.artword.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.json.annotations.JSON;
import org.river.base.img.Area;
import org.river.base.img.ICanvas;
import org.river.base.img.canvas.ArtWordCanvas;
import org.river.base.img.canvas.CanvasLayout;
import org.river.base.session.ISessionConnector;
import org.river.base.session.struts2.BaseAction;
import org.river.base.tools.string.StringUtils.Agorithm;
import org.river.rbtools.artword.bean.ImageBean;
import org.river.rbtools.artword.service.IArtWordContext;

public class ArtTextAction extends BaseAction{
	private static int WIDTH_DEFAULT=440;
	private static int HEIGHT_DEFAULT=440;
	
	/** service */
	private IArtWordContext artWordContext;

	/** result */
	private InputStream imgStream;

	/** params and result */
	private ImageBean imgInfo = new ImageBean();
	
	/**memcached connector*/
	private ISessionConnector memcachedSessionConnector;


	/**
	 * action method
	 * @return
	 * @throws Exception
	 */
	public String generate() throws Exception {
		if (imgInfo != null && !StringUtils.isEmpty(imgInfo.getContent())) {
			this.validArea(imgInfo);
			String content=org.river.base.tools.string.StringUtils.filterHtml(imgInfo.getContent());
			String key=org.river.base.tools.string.StringUtils.digest(content+imgInfo.getFilterName(), Agorithm.MD5);
			
			if(memcachedSessionConnector.get(key)==null){
				CanvasLayout layout = new CanvasLayout();
				layout.setInArea(new Area(0, 0, imgInfo.getWidth(), imgInfo.getHeight()));
				ICanvas canvas = new ArtWordCanvas(layout,content);
				artWordContext.render(canvas, imgInfo.getFilterName());
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				canvas.export("png", os);
				byte []imgBytes=os.toByteArray();
				memcachedSessionConnector.add(key, 30, imgBytes);
			}
			imgInfo.setKey(key);			
		}
		return SUCCESS;
	}
	
	/**
	 * action method:
	 * get the image from cached in the msession
	 * @return
	 * @throws Exception
	 */
	public String loadArtText()throws Exception{
		if (imgInfo != null && !StringUtils.isEmpty(imgInfo.getKey())){
			byte []imgBytes =(byte[]) memcachedSessionConnector.get(imgInfo.getKey());			
			imgStream=new ByteArrayInputStream(imgBytes);
		}
		return SUCCESS;
	}
	
	private void validArea(ImageBean imgInfo){

		if(ImageBean.DIRECTION_V.equals(imgInfo.getDirection())){
			imgInfo.setWidth(-1);
			if(imgInfo.getHeight()==0){
				imgInfo.setHeight(HEIGHT_DEFAULT);
			}
		}else{
			imgInfo.setHeight(-1);
			if(imgInfo.getWidth()==0){
				imgInfo.setWidth(WIDTH_DEFAULT);
			}
		}
	}

	@JSON(serialize=false)
	public IArtWordContext getArtWordContext() {
		return artWordContext;
	}

	public void setArtWordContext(IArtWordContext artWordContext) {
		this.artWordContext = artWordContext;
	}

	@JSON(serialize=false)
	public InputStream getImgStream() {
		return imgStream;
	}

	public void setImgStream(InputStream imgStream) {
		this.imgStream = imgStream;
	}

	public ImageBean getImgInfo() {
		return imgInfo;
	}

	public void setImgInfo(ImageBean imgInfo) {
		this.imgInfo = imgInfo;
	}

	public ISessionConnector getMemcachedSessionConnector() {
		return memcachedSessionConnector;
	}

	public void setMemcachedSessionConnector(
			ISessionConnector memcachedSessionConnector) {
		this.memcachedSessionConnector = memcachedSessionConnector;
	}
}
