package com.careerfocus.repository;

import com.careerfocus.entity.UploadFile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepository extends JpaRepository<UploadFile, Integer> {

}
