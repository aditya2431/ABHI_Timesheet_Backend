package com.example.timesheet.repository;

import com.example.timesheet.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
	List<User> findByUserNameAndPassword(String userName, String password);
	User findByUserName(String userName);
	List<User> findByabhiManager(String abhiManager);
	List<User> findByPartnerManager(String partnerManager);
	
}
