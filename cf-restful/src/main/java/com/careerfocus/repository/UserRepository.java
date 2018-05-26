package com.careerfocus.repository;

import com.careerfocus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsernameAndPassword(String username, String password);

	User findByUsername(String username);

	User findByUsernameAndPasswordAndStatus(String username, String password, String status);

}
