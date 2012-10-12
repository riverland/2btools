package org.river.rbtools.artword.service.impl;

import org.river.base.img.ICanvas;
import org.river.base.img.IFilter;
import org.river.base.img.IFilterCached;
import org.river.base.img.filters.ArtWordFilterCached;
import org.river.rbtools.artword.service.IArtWordContext;

public class ArtWordContextImpl implements IArtWordContext {

	private IFilterCached filterCached;
	private String configXml;

	public void initialize() throws Exception {
		filterCached = ArtWordFilterCached.load(configXml);
	}

	@Override
	public void render(ICanvas canvas, String... filters) {
		if (canvas != null && filters != null && filters.length > 0) {
			for (String name : filters) {
				IFilter filter = filterCached.getFilter(name);
				canvas.addFilter(name, filter == null ? null : filter);
			}
		}
	}

	public IFilterCached getFilterCached() {
		return filterCached;
	}

	public void setFilterCached(IFilterCached filterCached) {
		this.filterCached = filterCached;
	}

	public String getConfigXml() {
		return configXml;
	}

	public void setConfigXml(String configXml) {
		this.configXml = configXml;
	}

}
