package org.river.rbtools.doc.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.river.base.db.orm.hibernate.HibernateDao;
import org.river.base.entity.EntityFilter;
import org.river.base.entity.IFilter;
import org.river.rbtools.doc.db.model.Directory;
import org.river.rbtools.doc.db.model.Document;
import org.river.rbtools.doc.service.IDocumentService;
import org.river.rbtools.doc.utils.DIRUtils;
import org.river.rbtools.doc.utils.DOCMessage;
import org.river.rbtools.utils.exception.FunctionException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class DocumentServiceImpl implements IDocumentService {

	private HibernateDao<Document, Long> documentDao;
	private HibernateDao<Directory, Long> directoryDao;
	private String basePath;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void rmdoc(Long id) {
		documentDao.delByPrimaryKey(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Document rename(String name, Long id) {
		Document doc = documentDao.get(id);
		if (doc != null) {
			doc.setName(name);
		}
		documentDao.saveOrUpdate(doc);
		return doc;
	}
	

	@Override
	public Document get(String name, Long id) throws Exception {
		IFilter filter=EntityFilter.newInstance();
		filter.eq("directory.id", id);
		filter.eq("name", name);
		return documentDao.unique(filter);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Document mkdoc(Document doc, String text, Long dirId)
			throws Exception {
		Directory dir = directoryDao.get(dirId);
		if (dir == null)
			throw new FunctionException(DOCMessage.ERR_DIR_NON_EXIST);
		Document oldDoc=get(doc.getName(), dir.getId());
		
		String path = basePath + dir.getUrl();
		File dirFile = new File(path);
		File docFile=null;
		
		try {
			if(oldDoc==null){
				if (!dirFile.exists()) {
					FileUtils.forceMkdir(dirFile);
				}
				String docFileName= doc.getName()+"_"+System.currentTimeMillis();
				docFile = new File(path + docFileName);
				doc.setUrl(dir.getUrl() + docFileName);
			}else{
				docFile=new File(basePath+oldDoc.getUrl());
			}			
			
			FileUtils.writeStringToFile(docFile, text);
		} catch (IOException e) {
			// TODO delete the hard code
			throw new FunctionException("create file exception");
		}
		
		if(oldDoc==null){
			
			doc.setDirectory(dir);
			documentDao.save(doc);
		}else{
			doc=oldDoc;
		}

		return doc;
	}
	

	@Override
	public Document getByUrl(String url) throws Exception {
		IFilter filterObj=EntityFilter.newInstance();
		filterObj.eq("url", url);
		return documentDao.unique(filterObj);
	}

	@SuppressWarnings("unused")
	private void initialize() {
		if (StringUtils.isEmpty(basePath)
				|| !basePath.endsWith(DIRUtils.URL_SEPERATOR)) {
			basePath = basePath + DIRUtils.URL_SEPERATOR;
		}
	}

	public HibernateDao<Document, Long> getDocumentDao() {
		return documentDao;
	}

	public void setDocumentDao(HibernateDao<Document, Long> documentDao) {
		this.documentDao = documentDao;
	}

	public HibernateDao<Directory, Long> getDirectoryDao() {
		return directoryDao;
	}

	public void setDirectoryDao(HibernateDao<Directory, Long> directoryDao) {
		this.directoryDao = directoryDao;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}



}
