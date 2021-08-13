package com.thor.controller;

import com.thor.entity.Manager;
import com.thor.service.AutomaticInspectionManagerService;
import com.thor.type.Gender;
import com.thor.type.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("automatic-inspection-manager")
public class AutomaticInspectionManagerController {

    private AutomaticInspectionManagerService service;

    @Autowired
    public void setService(AutomaticInspectionManagerService service) {
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
}
