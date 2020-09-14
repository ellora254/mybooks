package com.mybooks.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Book {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@Column
	private String fileName;

	@Column
	private String fileType;

	@Column
	private String filePath;

	@Column
	private Double fizeSize;

	@ManyToOne
	private User owner;
	
	
}
