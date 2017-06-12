package com.careerfocus.repository;

import com.careerfocus.entity.Announcements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcements, Integer> {


    public List<Announcements> findByIsCurrentOrderByOrderAsc(boolean isCurrent);

    public List<Announcements> findAllByOrderByIsCurrentDescOrderAsc();

    public List<Announcements> findByIsCurrentAndOrderGreaterThan(boolean isCurrent, int order);

}
