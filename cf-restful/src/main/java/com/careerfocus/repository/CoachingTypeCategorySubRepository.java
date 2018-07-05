package com.careerfocus.repository;

import com.careerfocus.entity.CoachingTypeCategorySub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoachingTypeCategorySubRepository extends JpaRepository<CoachingTypeCategorySub, Integer> {

	public List<CoachingTypeCategorySub> findByIsDeleteOrderByCoachingTypeCategorySubIdAsc(boolean isDelete);

	public List<CoachingTypeCategorySub> findAllByOrderByIsCurrentDescOrderAsc();

	public List<CoachingTypeCategorySub> findAllByOrderByYearDescNameAscOrderAsc();

	public List<CoachingTypeCategorySub> findByCoachingTypeCategoryIdOrderByCoachingTypeCategoryIdDesc(int coachingTypeCategoryId);

}
