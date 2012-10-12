package org.river.rbtools;

import java.io.IOException;

import org.river.base.db.test.SpringTestCase;
import org.river.base.img.Area;
import org.river.base.img.ICanvas;
import org.river.base.img.canvas.ArtWordCanvas;
import org.river.base.img.canvas.CanvasLayout;
import org.river.rbtools.artword.service.IArtWordContext;

public class FilterTestCase extends SpringTestCase {
	/**service*/
	private IArtWordContext artWordContext;

	public void testCache() throws IOException{
		CanvasLayout layout=new CanvasLayout();
		layout.setInArea(new Area(0,0,-1,462));
		ICanvas canvas=new ArtWordCanvas(layout,"这个世界真透阿蛤蛤蛤蛤蛤蛤哈哈哈哈");
		artWordContext.render(canvas, "zhujian_maoti");
		canvas.export("png", "/mnt/kd.png");
	}
	
	public IArtWordContext getArtWordContext() {
		return artWordContext;
	}
	public void setArtWordContext(IArtWordContext artWordContext) {
		this.artWordContext = artWordContext;
	}
}
