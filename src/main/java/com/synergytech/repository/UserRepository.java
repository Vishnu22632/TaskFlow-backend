package com.synergytech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergytech.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
