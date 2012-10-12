package org.river.rbtools.doc.db.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.river.rbtools.db.model.BaseEntity;

@Entity
@Table(name="T_D_DIRECTORY")
public class Directory extends BaseEntity {
	private static final long serialVersionUID = -5019635639595057201L;
	
	/**the name of the directory object*/
	protected String name;
	
	/**the url of the directory*/
	protected String url;
	
	/**description*/
	protected String description;
	
	/**parent directory*/
	protected Directory parentDir;

	/**sub-directories*/
	protected List<Directory> children;
	
	/**the document contained in the directory*/
	protected List<Document> documents;

	@Basic
	@Column(name="NAME",length=64)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name="URL",length=128,unique=true)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Basic
	@Column(name="DIR_DESCS",length=255)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@ManyToOne(cascade={},fetch=FetchType.LAZY)
	@JoinColumn(name="PID")
	public Directory getParentDir() {
		return parentDir;
	}

	public void setParentDir(Directory parentDir) {
		this.parentDir = parentDir;
	}
	
	@OneToMany(cascade=CascadeType.ALL,targetEntity=Directory.class,fetch=FetchType.LAZY,mappedBy="parentDir")
	public List<Directory> getChildren() {
		return children;
	}

	public void setChildren(List<Directory> children) {
		this.children = children;
	}

	@OneToMany(cascade=CascadeType.ALL,targetEntity=Document.class,fetch=FetchType.LAZY,mappedBy="directory")
	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
}
