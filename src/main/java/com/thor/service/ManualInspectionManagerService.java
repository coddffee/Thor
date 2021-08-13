package com.thor.service;

import com.thor.dao.*;
import com.thor.entity.*;
import com.thor.type.*;
import com.thor.util.EntityUtil;
import com.thor.util.IdentifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@Service
public class ManualInspectionManagerService {

    private ManagersDao managersDao;
    private InspectorsRegistryDao inspectorsRegistryDao;
    private InspectorsDao inspectorsDao;
    private TasksDao tasksDao;
    private ManualInspectionRecordsDao manualInspectionRecordsDao;

    private Integer inspectorsRegistryPageSize;
    private PageData inspectorsRegistryPageData;

    private Integer inspectorsPageSize;
    private PageData inspectorsPageData;

    private Integer tasksPageSize;
    private PageData tasksPageData;

    private Integer inspectionRecordsPageSize;
    private PageData inspectionRecordsPageData;

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
    public void setTasksDao(TasksDao tasksDao) {
        this.tasksDao = tasksDao;
    }
    @Autowired
    public void setManualInspectionRecordsDao(ManualInspectionRecordsDao manualInspectionRecordsDao) {
        this.manualInspectionRecordsDao = manualInspectionRecordsDao;
    }

    @Value("${thor.page-size.inspectors-registry-page-size}")
    public void setInspectorsRegistryPageSize(Integer inspectorsRegistryPageSize) {
        this.inspectorsRegistryPageSize = inspectorsRegistryPageSize;
    }
    @Value("${thor.page-size.inspectors-page-size}")
    public void setInspectorsPageSize(Integer inspectorsPageSize) {
        this.inspectorsPageSize = inspectorsPageSize;
    }
    @Value("${thor.page-size.managers-tasks-page-size}")
    public void setTasksPageSize(Integer tasksPageSize) {
        this.tasksPageSize = tasksPageSize;
    }
    @Value("${thor.page-size.manager-manual-inspection-records-page-size}")
    public void setInspectionRecordsPageSize(Integer inspectionRecordsPageSize) {
        this.inspectionRecordsPageSize = inspectionRecordsPageSize;
    }

    @Transactional(timeout = 5,readOnly = true)
    public Manager queryMyInformationService(HttpServletRequest request) {
        Identity identity = IdentifyUtil.readIdentity(request);
        return managersDao.selectByPhone(identity.getPhone());
    }

