package com.noyss.hr.entity.db;

import com.noyss.hr.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@SequenceGenerator(initialValue = 1, name = "generation_id", sequenceName = "seq_app_user")
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AppUser   extends BaseEntity {

    private String name;

    private String username;

    private String password;

    private String email;

    @Column(name = "status", length = 1)
    private String status;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appUser")
    private List<AppUserRoles> appUserRoles = new ArrayList<>();

}
