package com.thor.service;

import com.thor.dao.InspectorsDao;
import com.thor.dao.ManualInspectionRecordsDao;
import com.thor.dao.TasksDao;
import com.thor.entity.Inspector;
import com.thor.entity.ManualInspectionRecord;
import com.thor.entity.Task;
import com.thor.type.Gender;
import com.thor.type.Identity;
import com.thor.type.PageData;
import com.thor.type.ResponseCode;
import com.thor.util.IdentifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

@Service
public class InspectorService {

    private InspectorsDao inspectorsDao;
    private TasksDao tasksDao;
    private ManualInspectionRecordsDao manualInspectionRecordsDao;

    private Integer tasksPageSize;
    private PageData tasksPageData;

    private Integer inspectionRecordsPageSize;
    private PageData inspectionRecordsPageData;

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

    @Value("${thor.page-size.inspectors-tasks-page-size}")
    public void setTasksPageSize(Integer tasksPageSize) {
        this.tasksPageSize = tasksPageSize;
    }
    @Value("${thor.page-size.inspector-manual-inspection-records-page-size}")
    public void setInspectionRecordsPageSize(Integer inspectionRecordsPageSize) {
        this.inspectionRecordsPageSize = inspectionRecordsPageSize;
    }

    @Transactional(timeout = 5,readOnly = true)
    public Inspector queryMyInformationService(HttpServletRequest request) {
        Identity identity = IdentifyUtil.readIdentity(request);
        return inspectorsDao.selectByPhone(identity.getPhone());
    }

    @Transactional(timeout = 5,rollbackFor = Exception.class)
    public ResponseCode updateNameService(HttpServletRequest request,
                                          Integer id, String name) {
        if(id == null || name == null || name.equals("")) {
            return ResponseCode.ERROR;
        }
        if(!inspectorsDao.updatePropertyById(id,"name",name)) {
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
        if(inspectorsDao.selectByPhone(phone) != null) {
            return ResponseCode.DUPLICATE_WRITE;
        }
        if(!inspectorsDao.updatePropertyById(id,"phone",phone)) {
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
        if(!inspectorsDao.updatePropertyById(id,"password",password)) {
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
        if(!inspectorsDao.updatePropertyById(id,"gender",gender)) {
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
        if(!inspectorsDao.updatePropertyById(id,"age",age)) {
            return ResponseCode.ERROR;
        }
        return ResponseCode.OK;
    }

    @Transactional(timeout = 5,readOnly = true)
    public PageData queryTasksPageDataService(HttpServletRequest request) {
        Identity identity = IdentifyUtil.readIdentity(request);
        Integer inspectorId = inspectorsDao.selectIdByPhone(identity.getPhone());
        Integer count = tasksDao.selectCountByInspectorId(inspectorId);
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
        Integer inspectorId = inspectorsDao.selectIdByPhone(identity.getPhone());
        return tasksDao.selectByInspectorIdAndPage(inspectorId,tasksPageSize*(page-1),tasksPageSize);
    }

    @Transactional(timeout = 5,rollbackFor = Exception.class)
    public ResponseCode uploadInspectionRecordService(HttpServletRequest request,
                                                      ManualInspectionRecord record) {
        if(record == null) {
            return ResponseCode.ERROR;
        }
        Identity identity = IdentifyUtil.readIdentity(request);
        /**
         * find my inspector id by phone
         */
        Integer inspectorId = inspectorsDao.selectIdByPhone(identity.getPhone());
        /**
         * get task by passed manual inspection record
         */
        Task task = tasksDao.selectById(record.getTaskId());
        if(task == null) {
            return ResponseCode.ERROR;
        } else if(task.getInspectorId() != inspectorId) {
            return ResponseCode.NO_ACCESS_RIGHTS;
        }
        if(manualInspectionRecordsDao.selectByTaskId(record.getTaskId()) != null) {
            return ResponseCode.DUPLICATE_WRITE;
        }
        record.setDate(new Date(System.currentTimeMillis()));
        if(!manualInspectionRecordsDao.insertOne(record)) {
            return ResponseCode.ERROR;
        }
        return ResponseCode.OK;
    }

    @Transactional(timeout = 5,rollbackFor = Exception.class)
    public ResponseCode updateInspectionRecordService(HttpServletRequest request,
                                                      Integer id,
                                                      ManualInspectionRecord record) {
        if(record == null) {
            return ResponseCode.ERROR;
        }
        Identity identity = IdentifyUtil.readIdentity(request);
        Integer inspectorId = inspectorsDao.selectIdByPhone(identity.getPhone());
        ManualInspectionRecord testRecord = manualInspectionRecordsDao.selectById(id);
        if(testRecord == null) {
            return ResponseCode.ERROR;
        }
        Task task = tasksDao.selectById(testRecord.getTaskId());
        if(task.getInspectorId() != inspectorId) {
            return ResponseCode.NO_ACCESS_RIGHTS;
        }
        record.setDate(new Date(System.currentTimeMillis()));
        if(!manualInspectionRecordsDao.updateById(id,record)) {
            return ResponseCode.ERROR;
        }
        return ResponseCode.OK;
    }

    @Transactional(timeout = 5,readOnly = true)
    public PageData queryInspectionRecordsPageDataService(HttpServletRequest request) {
        Identity identity = IdentifyUtil.readIdentity(request);
        Integer inspectorId = inspectorsDao.selectIdByPhone(identity.getPhone());
        Integer count = manualInspectionRecordsDao.selectCountByInspectorId(inspectorId);
        Integer pages = count/inspectionRecordsPageSize;
        if(count % inspectionRecordsPageSize != 0) {
            pages ++;
        }
        return (inspectionRecordsPageData = new PageData(inspectionRecordsPageSize,count,pages));
    }

    @Transactional(timeout = 5,readOnly = true)
    public List<ManualInspectionRecord> queryInspectionRecordsService(HttpServletRequest request,Integer page) {
        if(tasksPageData == null) {
            queryInspectionRecordsPageDataService(request);
        }
        if(page < 1) {
            page = 1;
        } else if(page > inspectionRecordsPageData.getPages()) {
            page = inspectionRecordsPageData.getPages();
        }
        Identity identity = IdentifyUtil.readIdentity(request);
        Integer inspectorId = inspectorsDao.selectIdByPhone(identity.getPhone());
        return manualInspectionRecordsDao.
                selectByInspectorIdAndPage(inspectorId,inspectionRecordsPageSize*(page-1),inspectionRecordsPageSize);
    }

}
