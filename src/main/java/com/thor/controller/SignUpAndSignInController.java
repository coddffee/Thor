package com.thor.controller;

import com.thor.entity.InspectorRegistry;
import com.thor.entity.ManagerRegistry;
import com.thor.service.SignUpAndSignInService;
import com.thor.type.Identity;
import com.thor.type.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SignUpAndSignInController {

    private SignUpAndSignInService service;

    @Autowired
    public void setService(SignUpAndSignInService service) {
        this.service = service;
    }

    @ResponseBody
    @PostMapping(value = "sign-up/manager",
            consumes = "application/json;charset=utf-8")
    public ResponseCode managerSignUp(HttpServletRequest request,
                                      @RequestBody ManagerRegistry registry) {

        return service.managerSignUpService(request,registry);
    }

    @ResponseBody
    @GetMapping("my/registry/manager")
    public ManagerRegistry managerQueryRegistry(HttpServletRequest request) {
        return service.managerQueryRegistryService(request);
    }

    @ResponseBody
    @PostMapping(value = "sign-in/manager",
            consumes = "application/json;charset=utf-8")
    public ResponseCode managerSignIn(HttpServletRequest request,
                                      @RequestBody Identity identity) {
        return service.managerSignInService(request,identity);
    }

    @ResponseBody
    @PostMapping(value = "sign-up/inspector",
            consumes = "application/json;charset=utf-8")
    public ResponseCode inspectorSignUp(HttpServletRequest request,
                                        @RequestBody InspectorRegistry registry) {
        return service.inspectorSignUpService(request,registry);
    }

    @ResponseBody
    @GetMapping("my/registry/inspector")
    public InspectorRegistry inspectorQueryRegistry(HttpServletRequest request) {
        return service.inspectorQueryRegistry(request);
    }

    @ResponseBody
    @PostMapping(value = "sign-in/inspector",
            consumes = "application/json;charset=utf-8")
    public ResponseCode inspectorSignIn(HttpServletRequest request,
                                        @RequestBody Identity identity) {
        return service.inspectorSignInService(request,identity);
    }

    @GetMapping(value = "sign-out")
    public String signOut(HttpServletRequest request) {
        service.signOutService(request);
        return "sign-in.html";
    }

}
