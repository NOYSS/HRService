package com.noyss.hr.entity;

import com.noyss.hr.util.AppUtil;
import com.noyss.hr.util.DateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

@Component
public class EntityListener {

    @PrePersist
    public void prePersistFunction(Object object) {
        String user = null;
        Date currentDate = DateUtil.getCurrentDate();
        try {
            user = "System";
        } catch (Exception e) {

        }
        try {
            this.assignValueToCommonFields(object, user, currentDate, "CREATE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreUpdate
    public void preUpdateFunction(Object object) {
        String user = null;
        Date currentDate = DateUtil.getCurrentDate();
        try {
            user = "System";
        } catch (Exception e) {

        }
        try {
            this.assignValueToCommonFields(object, user, currentDate, "UPDATE");
        } catch (Exception e) {

        }
    }

    private void assignValueToCommonFields(Object arg, String user, Date currentDate, String mode) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        BeanUtils.setProperty(arg, "updatedBy", user);
        BeanUtils.setProperty(arg, "updatedDate", currentDate);
        if (mode.equals("CREATE")) {
            BeanUtils.setProperty(arg, "createdBy", user);
            BeanUtils.setProperty(arg, "createdDate", currentDate);
        }
        Class cls = arg.getClass();
        Object target = arg;
        for (Field field : cls.getDeclaredFields()) {
            Field strField = ReflectionUtils.findField(cls, field.getName());
            if (strField != null) {
                if (strField.getType().equals(String.class)) {
                    strField.setAccessible(true);
                    Object value = ReflectionUtils.getField(strField, target);
                    if (value != null && AppUtil.isEmpty(value.toString())) {
                        ReflectionUtils.makeAccessible(strField); //set null when emptyString
                        ReflectionUtils.setField(strField, target, null);
                    }
                }
            }
        }
    }
}
