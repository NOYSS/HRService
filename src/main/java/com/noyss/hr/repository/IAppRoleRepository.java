package com.noyss.hr.repository;

import com.noyss.hr.entity.db.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppRoleRepository extends JpaRepository<AppRole, Long> {
}
