package com.thor.service;

import com.thor.dao.ManagersDao;
import com.thor.entity.Manager;
import com.thor.type.Gender;
import com.thor.type.Identity;
import com.thor.type.ResponseCode;
import com.thor.util.IdentifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;

@Service
public class EquipmentManagerService {

    private ManagersDao managersDao;

    @Autowired
    public void setManagersDao(ManagersDao managersDao) {
        this.managersDao = managersDao;
    }

    @Transactional(timeout = 5,readOnly = true)
    public Manager queryMyInformationService(HttpServletRequest request) {
        Identity identity = IdentifyUtil.readIdentity(request);
        return managersDao.selectByPhone(identity.getPhone());
    }

    @Transactional(timeout = 5,rollbackFor = Exception.class)
    public ResponseCode updateNameService(HttpServletRequest request,
                                          Integer id, String name) {
        if(id == null || name == null || name.equals("")) {
            return ResponseCode.ERROR;
        }
        if(!managersDao.updatePropertyById(id,"name",name)) {
            return ResponseCode.ERROR;
        }
        Identity identity = IdentifyUtil.readIdentity(request);
        identity.setName(name);
        IdentifyUtil.writeIdentity(request,identity,0);
        return ResponseCode.OK;
    }

    @Transactional(timeout = 5,rollbackFor = Exception.class)
    public ResponseCode updatePhoneService(HttpServletRequest request,
                                           Integer id,String phone) {
        if(id == null || phone == null || phone.equals("")) {
            return ResponseCode.ERROR;
        }
        if(managersDao.selectByPhone(phone) != null) {
            return ResponseCode.DUPLICATE_WRITE;
        }
        if(!managersDao.updatePropertyById(id,"phone",phone)) {
            return ResponseCode.ERROR;
        }
        Identity identity = IdentifyUtil.readIdentity(request);
        identity.setPhone(phone);
        IdentifyUtil.writeIdentity(request,identity,0);
        return ResponseCode.OK;
    }

    @Transactional(timeout = 5,rollbackFor = Exception.class)
    public ResponseCode updatePasswordService(HttpServletRequest request,
                                              Integer id,String password) {
        if(id == null || password == null || password.equals("")) {
            return ResponseCode.ERROR;
        }
        if(!managersDao.updatePropertyById(id,"password",password)) {
            return ResponseCode.ERROR;
        }
        return ResponseCode.OK;
    }

    @Transactional(timeout = 5,rollbackFor = Exception.class)
    public ResponseCode updateGenderService(HttpServletRequest request,
                                            Integer id,
                                            Gender gender) {
        if(id == null || gender == null) {
            return ResponseCode.ERROR;
        }
        if(!managersDao.updatePropertyById(id,"gender",gender)) {
            return ResponseCode.ERROR;
        }
        return ResponseCode.OK;
    }

    @Transactional(timeout = 5,rollbackFor = Exception.class)
    public ResponseCode updateAgeService(HttpServletRequest request,
                                         Integer id,
                                         Integer age) {
        if(id == null || age == null) {
            return ResponseCode.ERROR;
        }
        if(!managersDao.updatePropertyById(id,"age",age)) {
            return ResponseCode.ERROR;
        }
        return ResponseCode.OK;
    }

}
