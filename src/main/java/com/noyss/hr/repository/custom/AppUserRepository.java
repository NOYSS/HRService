package com.noyss.hr.repository.custom;

import com.noyss.hr.entity.db.AppUser;
import com.noyss.hr.repository.IAppUserRepository;
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
public class AppUserRepository implements IAppUserRepositoryCustom {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IAppUserRepository iAppUserRepository;

    @Override
    @Transactional
    public Object save(String dataJson) {
        try{
            JSONObject jsonObject = new JSONObject(dataJson);
            Long id = jsonObject.optLong("id",0);
            AppUser appUser = null;
            if(id <= 0){
                appUser = new AppUser();
            }else{
                appUser = iAppUserRepository.getById(id);
            }
            appUser.setUsername(jsonObject.optString("username", ""));
            appUser.setPassword(jsonObject.optString("password", ""));
            appUser.setStatus(jsonObject.optString("status", ""));
            iAppUserRepository.saveAndFlush(appUser);
            return appUser;
        }catch (Exception ex){
            LOGGER.error("ERROR : {}",ex.getMessage(),ex);
            return null;
        }
    }

    @Override
    @Transactional
    public Object deleteById(Long id) {
        try{
            iAppUserRepository.deleteById(id);
            return "DELETE";
        }catch (Exception ex){
            LOGGER.error("ERROR : {}",ex.getMessage(),ex);
            return "ERROR";
        }
    }

    @Override
    public Object findById(Long id) {
        try{
            return iAppUserRepository.getOne(id);
        }catch (Exception ex){
            LOGGER.error("ERROR : {}",ex.getMessage(),ex);
            return null;
        }
    }

    @Override
    public Object findAll() {
        try{
            return iAppUserRepository.findAll();
        }catch (Exception ex){
            LOGGER.error("ERROR : {}",ex.getMessage(),ex);
            return null;
        }
    }

    @Override
    public Object findByUsername(String username) {
        try{
            Session session = (Session) entityManager.getDelegate();
            StringBuilder queryString = new StringBuilder();
            queryString.append(StatementSQL.APP_USER);
            queryString.append("\n where username = '" + username + "'");
            SQLQuery query = session.createSQLQuery(queryString.toString());
            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            return query.list();
        }catch (Exception ex){
            LOGGER.error("ERROR : {}",ex.getMessage(),ex);
            return null;
        }
    }
}
