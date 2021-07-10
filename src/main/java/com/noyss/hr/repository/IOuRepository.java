package com.noyss.hr.repository;

import com.noyss.hr.entity.db.Ou;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOuRepository extends JpaRepository<Ou, Long> {
}
