package com.thor.controller;

import com.thor.entity.*;
import com.thor.service.ManualInspectionManagerService;
import com.thor.type.Gender;
import com.thor.type.PageData;
import com.thor.type.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("manual-inspection-manager")
public class ManualInspectionManagerController {

    private ManualInspectionManagerService service;

    @Autowired
    public void setService(ManualInspectionManagerService service) {
        this.service = service;
    }

    @GetMapping("my-info")
    public Manager queryMyInformation(HttpServletRequest request) {
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

    @GetMapping("inspectors-registry")
    public PageData queryInspectorsRegistryPageData(HttpServletRequest request) {
        return service.queryInspectorsRegistryPageDataService(request);
    }

    @GetMapping("inspectors-registry/{page}")
    public List<InspectorRegistry> queryInspectorsRegistry(HttpServletRequest request,
                                                           @PathVariable("page") Integer page) {
        return service.queryInspectorsRegistryService(request,page);
    }

    @PatchMapping("accept/inspector/{id}")
    public ResponseCode acceptInspector(HttpServletRequest request,
                                        @PathVariable("id") Integer id) {
        return service.acceptInspectorService(request,id);
    }

    @GetMapping("inspectors")
    public PageData queryInspectorsPageData(HttpServletRequest request) {
        return service.queryInspectorsPageDataService(request);
    }

    @GetMapping("inspectors/{page}")
    public List<Inspector> queryInspectors(HttpServletRequest request,
                                           @PathVariable("page") Integer page) {
        return service.queryInspectorsService(request,page);
    }

    @PostMapping("tasks/create")
    public ResponseCode createTask(HttpServletRequest request,
                                   @RequestBody Task task) {
        return service.createTaskService(request,task);
    }

    @PutMapping("tasks/{id}")
    public ResponseCode updateTask(HttpServletRequest request,
                                   @PathVariable("id") Integer id,
                                   @RequestBody Task task) {
        return service.updateTaskService(request,id,task);
    }

    @GetMapping("tasks")
    public PageData queryTasksPageData(HttpServletRequest request) {
        return service.queryTasksPageDataService(request);
    }

    @GetMapping("tasks/{page}")
    public List<Task> queryTasks(HttpServletRequest request,
                                 @PathVariable("page") Integer page) {
        return service.queryTasksService(request,page);
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

