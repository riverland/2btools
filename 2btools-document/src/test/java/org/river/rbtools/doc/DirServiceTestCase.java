package org.river.rbtools.doc;

import java.util.ArrayList;
import java.util.List;

import org.river.base.db.orm.hibernate.HibernateDao;
import org.river.base.db.test.HibernateTxTestCase;
import org.river.base.entity.util.Sort;
import org.river.rbtools.doc.db.model.Directory;
import org.river.rbtools.doc.db.model.Document;
import org.river.rbtools.doc.service.IDirectoryService;

public class DirServiceTestCase extends HibernateTxTestCase {
	private IDirectoryService directoryService;
	private HibernateDao<Directory,Long> directoryDao;


	public void testMkdir() throws Exception{
		directoryService.mkdir("tech/program/c++");
		setComplete();
	}
	
	public void testAddDoc(){
		List<Directory> all=directoryDao.all(Sort.newInstance().asc("id"));
		for(Directory tmp:all){
			List<Document> docs=new ArrayList<Document>();
			for(int i=0;i<10;i++){
				Document doc=new Document();
				doc.setName("biji"+i+".txt");
				doc.setSuffix("txt");
				doc.setUrl(tmp.getUrl()+"/"+doc.getName());
				doc.setDirectory(tmp);
				docs.add(doc);
			}
			tmp.setDocuments(docs);
			directoryDao.update(tmp);
		}
		setComplete();
	}
	
	public void testRename(){
		directoryService.rename("艺术札记", 24l);
		setComplete();
	}

	public IDirectoryService getDirectoryService() {
		return directoryService;
	}

	public void setDirectoryService(IDirectoryService directoryService) {
		this.directoryService = directoryService;
	}	
	
	public HibernateDao<Directory, Long> getDirectoryDao() {
		return directoryDao;
	}

	public void setDirectoryDao(HibernateDao<Directory, Long> directoryDao) {
		this.directoryDao = directoryDao;
	}
}
