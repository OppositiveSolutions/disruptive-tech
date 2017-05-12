package com.careerfocus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.careerfocus.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
