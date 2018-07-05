package com.careerfocus.repository;

import com.careerfocus.entity.CoachingTypeCategorySubUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoachingTypeCategorySubUnitRepository extends JpaRepository<CoachingTypeCategorySubUnit, Integer> {

	public List<CoachingTypeCategorySubUnit> findByIsDeleteOrderByCoachingTypeCategorySubUnitIdAsc(boolean isDelete);

	public List<CoachingTypeCategorySubUnit> findAllByOrderByIsCurrentDescOrderAsc();

	public List<CoachingTypeCategorySubUnit> findAllByOrderByYearDescNameAscOrderAsc();

	public List<CoachingTypeCategorySubUnit> findByCoachingTypeCategorySubIdOrderByCoachingTypeCategorySubIdDesc(
			int coachingTypeCategorySubId);

}
