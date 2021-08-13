package com.thor.service;

import com.thor.dao.ManagersDao;
import com.thor.dao.ManagersRegistryDao;
import com.thor.entity.Manager;
import com.thor.entity.ManagerRegistry;
import com.thor.type.*;
import com.thor.util.EntityUtil;
import com.thor.util.IdentifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.util.List;

@Service
public class SystemManagerService {

    private String name;
    private String phone;
    private String password;

    private Integer managersRegistryPageSize;
    private PageData managersRegistryPageData;

    private Integer managersPageSize;
    private PageData managersPageData;

    private ManagersRegistryDao managersRegistryDao;
    private ManagersDao managersDao;

    @Value("${thor.system-manager.name}")
    public void setName(String name) {
        this.name = name;
    }

    @Value("${thor.system-manager.phone}")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Value("${thor.system-manager.password}")
    public void setPassword(String password) {
        this.password = password;
    }

    @Value("${thor.page-size.managers-registry-page-size}")
    public void setManagersRegistryPageSize(Integer managersRegistryPageSize) {
        this.managersRegistryPageSize = managersRegistryPageSize;
    }

    @Value("${thor.page-size.managers-page-size}")
    public void setManagersPageSize(Integer managersPageSize) {
        this.managersPageSize = managersPageSize;
    }

    @Autowired
    public void setManagersRegistryDao(ManagersRegistryDao managersRegistryDao) {
        this.managersRegistryDao = managersRegistryDao;
    }
    @Autowired
    public void setManagersDao(ManagersDao managersDao) {
        this.managersDao = managersDao;
    }

    public ResponseCode signInService(Identity identity) {
        Identity key = new Identity(name,phone,password,null);
        ResponseCode code = IdentifyUtil.verifyIdentity(identity,key);
        if(code == ResponseCode.OK) {
            identity.setRole(Role.SYSTEM_MANAGER);
        }
        return code;
    }

    @Transactional(timeout = 5,readOnly = true)
    public PageData queryManagersRegistryPageDataService() {
        Integer count = managersRegistryDao.selectCount();
        Integer pages = count/ managersRegistryPageSize;
        if(count% managersRegistryPageSize != 0) {
            pages++;
        }
        return (managersRegistryPageData = new PageData(managersRegistryPageSize,count,pages));
    }

    @Transactional(timeout = 5,readOnly = true)
    public List<ManagerRegistry> queryManagersRegistryService(Integer page) {
        if(managersRegistryPageData == null) {
            queryManagersRegistryPageDataService();
        }
        if(page < 1) {
            page = 1;
        } else if(page > managersRegistryPageData.getPages()) {
            page = managersRegistryPageData.getPages();
        }
        return managersRegistryDao.selectByPage(managersRegistryPageSize *(page-1), managersRegistryPageSize);
    }

    @Transactional(timeout = 5,rollbackFor = Exception.class)
    public ResponseCode acceptManagerService(Integer id) {
        ManagerRegistry registry = managersRegistryDao.selectById(id);
        if(registry == null) {
            return ResponseCode.ERROR;
        }
        if(registry.getStatus() == VerifyStatus.ACCEPTED) {
            return ResponseCode.DUPLICATE_WRITE;
        }
        if(!managersRegistryDao.updateStatusById(id,VerifyStatus.ACCEPTED)) {
            return ResponseCode.ERROR;
        }
        registry.setStatus(VerifyStatus.ACCEPTED);
        Date date = new Date(System.currentTimeMillis());
        registry.setDate(date);
        Manager manager = EntityUtil.managerRegistryToManager(registry);
        if(!managersDao.insertOne(manager)){
            return ResponseCode.ERROR;
        }
        return ResponseCode.OK;
    }

    @Transactional(timeout = 5,readOnly = true)
    public PageData queryManagersPageDataService() {
        Integer count = managersDao.selectCount();
        Integer pages = count/managersPageSize;
        if(count% managersPageSize != 0) {
            pages++;
        }
        return (managersPageData = new PageData(managersPageSize,count,pages));
    }

    @Transactional(timeout = 5,readOnly = true)
    public List<Manager> queryManagersService(Integer page) {
        if(managersPageData == null) {
            queryManagersPageDataService();
        }
        if(page < 1) {
            page = 1;
        } else if(page > managersPageData.getPages()) {
            page = managersPageData.getPages();
        }
        return managersDao.selectByPage(managersPageSize*(page-1),managersPageSize);
    }

}
