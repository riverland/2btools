package org.river.rbtools.knifepenner.action;

import org.apache.struts2.json.annotations.JSON;
import org.river.base.db.pagination.IPagination;
import org.river.base.db.pagination.Pagination;
import org.river.base.session.struts2.BaseAction;
import org.river.rbtools.knifepenner.db.bo.SealPattern;
import org.river.rbtools.knifepenner.service.ISealPatternService;

/**
 * PatternAction class
 * @author river
 * @create 20120602
 */
public class PatternAction extends BaseAction {
	public static final int DEFAULT_SCOPE=30;
	
	private IPagination<SealPattern> page=new Pagination<SealPattern>(null,0,DEFAULT_SCOPE,0);

	private ISealPatternService sealPatternService;



	/**
	 * action method
	 * @return
	 * @throws Exception
	 */
	public String list()throws Exception{
		page=sealPatternService.list(page.getFrom(), page.getScope());
		return SUCCESS;
	}
	
	
	@JSON(serialize=false)
	public ISealPatternService getSealPatternService() {
		return sealPatternService;
	}
	
	public void setSealPatternService(ISealPatternService sealPatternService) {
		this.sealPatternService = sealPatternService;
	}

	public IPagination<SealPattern> getPage() {
		return page;
	}

	public void setPage(IPagination<SealPattern> page) {
		this.page = page;
	}

}
