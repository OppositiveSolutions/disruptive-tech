package com.careerfocus.repository;

import com.careerfocus.entity.Achievers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchieversRepository extends JpaRepository<Achievers, Integer> {


    public List<Achievers> findByIsCurrentOrderByOrderAsc(boolean isCurrent);

    public List<Achievers> findAllByOrderByIsCurrentDescOrderAsc();
    
    public List<Achievers> findAllByOrderByYearDescOrderAsc();

    public List<Achievers> findByIsCurrentAndOrderGreaterThan(boolean isCurrent, int order);

}
