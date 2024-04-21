package com.stockportfolia.jwtservice.repository;

import com.stockportfolia.jwtservice.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserInfo, Long> {
    public UserInfo findByUsername(String username);
}
