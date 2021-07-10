package com.noyss.hr.repository.custom;

public interface IOuRepositoryCustom extends IBaseRepository{
    Object findByCode(String code);
}
