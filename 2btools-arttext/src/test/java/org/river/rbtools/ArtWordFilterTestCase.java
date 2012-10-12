package org.river.rbtools;

import org.river.base.db.test.SpringTestCase;
import org.river.base.img.Area;
import org.river.base.img.ICanvas;
import org.river.base.img.canvas.ArtWordCanvas;
import org.river.base.img.canvas.CanvasLayout;
import org.river.rbtools.artword.service.IArtWordContext;

public class ArtWordFilterTestCase extends SpringTestCase {
	/** service */
	private IArtWordContext artWordContext;

	public void testBeikeFilter() throws Exception{
		String text="什么叫特权,法律管不住他，他不守法你没办法.我就住在钓鱼台旁边，每天都有特权者来来去去，他一来就交通管制，别人都不能走，就他能走，你凭什么就可以违反交通规则.什么叫特权,法律管不住他，他不守法你没办法.我就住在钓鱼台旁边，每天都有特权者来来去去，他一来就交通管制，别人都不能走，就他能走，你凭什么就可以违反交通规则.什么叫特权,法律管不住他，他不守法你没办法.我就住在钓鱼台旁边，每天都有特权者来来去去，他一来就交通管制，别人都不能走，就他能走，你凭什么就可以违反交通规则.什么叫特权,法律管不住他，他不守法你没办法.我就住在钓鱼台旁边，每天都有特权者来来去去，他一来就交通管制，别人都不能走，就他能走，你凭什么就可以违反交通规则.什么叫特权,法律管不住他，他不守法你没办法.我就住在钓鱼台旁边，每天都有特权者来来去去，他一来就交通管制，别人都不能走，就他能走，你凭什么就可以违反交通规则.什么叫特权,法律管不住他，他不守法你没办法.我就住在钓鱼台旁边，每天都有特权者来来去去，他一来就交通管制，别人都不能走，就他能走，你凭什么就可以违反交通规则.";
		CanvasLayout layout = new CanvasLayout();
		layout.setInArea(new Area(0, 0, -1, 440));
		ICanvas canvas = new ArtWordCanvas(layout,text);
		artWordContext.render(canvas, "banzhi_caoshu");
		canvas.export("png", "/mnt/testdir/beike.png");
	}
	
	public IArtWordContext getArtWordContext() {
		return artWordContext;
	}

	public void setArtWordContext(IArtWordContext artWordContext) {
		this.artWordContext = artWordContext;
	}

}
