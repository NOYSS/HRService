package com.noyss.hr.repository.custom;

public interface IBaseRepository {
    Object save (String dataJson);
    Object deleteById (Long id);
    Object findById (Long id);
    Object findAll ();
}
