package com.noyss.hr.entity.db;

import com.noyss.hr.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@SequenceGenerator(initialValue = 1, name = "generation_id", sequenceName = "seq_app_user_roles")
@EqualsAndHashCode(callSuper = false)
public class AppUserRoles extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appUser")
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appRole")
    private AppRole appRole;
}
