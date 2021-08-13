package com.thor.controller;

import com.thor.entity.Manager;
import com.thor.entity.ManagerRegistry;
import com.thor.service.SystemManagerService;
import com.thor.type.Identity;
import com.thor.type.PageData;
import com.thor.type.ResponseCode;
import com.thor.util.IdentifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("system-manager")
public class SystemManagerController {

    private SystemManagerService service;

    @Autowired
    public void setService(SystemManagerService service) {
        this.service = service;
    }

    @PostMapping(value = "sign-in",consumes = "application/json;charset=utf-8")
    public ResponseCode signIn(@RequestBody Identity identity,HttpServletRequest request) {
        ResponseCode code = service.signInService(identity);
        if(code == ResponseCode.OK) {
            IdentifyUtil.writeIdentity(request,identity,0);
        }
        return code;
    }

    @GetMapping("managers-registry")
    public PageData queryManagersRegistryPageData() {
        return service.queryManagersRegistryPageDataService();
    }

    @GetMapping("managers-registry/{page}")
    public List<ManagerRegistry> queryManagersRegistry(@PathVariable("page") Integer page) {
        return service.queryManagersRegistryService(page);
    }

    @PatchMapping("accept/manager/{id}")
    public ResponseCode acceptManager(@PathVariable("id") Integer id) {
        return service.acceptManagerService(id);
    }

    @GetMapping("managers")
    public PageData queryManagersPageData() {
        return service.queryManagersPageDataService();
    }

    @GetMapping("managers/{page}")
    public List<Manager> queryManagers(@PathVariable("page") Integer page) {
        return service.queryManagersService(page);
    }

}
