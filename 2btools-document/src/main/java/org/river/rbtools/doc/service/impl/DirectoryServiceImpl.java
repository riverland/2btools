package org.river.rbtools.doc.service.impl;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.river.base.db.orm.hibernate.HibernateDao;
import org.river.base.entity.EntityFilter;
import org.river.base.entity.IFilter;
import org.river.rbtools.doc.db.model.Directory;
import org.river.rbtools.doc.db.model.Document;
import org.river.rbtools.doc.service.IDirectoryService;
import org.river.rbtools.doc.utils.DIRUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * IDirectoryService implement class
 * @author river
 */
public class DirectoryServiceImpl implements IDirectoryService {
	
	private HibernateDao<Directory,Long> directoryDao;
	private HibernateDao<Document,Long> documentDao;


	@Override
	public Directory get(Long id) {
		return directoryDao.get(id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public Directory mkdir(String path) throws Exception {
		if(!path.endsWith(DIRUtils.URL_SEPERATOR)){
			path=path+DIRUtils.URL_SEPERATOR;
		}
		Directory dir=this.getByPath(path);
		if(dir==null){
			dir=new Directory();
		}
		
		dir.setUrl(path);
		dir.setName(DIRUtils.getDIRName(path));
		
		Directory parent=null;
		String parentPath=DIRUtils.parentPath(path);
		if(!StringUtils.isEmpty(parentPath)){
			parent=this.getByPath(parentPath);
			if(parent==null){
				parent=mkdir(parentPath);
			}
		}
		dir.setParentDir(parent);
		directoryDao.save(dir);
		return dir;
	}

	@Override
	public Directory getByPath(String path) throws Exception {
		IFilter filterObj=EntityFilter.newInstance();
		filterObj.eq("url", path);
		
		return directoryDao.unique(filterObj);
	}
	

	@Override
	public void addDocument(Document doc, Long dirId) {
		Directory dir=directoryDao.get(dirId);
		if(dir!=null){
			dir.getDocuments().add(doc);
		}
		directoryDao.saveOrUpdate(dir);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Directory rename(String name, Long id) {
		if(StringUtils.isEmpty(name)||id==null)return null;
		Directory dir=directoryDao.get(id);
		if(dir==null)return null;
		
		String oldUrl=dir.getUrl();
		String newUrl=DIRUtils.parentPath(oldUrl)+DIRUtils.URL_SEPERATOR+name+DIRUtils.URL_SEPERATOR;
		String hql="update Directory d set d.url=:newUrl||substring(d.url,:offset+1) where d.url like :oldLike";
		Query query=directoryDao.getSession().createQuery(hql);
		query.setParameter("newUrl", newUrl);
		query.setParameter("offset", oldUrl.length());
		query.setParameter("oldLike", oldUrl+"%");
		query.executeUpdate();
		dir.setName(name);
		dir.setUrl(newUrl);
		directoryDao.saveOrUpdate(dir);
		
		//this.doRenameDocUrl(oldUrl, newUrl);	
		
		return dir;
	}
	
//	/***/
//	private void doRenameDocUrl(String oldUrl,String newUrl){
//		String hql="update Document d set d.url=:newUrl||substring(d.url,:offset+1) where d.url like :oldLike";
//		Query query=documentDao.getSession().createQuery(hql);
//		query.setParameter("newUrl", newUrl);
//		query.setParameter("offset", oldUrl.length());
//		query.setParameter("oldLike", oldUrl+"%");
//		query.executeUpdate();
//	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void rmdir(Long id) {
		directoryDao.delByPrimaryKey(id);
	}
	
	
	
	public HibernateDao<Directory, Long> getDirectoryDao() {
		return directoryDao;
	}

	public void setDirectoryDao(HibernateDao<Directory, Long> directoryDao) {
		this.directoryDao = directoryDao;
	}


	public HibernateDao<Document, Long> getDocumentDao() {
		return documentDao;
	}

	public void setDocumentDao(HibernateDao<Document, Long> documentDao) {
		this.documentDao = documentDao;
	}
}
