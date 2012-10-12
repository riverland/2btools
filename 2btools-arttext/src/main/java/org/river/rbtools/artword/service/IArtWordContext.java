package org.river.rbtools.artword.service;

import org.river.base.img.ICanvas;


public interface IArtWordContext {
	public void render(ICanvas canvas,String...filters);
}
