package com.mybooks.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mybooks.app.enums.Permission;

@Entity
@Table
public class FileShareTracker {

	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private Book book;
	
	@OneToOne
	private User sharedWith;
	
	@Column
	private Permission perm;
}
