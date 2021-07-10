package com.noyss.hr.controller.api;

import com.noyss.hr.repository.custom.IAppRoleRepositoryCustom;
import flexjson.JSONSerializer;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "AppRoleController", description = "REST Apis related to App Role Entity!!!!")
@RestController
@RequestMapping("/api/appRole")
public class AppRoleController {
    
    @Autowired
    private IAppRoleRepositoryCustom iAppRoleRepositoryCustom;

    @GetMapping
    public String findAll() {
        return new JSONSerializer().serialize(iAppRoleRepositoryCustom.findAll());
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id) {
        return new JSONSerializer().serialize(iAppRoleRepositoryCustom.findById(id));
    }

    @GetMapping("/findByCode")
    public String findByCode(@RequestParam("code") String code) {
        return new JSONSerializer().serialize(iAppRoleRepositoryCustom.findByCode(code));
    }

    @PostMapping
    public String save(@RequestBody String dataJson){
        return new JSONSerializer().serialize(iAppRoleRepositoryCustom.save(dataJson));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        return new JSONSerializer().serialize(iAppRoleRepositoryCustom.deleteById(id));
    }
}
