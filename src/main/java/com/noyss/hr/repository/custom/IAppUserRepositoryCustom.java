package com.noyss.hr.repository.custom;

public interface IAppUserRepositoryCustom extends IBaseRepository{
    Object findByUsername(String username);
}
