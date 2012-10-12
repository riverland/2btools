package org.river.rbtools.doc.service;

import org.river.rbtools.doc.db.model.Document;
import org.river.rbtools.utils.exception.FunctionException;

public interface IDocumentService {
	
	/**
	 * remove the document from directory
	 * @param id
	 */
	public void rmdoc(Long id);
	
	/**
	 * create doc
	 * @param name
	 * @param text
	 * @return
	 * @throws FunctionException 
	 * @throws Exception 
	 */
	public Document mkdoc(Document doc,String text,Long dirId) throws FunctionException, Exception;
	
	/**
	 * rename the document
	 * @param name
	 * @param id
	 */
	public Document rename(String name,Long id);
	
	/**
	 * get the document
	 * @param name
	 * @param id
	 * @throws Exception 
	 */
	public Document get(String name,Long id) throws Exception;
	
	/**
	 * get the document Entity by Url
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public Document getByUrl(String url) throws Exception;
}
