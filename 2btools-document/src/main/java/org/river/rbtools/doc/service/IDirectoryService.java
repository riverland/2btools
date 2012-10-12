package org.river.rbtools.doc.service;

import org.river.rbtools.doc.db.model.Directory;
import org.river.rbtools.doc.db.model.Document;
/**
 * directory service interface
 * @author river
 * @create 20120611
 */
public interface IDirectoryService {	
	
	/**
	 * get by the primary key
	 * @param id
	 * @return
	 */
	public Directory get(Long id);
	
	/**
	 * mkdir
	 * @param path
	 * @return
	 * @throws Exception 
	 */
	public Directory mkdir(String path) throws Exception;	
	
	/**
	 * get directory by path
	 * @param path
	 * @return
	 * @throws Exception 
	 */
	public Directory getByPath(String path) throws Exception;

	/**
	 * add document to directory
	 * @param doc
	 * @param dirId
	 */
	public void addDocument(Document doc,Long dirId);
	
	/**
	 * rename the directory
	 * @param name
	 * @param id
	 */
	public Directory rename(String name,Long id);
	
	/**
	 * remove directory
	 * @param id
	 */
	public void rmdir(Long id);
}
