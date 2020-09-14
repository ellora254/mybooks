package com.mybooks.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybooks.app.entities.Verification;

public interface VerificationRepository extends JpaRepository<Verification, Long>{

}
