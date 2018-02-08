package com.careerfocus.repository;

import com.careerfocus.entity.UploadFile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadRepository extends JpaRepository<UploadFile, Integer> {

	@Query(value = "select fu from UploadFile fu where fu.isCurrent = 1 and fu.coachingType = :key"
			+ " ORDER BY fu.id DESC", nativeQuery = false)
	public List<UploadFile> findAllByIsCurrentOrderByIdAsc(@Param("key") int key);

}
