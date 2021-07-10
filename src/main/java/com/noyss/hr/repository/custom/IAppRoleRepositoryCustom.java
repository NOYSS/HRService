package com.noyss.hr.repository.custom;

public interface IAppRoleRepositoryCustom extends IBaseRepository{
    Object findByCode(String code);
}
