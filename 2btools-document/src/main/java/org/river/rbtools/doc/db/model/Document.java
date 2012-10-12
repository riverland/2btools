package org.river.rbtools.doc.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.river.rbtools.db.model.BaseEntity;

@Entity
@Table(name="T_D_DOC")
public class Document extends BaseEntity {
	private static final long serialVersionUID = -2770519574185343706L;

	/**name of the document*/
	protected String name;
	
	/**file type F:file*/
	protected String doctype;
	
	/**suffix of the file*/
	protected String suffix;
	
	/**the persist path of the document*/
	protected String url;	
	
	/**description of the url*/
	protected String description;
	
	protected Directory directory;

	@Basic
	@Column(name="NAME",length=64)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name="DOC_TYPE",length=16)
	public String getDoctype() {
		return doctype;
	}

	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}

	@Basic
	@Column(name="SUFFIX",length=16)
	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
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
	@Column(name="DOC_DESCS",length=255)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(cascade={},fetch=FetchType.LAZY)
	@JoinColumn(name="DOC_DIR")
	public Directory getDirectory() {
		return directory;
	}

	public void setDirectory(Directory directory) {
		this.directory = directory;
	}
	
	
}
