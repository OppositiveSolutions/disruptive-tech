package com.careerfocus.repository;

import com.careerfocus.entity.Center;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CenterRepository extends JpaRepository<Center, Integer> {

    public Center findByCenterCode(String centerCode);

}
