package com.moda.api_java_sts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moda.api_java_sts.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
