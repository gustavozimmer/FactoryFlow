package com.zimmer.FactoryFlow.repository;

import com.zimmer.FactoryFlow.entity.PermissionRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRoleRepository extends JpaRepository<PermissionRoleEntity, Long> {

    List<PermissionRoleEntity> findByRoleId(Long id);

}
