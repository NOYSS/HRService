package com.noyss.hr.repository;

import com.noyss.hr.entity.db.AppUserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppUserRolesRepository extends JpaRepository<AppUserRoles, Long> {
}
