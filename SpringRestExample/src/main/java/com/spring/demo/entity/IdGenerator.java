package com.spring.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ID_GENERATOR")
public class IdGenerator implements Serializable {

	 
	private static final long serialVersionUID = 1L;
	
	@Id
	Integer id;

	@Column(name="num")
	Integer num;
	
	@Column(name = "version")
	@Version
	private Long version;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return num;
	}

	public void setNumber(Integer num) {
		this.num = num;
	}

	public IdGenerator() {
		 
	}
	
	
	public IdGenerator(Integer id) {
		super();
		this.id = id;
	}
	

}
