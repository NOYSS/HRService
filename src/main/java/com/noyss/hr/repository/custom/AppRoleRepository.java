package com.noyss.hr.repository.custom;

import com.noyss.hr.entity.db.AppRole;
import com.noyss.hr.repository.IAppRoleRepository;
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
public class AppRoleRepository implements  IAppRoleRepositoryCustom{

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IAppRoleRepository iAppRoleRepository;

    @Override
    @Transactional
    public Object save(String dataJson) {
        try{
            JSONObject jsonObject = new JSONObject(dataJson);
            Long id = jsonObject.optLong("id",0);
            AppRole appRole = null;
            if(id <= 0){
                appRole = new AppRole();
            }else{
                appRole = iAppRoleRepository.getById(id);
            }
            appRole.setCode(jsonObject.optString("code", ""));
            appRole.setName(jsonObject.optString("name", ""));
            appRole.setStatus(jsonObject.optString("status", ""));
            appRole.setSeq(jsonObject.optInt("seq", 0));
            iAppRoleRepository.saveAndFlush(appRole);
            return appRole;
        }catch (Exception ex){
            LOGGER.error("ERROR : {}",ex.getMessage(),ex);
            return null;
        }
    }

    @Override
    @Transactional
    public Object deleteById(Long id) {
        try{
            iAppRoleRepository.deleteById(id);
            return "DELETE";
        }catch (Exception ex){
            LOGGER.error("ERROR : {}",ex.getMessage(),ex);
            return "ERROR";
        }
    }

    @Override
    public Object findById(Long id) {
        try{
            return iAppRoleRepository.getOne(id);
        }catch (Exception ex){
            LOGGER.error("ERROR : {}",ex.getMessage(),ex);
            return null;
        }
    }

    @Override
    public Object findAll() {
        try{
            return iAppRoleRepository.findAll();
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
            queryString.append(StatementSQL.APP_ROLE);
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
