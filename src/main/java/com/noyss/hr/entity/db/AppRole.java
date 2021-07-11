package com.noyss.hr.entity.db;

import com.noyss.hr.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@SequenceGenerator(initialValue = 1, name = "generation_id", sequenceName = "seq_app_role")
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AppRole extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    private Integer seq;

    @Column(name = "status", length = 1)
    private String status;
}
