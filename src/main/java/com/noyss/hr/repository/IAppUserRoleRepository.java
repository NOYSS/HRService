package com.noyss.hr.repository;

import com.noyss.hr.entity.db.AppUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppUserRoleRepository extends JpaRepository<AppUserRole, Long> {
}
