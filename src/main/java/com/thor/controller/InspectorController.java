package com.thor.controller;

import com.thor.entity.Inspector;
import com.thor.entity.ManualInspectionRecord;
import com.thor.entity.Task;
import com.thor.service.InspectorService;
import com.thor.type.Gender;
import com.thor.type.PageData;
import com.thor.type.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("inspector")
public class InspectorController {

    private InspectorService service;

    @Autowired
    public void setService(InspectorService service) {
        this.service = service;
    }

    @GetMapping("my-info")
    public Inspector queryMyInformation(HttpServletRequest request) {
        return service.queryMyInformationService(request);
    }

    @PatchMapping("my-info/update/name/{id}/{name}")
    public ResponseCode updateName(HttpServletRequest request,
                                   @PathVariable("id") Integer id,
                                   @PathVariable("name") String name) {
        return service.updateNameService(request,id,name);
    }

    @PatchMapping("my-info/update/phone/{id}/{phone}")
    public ResponseCode updatePhone(HttpServletRequest request,
                                    @PathVariable("id") Integer id,
                                    @PathVariable("phone") String phone) {
        return service.updatePhoneService(request,id,phone);
    }

    @PatchMapping("my-info/update/password/{id}/{password}")
    public ResponseCode updatePassword(HttpServletRequest request,
                                       @PathVariable("id") Integer id,
                                       @PathVariable("password") String password) {
        return service.updatePasswordService(request,id,password);
    }

    @PatchMapping("my-info/update/gender/{id}/{gender}")
    public ResponseCode updateGender(HttpServletRequest request,
                                     @PathVariable("id") Integer id,
                                     @PathVariable("gender") Gender gender) {
        return service.updateGenderService(request,id,gender);
    }

    @PatchMapping("my-info/update/age/{id}/{age}")
    public ResponseCode updateAge(HttpServletRequest request,
                                  @PathVariable("id") Integer id,
                                  @PathVariable("age") Integer age) {
        return service.updateAgeService(request,id,age);
    }

    @GetMapping("tasks")
    public PageData queryTasksPageData(HttpServletRequest request) {
        return service.queryTasksPageDataService(request);
    }

    @GetMapping("tasks/{page}")
    public List<Task> queryTasks(HttpServletRequest request,@PathVariable("page") Integer page) {
        return service.queryTasksService(request,page);
    }

    @PostMapping("records/upload")
    public ResponseCode uploadInspectionRecord(HttpServletRequest request,
                                               @RequestBody ManualInspectionRecord record) {
        return service.uploadInspectionRecordService(request,record);
    }

    @PutMapping("records/{id}")
    public ResponseCode updateInspectionRecord(HttpServletRequest request,
                                               @PathVariable("id") Integer id,
                                               @RequestBody ManualInspectionRecord record) {
        return service.updateInspectionRecordService(request,id,record);
    }

    @GetMapping("records")
    public PageData queryInspectionRecordsPageData(HttpServletRequest request) {
        return service.queryInspectionRecordsPageDataService(request);
    }

    @GetMapping("records/{page}")
    public List<ManualInspectionRecord> queryInspectionRecords(HttpServletRequest request,
                                                               @PathVariable("page") Integer page) {
        return service.queryInspectionRecordsService(request,page);
    }

}
