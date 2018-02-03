package com.careerfocus.repository;

import com.careerfocus.entity.SliderAnnouncement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SliderAnnouncementRepository extends JpaRepository<SliderAnnouncement, Integer> {
	
//	@Modifying
//	@Query("UPDATE SliderAnnouncement s SET s.isCurrent = !s.isCurrent WHERE s.SliderAnnouncementId = ?")
//	void updateSliderAnnouncementIsCurrent(int SliderAnnouncementId);

}
