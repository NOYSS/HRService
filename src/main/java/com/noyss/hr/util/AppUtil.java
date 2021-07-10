package com.noyss.hr.util;

import java.util.List;

public class AppUtil {

    public static boolean isNotEmpty(List checkList) {
        Boolean returnValue = false;
        if (checkList != null && !checkList.isEmpty() && checkList.size() > 0) {
            returnValue = true;
        }
        return returnValue;
    }

    public static boolean isNotEmpty(String checkString) {
        boolean returnValue = false;
        if (checkString != null && checkString.length() > 0) {
            returnValue = true;
        }
        return returnValue;
    }

    public static boolean isEmpty(String checkString) {
        boolean returnValue = true;
        if (checkString != null && checkString.length() > 0) {
            returnValue = false;
        }
        return returnValue;
    }

    public static boolean isNotNull(Object object) {
        boolean returnValue = false;
        if (object != null) {
            returnValue = true;
        }
        return returnValue;
    }

    public static boolean isNull(Object object) {
        boolean returnValue = false;
        if (object == null) {
            returnValue = true;
        }
        return returnValue;
    }

}
