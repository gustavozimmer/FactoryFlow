package com.zimmer.FactoryFlow.repository;

import com.zimmer.FactoryFlow.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
