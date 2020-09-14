package com.mybooks.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mybooks.app.entities.FileShareTracker;

public interface FileShareTrackerRepository extends JpaRepository<FileShareTracker, Long>{

}
