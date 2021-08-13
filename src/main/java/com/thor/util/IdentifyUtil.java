package com.thor.util;

import com.thor.type.Identity;
import com.thor.type.ResponseCode;
import com.thor.type.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class IdentifyUtil {

    public final static String IDENTITY = "IDENTITY";

    /**
     * write identity in local
     * @param request
     * @param identity
     * @param interval
     */
    public static void writeIdentity(HttpServletRequest request,
                                     Identity identity,
                                    int interval) {
        HttpSession session = request.getSession();
        /**
         * override password
         */
        identity.setPassword("do you want to know?");
        session.setAttribute(IDENTITY,identity);
        session.setMaxInactiveInterval(interval);
    }

    /**
     * read local identity
     * @param request
     * @return
     */
    public static Identity readIdentity(HttpServletRequest request) {
        return (Identity) request.getSession().getAttribute(IDENTITY);
    }

    public static void clearIdentity(HttpServletRequest request) {
        request.getSession().setAttribute(IDENTITY,null);
    }

    /**
     * verify user's role
     * @param request
     * @param role
     * @return true if the user's role same with the specified role
     */
    public static boolean verifyRole(HttpServletRequest request,Role role) {
        Identity identity = readIdentity(request);
        if(identity != null && identity.getRole() == role) {
            return true;
        }
        return false;
    }

    /**
     * verify user's identity
     * @param target
     * @param key
     * @return
     */
    public static ResponseCode verifyIdentity(Identity target,Identity key) {
        if(target == null || key == null) {
            return ResponseCode.ERROR;
        }
        if(target.getPhone() == null || target.getPassword() == null) {
            return ResponseCode.NULL_VALUE;
        }
        if(!target.getPhone().equals(key.getPhone())) {
            return ResponseCode.PHONE_WRONG;
        }
        if(!target.getPassword().equals(key.getPassword())) {
            return ResponseCode.PASSWORD_WRONG;
        }
        return ResponseCode.OK;
    }

}
