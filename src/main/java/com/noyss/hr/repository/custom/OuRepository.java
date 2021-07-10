package com.noyss.hr.repository.custom;

import com.noyss.hr.entity.db.Ou;
import com.noyss.hr.repository.IOuRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class OuRepository implements IOuRepositoryCustom {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IOuRepository iOuRepository;

    @Override
    @Transactional
    public Object save(String dataJson) {
        try{
            JSONObject jsonObject = new JSONObject(dataJson);
            Long id = jsonObject.optLong("id",0);
            Ou ou = null;
            if(id <= 0){
                ou = new Ou();
            }else{
                ou = iOuRepository.getById(id);
            }
            ou.setName(jsonObject.optString("name", ""));
            ou.setCode(jsonObject.optString("code", ""));
            ou.setStatus(jsonObject.optString("status", ""));
            iOuRepository.saveAndFlush(ou);
            return ou;
        }catch (Exception ex){
            LOGGER.error("ERROR : {}",ex.getMessage(),ex);
            return null;
        }
    }

    @Override
    @Transactional
    public Object deleteById(Long id) {
        try{
            iOuRepository.deleteById(id);
            return "DELETE";
        }catch (Exception ex){
            LOGGER.error("ERROR : {}",ex.getMessage(),ex);
            return "ERROR";
        }
    }

    @Override
    public Object findById(Long id) {
        try{
            return iOuRepository.getOne(id);
        }catch (Exception ex){
            LOGGER.error("ERROR : {}",ex.getMessage(),ex);
            return null;
        }
    }

    @Override
    public Object findAll() {
        try{
            return iOuRepository.findAll();
        }catch (Exception ex){
            LOGGER.error("ERROR : {}",ex.getMessage(),ex);
            return null;
        }
    }

    @Override
    public Object findByCode(String code) {
        try{
            Session session = (Session) entityManager.getDelegate();
            StringBuilder queryString = new StringBuilder();
            queryString.append(StatementSQL.OU);
            queryString.append("\n where code = '" + code + "'");
            SQLQuery query = session.createSQLQuery(queryString.toString());
            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            return query.list();
        }catch (Exception ex){
            LOGGER.error("ERROR : {}",ex.getMessage(),ex);
            return null;
        }
    }
}
