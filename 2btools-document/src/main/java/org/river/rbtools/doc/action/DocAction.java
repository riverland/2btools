package org.river.rbtools.doc.action;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.json.annotations.JSON;
import org.river.base.session.struts2.BaseAction;
import org.river.rbtools.doc.db.model.Directory;
import org.river.rbtools.doc.db.model.Document;
import org.river.rbtools.doc.service.IDirectoryService;
import org.river.rbtools.doc.service.IDocumentService;
/**
 * MVC action class
 * @author river
 * @create 20120611
 */
public class DocAction extends BaseAction {
	private static final String OPT_TYPE_DOC="doc";
	private static final String OPT_TYPE_DIR="dir";
	private IDirectoryService directoryService;
	private IDocumentService documentService;


	private Directory directory=new Directory();
	private Document doc=new Document();

	/**params*/
	private String type;
	private String docText;


	/**
	 * action method
	 * @param directory.id
	 * @return
	 * @throws Exception
	 */
	public String open()throws Exception{
		if(directory.getId()!=null){
			directory=directoryService.get(directory.getId());
		}
		
		return SUCCESS;
	}
	
	/**
	 * action method
	 * @param directory.url
	 * @return
	 * @throws Exception
	 */
	public String mkdir()throws Exception{
		if(StringUtils.isNotEmpty(directory.getUrl())){
			directory=directoryService.mkdir(directory.getUrl());
		}
		
		return SUCCESS;
	}
	 
	/**
	 * action method create the document
	 * @return
	 * @throws Exception
	 */
	public String mkdoc()throws Exception{
		if(directory.getId()!=null&&StringUtils.isNotEmpty(docText)&&StringUtils.isNotEmpty(doc.getName())){
			documentService.mkdoc(doc, docText, directory.getId());
		}
		return SUCCESS;
	}
	
	/**
	 * action method
	 * @return
	 * @throws Exception
	 */
	public String rm()throws Exception{
		if(directory.getId()!=null&&OPT_TYPE_DIR.equals(type)){
			directoryService.rmdir(directory.getId());
		}else if(doc.getId()!=null&&OPT_TYPE_DOC.equals(type)){
			documentService.rmdoc(doc.getId());
		}
		
		return SUCCESS;
	}
	
	/**
	 * action method
	 * @return
	 * @throws Exception
	 */
	public String rename()throws Exception{
		if(StringUtils.isNotEmpty(directory.getName())&&OPT_TYPE_DIR.equals(type)){
			Directory dir=directoryService.rename(directory.getName(), directory.getId());
			if(dir!=null){
				directory=dir.getParentDir();
			}
		}else if(StringUtils.isNotEmpty(doc.getName())&&OPT_TYPE_DOC.equals(type)){
			doc=documentService.rename(doc.getName(), doc.getId());
			if(doc!=null){
				directory=doc.getDirectory();
			}
		}
		return SUCCESS;
	}

	public Directory getDirectory() {
		return directory;
	}

	public void setDirectory(Directory directory) {
		this.directory = directory;
	}
	
	@JSON(serialize=false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}
	

	@JSON(serialize=false)
	public String getDocText() {
		return docText;
	}

	public void setDocText(String docText) {
		this.docText = docText;
	}

	@JSON(serialize=false)
	public IDirectoryService getDirectoryService() {
		return directoryService;
	}

	public void setDirectoryService(IDirectoryService directoryService) {
		this.directoryService = directoryService;
	}
	
	@JSON(serialize=false)
	public IDocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(IDocumentService documentService) {
		this.documentService = documentService;
	}
	
	
}
