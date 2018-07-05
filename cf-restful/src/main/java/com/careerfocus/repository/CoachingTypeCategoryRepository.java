package com.careerfocus.repository;

import com.careerfocus.entity.CoachingTypeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoachingTypeCategoryRepository extends JpaRepository<CoachingTypeCategory, Integer> {

	public List<CoachingTypeCategory> findByIsDeleteOrderByCoachingTypeCategoryIdAsc(boolean isDelete);

	public List<CoachingTypeCategory> findAllByOrderByIsCurrentDescOrderAsc();

	public List<CoachingTypeCategory> findByCoachingTypeIdOrderByCoachingTypeIdDesc(int coachingTypeId);

	public List<CoachingTypeCategory> findAllByOrderByYearDescNameAscOrderAsc();


}
