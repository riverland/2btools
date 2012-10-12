package org.river.rbtools.db.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.river.base.entity.Entity;

@MappedSuperclass
public abstract class BaseEntity extends Entity implements Serializable{
	private static final long serialVersionUID = 6916279535008829135L;

	/**logic primary key*/
	protected Long id;
	
	/**createTime*/
	protected Date createTime=new Date();
	
	/**updateTime*/
	protected Date updateTime;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@Basic
	@Column(name="CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	@Basic
	@Column(name="UPDATE_TIME")
	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	@SuppressWarnings("rawtypes")
	@Transient
	@Override
	public Class getType() {
		return this.getClass();
	}

}