    @Transactional(timeout = 5,rollbackFor = Exception.class)
    public ResponseCode updateNameService(HttpServletRequest request,
                                                   Integer id,String name) {
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

    @Transactional(timeout = 5,readOnly = true)
    public PageData queryInspectorsRegistryPageDataService(HttpServletRequest request) {
        Identity identity = IdentifyUtil.readIdentity(request);
        Integer managerId = managersDao.selectIdByPhone(identity.getPhone());
        Integer count = inspectorsRegistryDao.selectCountByManagerId(managerId);
        Integer pages = count/inspectorsRegistryPageSize;
        if(count % inspectorsRegistryPageSize != 0) {
            pages ++;
        }
        return (inspectorsRegistryPageData = new PageData(inspectorsRegistryPageSize,count,pages));
    }

    @Transactional(timeout = 5,readOnly = true)
    public List<InspectorRegistry> queryInspectorsRegistryService(HttpServletRequest request,Integer page) {
        if(inspectorsRegistryPageData == null) {
            queryInspectorsRegistryPageDataService(request);
        }
        if(page < 1) {
            page = 1;
        } else if(page > inspectorsRegistryPageData.getPages()) {
            page = inspectorsRegistryPageData.getPages();
        }
        Identity identity = IdentifyUtil.readIdentity(request);
        Integer managerId = managersDao.selectIdByPhone(identity.getPhone());
        return inspectorsRegistryDao.selectByManagerIdAndPage(managerId,inspectorsRegistryPageSize*(page-1),inspectorsRegistryPageSize);
    }


    @Transactional(timeout = 5,rollbackFor = Exception.class)
    public ResponseCode acceptInspectorService(HttpServletRequest request,Integer id) {
        InspectorRegistry registry = inspectorsRegistryDao.selectById(id);
        if(registry == null) {
            return ResponseCode.ERROR;
        }
        Identity identity = IdentifyUtil.readIdentity(request);
        Integer managerId = managersDao.selectIdByPhone(identity.getPhone());
        if(registry.getManagerId() != managerId) {
            return ResponseCode.NO_ACCESS_RIGHTS;
        }
        if(registry.getStatus() == VerifyStatus.ACCEPTED) {
            return ResponseCode.DUPLICATE_WRITE;
        }
        if(!inspectorsRegistryDao.updateStatusById(id,VerifyStatus.ACCEPTED)) {
            return ResponseCode.ERROR;
        }
        registry.setStatus(VerifyStatus.ACCEPTED);
        Date date = new Date(System.currentTimeMillis());
        registry.setDate(date);
        Inspector inspector = EntityUtil.inspectorRegistryToInspector(registry);
        if(!inspectorsDao.insertOne(inspector)) {
            return ResponseCode.ERROR;
        }
        return ResponseCode.OK;
    }

    @Transactional(timeout = 5,readOnly = true)
    public PageData queryInspectorsPageDataService(HttpServletRequest request) {
        Identity identity = IdentifyUtil.readIdentity(request);
        Integer managerId = managersDao.selectIdByPhone(identity.getPhone());
        Integer count = inspectorsDao.selectCountByManagerId(managerId);
        Integer pages = count/inspectorsPageSize;
        if(count % inspectorsPageSize != 0) {
            pages ++;
        }
        return (inspectorsPageData = new PageData(inspectorsPageSize,count,pages));
    }

    @Transactional(timeout = 5,readOnly = true)
    public List<Inspector> queryInspectorsService(HttpServletRequest request,Integer page) {
        if(inspectorsPageData == null) {
            queryInspectorsPageDataService(request);
        }
        if(page < 1) {
            page = 1;
        } else if(page > inspectorsPageData.getPages()) {
            page = inspectorsPageData.getPages();
        }
        Identity identity = IdentifyUtil.readIdentity(request);
        Integer managerId = managersDao.selectIdByPhone(identity.getPhone());
        return inspectorsDao.selectByManagerIdAndPage(managerId,inspectorsPageSize*(page-1),inspectorsPageSize);
    }

    @Transactional(timeout = 5,rollbackFor = Exception.class)
    public ResponseCode createTaskService(HttpServletRequest request,Task task) {
        if(task == null) {
            return ResponseCode.ERROR;
        }
        Identity identity = IdentifyUtil.readIdentity(request);
        Integer managerId = managersDao.selectIdByPhone(identity.getPhone());
        Inspector inspector = inspectorsDao.selectById(task.getInspectorId());
        if(inspector.getManagerId() != managerId) {
            return ResponseCode.NO_ACCESS_RIGHTS;
        }
        task.setManagerId(managerId);
        task.setDate(new Date(System.currentTimeMillis()));
        if(!tasksDao.insertOne(task)) {
            return ResponseCode.ERROR;
        }
        return ResponseCode.OK;
    }

    @Transactional(timeout = 5,rollbackFor = Exception.class)
    public ResponseCode updateTaskService(HttpServletRequest request,
                                          Integer id,
                                          Task task) {
        if(task == null) {
            return ResponseCode.ERROR;
        }
        Identity identity = IdentifyUtil.readIdentity(request);
        Integer managerId = managersDao.selectIdByPhone(identity.getPhone());
        Task testTask = tasksDao.selectById(id);
        if(testTask.getManagerId() != managerId) {
            return ResponseCode.NO_ACCESS_RIGHTS;
        }
        task.setManagerId(managerId);
        task.setDate(new Date(System.currentTimeMillis()));
        if(!tasksDao.updateById(id,task)) {
            return ResponseCode.ERROR;
        }
        return ResponseCode.OK;
    }

    @Transactional(timeout = 5,readOnly = true)
    public PageData queryTasksPageDataService(HttpServletRequest request) {
        Identity identity = IdentifyUtil.readIdentity(request);
        Integer managerId = managersDao.selectIdByPhone(identity.getPhone());
        Integer count = tasksDao.selectCountByManagerId(managerId);
        Integer pages = count/tasksPageSize;
        if(count % tasksPageSize != 0) {
            pages ++;
        }
        return (tasksPageData = new PageData(tasksPageSize,count,pages));
    }

    @Transactional(timeout = 5,readOnly = true)
    public List<Task> queryTasksService(HttpServletRequest request,Integer page) {
        if(tasksPageData == null) {
            queryTasksPageDataService(request);
        }
        if(page < 1) {
            page = 1;
        } else if(page > tasksPageData.getPages()) {
            page = tasksPageData.getPages();
        }
        Identity identity = IdentifyUtil.readIdentity(request);
        Integer managerId = managersDao.selectIdByPhone(identity.getPhone());
        return tasksDao.selectByManagerIdAndPage(managerId,tasksPageSize*(page-1),tasksPageSize);
    }

    @Transactional(timeout = 5,readOnly = true)
    public PageData queryInspectionRecordsPageDataService(HttpServletRequest request) {
        Identity identity = IdentifyUtil.readIdentity(request);
        Integer managerId = managersDao.selectIdByPhone(identity.getPhone());
        Integer count = manualInspectionRecordsDao.selectCountByManagerId(managerId);
        Integer pages = count/inspectionRecordsPageSize;
        if(count % inspectionRecordsPageSize != 0) {
            pages ++;
        }
        return (inspectionRecordsPageData = new PageData(inspectionRecordsPageSize,count,pages));
    }

    @Transactional(timeout = 5,readOnly = true)
    public List<ManualInspectionRecord> queryInspectionRecordsService(HttpServletRequest request, Integer page) {
        if(tasksPageData == null) {
            queryInspectionRecordsPageDataService(request);
        }
        if(page < 1) {
            page = 1;
        } else if(page > inspectionRecordsPageData.getPages()) {
            page = inspectionRecordsPageData.getPages();
        }
        Identity identity = IdentifyUtil.readIdentity(request);
        Integer managerId = managersDao.selectIdByPhone(identity.getPhone());
        return manualInspectionRecordsDao.
                selectByManagerIdAndPage(managerId,inspectionRecordsPageSize*(page-1),inspectionRecordsPageSize);
    }

}
