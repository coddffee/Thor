package com.thor.service;

import com.thor.dao.*;
import com.thor.entity.Inspector;
import com.thor.entity.InspectorRegistry;
import com.thor.entity.Manager;
import com.thor.entity.ManagerRegistry;
import com.thor.type.Identity;
import com.thor.type.ResponseCode;
import com.thor.type.Role;
import com.thor.util.IdentifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;

@Service
public class SignUpAndSignInService {

    private ManagersRegistryDao managersRegistryDao;
    private ManagersDao managersDao;
    private InspectorsRegistryDao inspectorsRegistryDao;
    private InspectorsDao inspectorsDao;
    private ManagersPostDictionaryDao managersPostDictionaryDao;

    @Autowired
    public void setManagersRegistryDao(ManagersRegistryDao managersRegistryDao) {
        this.managersRegistryDao = managersRegistryDao;
    }
    @Autowired
    public void setManagersDao(ManagersDao managersDao) {
        this.managersDao = managersDao;
    }
    @Autowired
    public void setInspectorsRegistryDao(InspectorsRegistryDao inspectorsRegistryDao) {
        this.inspectorsRegistryDao = inspectorsRegistryDao;
    }
    @Autowired
    public void setInspectorsDao(InspectorsDao inspectorsDao) {
        this.inspectorsDao = inspectorsDao;
    }
    @Autowired
    public void setManagersPostDictionaryDao(ManagersPostDictionaryDao managersPostDictionaryDao) {
        this.managersPostDictionaryDao = managersPostDictionaryDao;
    }

    @Transactional(timeout = 5,rollbackFor = Exception.class)
    public ResponseCode managerSignUpService(HttpServletRequest request,
                                             ManagerRegistry registry) {
        if(registry == null) {
            return ResponseCode.ERROR;
        }
        if(managersDao.selectByPhone(registry.getPhone()) != null) {
            return ResponseCode.DUPLICATE_WRITE;
        }
        if(managersRegistryDao.insertOne(registry)) {
            Identity identity = new Identity(registry.getName(),
                    registry.getPhone(),"",Role.NONE);
            IdentifyUtil.writeIdentity(request,identity,0);
            return ResponseCode.OK;
        }
        return ResponseCode.ERROR;
    }

    @Transactional(timeout = 5,readOnly = true)
    public ManagerRegistry managerQueryRegistryService(HttpServletRequest request) {
        Identity identity = IdentifyUtil.readIdentity(request);
        if(identity == null) {
            return null;
        }
        return managersRegistryDao.selectByPhone(identity.getPhone());
    }

    @Transactional(timeout = 5,readOnly = true)
    public ResponseCode managerSignInService(HttpServletRequest request,
                                             Identity identity) {
        if(identity == null) {
            return ResponseCode.ERROR;
        }
        Manager manager = managersDao.selectByPhone(identity.getPhone());
        if(manager == null) {
            return ResponseCode.PHONE_WRONG;
        }
        if(!identity.getPassword().equals(manager.getPassword())) {
            return ResponseCode.PASSWORD_WRONG;
        }
        Role managerRole = managersPostDictionaryDao.selectRoleById(manager.getPostId());
        identity.setRole(managerRole);
        identity.setPassword("");
        IdentifyUtil.writeIdentity(request,identity,0);
        return ResponseCode.OK;
    }

    @Transactional(timeout = 5,rollbackFor = Exception.class)
    public ResponseCode inspectorSignUpService(HttpServletRequest request,
                                               InspectorRegistry registry) {
        if(registry == null) {
            return ResponseCode.ERROR;
        }
        if(inspectorsDao.selectByPhone(registry.getPhone()) != null) {
            return ResponseCode.DUPLICATE_WRITE;
        }
        if(inspectorsRegistryDao.insertOne(registry)) {
            Identity identity = new Identity(registry.getName(),
                    registry.getPhone(),registry.getPassword(),Role.NONE);
            IdentifyUtil.writeIdentity(request,identity,0);
            return ResponseCode.OK;
        }
        return ResponseCode.ERROR;
    }

    @Transactional(timeout = 5,readOnly = true)
    public InspectorRegistry inspectorQueryRegistry(HttpServletRequest request) {
        Identity identity = IdentifyUtil.readIdentity(request);
        if(identity == null) {
            return null;
        }
        return inspectorsRegistryDao.selectByPhone(identity.getPhone());
    }

    @Transactional(timeout = 5,readOnly = true)
    public ResponseCode inspectorSignInService(HttpServletRequest request,
                                               Identity identity) {
        if(identity == null) {
            return ResponseCode.ERROR;
        }
        Inspector inspector = inspectorsDao.selectByPhone(identity.getPhone());
        if(inspector == null) {
            return ResponseCode.PHONE_WRONG;
        }
        if(!identity.getPassword().equals(inspector.getPassword())) {
            return ResponseCode.PASSWORD_WRONG;
        }
        identity.setRole(Role.INSPECTOR);
        identity.setPassword("");
        IdentifyUtil.writeIdentity(request,identity,0);
        return ResponseCode.OK;
    }

    public void signOutService(HttpServletRequest request) {
        IdentifyUtil.clearIdentity(request);
    }

}
