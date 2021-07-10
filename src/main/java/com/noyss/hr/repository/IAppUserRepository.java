package com.noyss.hr.repository;

import com.noyss.hr.entity.db.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppUserRepository extends JpaRepository<AppUser, Long> {
}
