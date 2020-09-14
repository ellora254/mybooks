package com.mybooks.app.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mybooks.app.enums.VerificationStatus;

@Entity
@Table
public class Verification {

	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
	@OneToOne
	private User requestor;
	
	@OneToOne
	private User verifier;
	
	@Column
	@Enumerated(EnumType.STRING)
	private VerificationStatus status;
	
	@Column
	private Boolean isCleanupDone;
	
	@OneToOne
	private Book beforeVerificationBook;
	
	@OneToOne
	private Book afterVerificationBook;
	
	@OneToMany
	private List<Transaction> transactions;
}
