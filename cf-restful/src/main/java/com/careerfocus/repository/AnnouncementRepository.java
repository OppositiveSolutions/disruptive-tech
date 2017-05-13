package com.careerfocus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerfocus.entity.Announcements;

public interface AnnouncementRepository extends JpaRepository<Announcements, Integer> {

	
	public List<Announcements> findByIsCurrentOrderByOrderAsc(boolean isCurrent);
	
	public List<Announcements> findAllByOrderByIsCurrentDescOrderAsc();
	
	public List<Announcements> findByIsCurrentAndOrderGreaterThan(boolean isCurrent, int order);
	
}
