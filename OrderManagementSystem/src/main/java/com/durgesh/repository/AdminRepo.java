package com.durgesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.durgesh.model.Admin;

public interface AdminRepo extends JpaRepository<Admin,Long> {

	Admin findByUserName(String username);

}
