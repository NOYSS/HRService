package com.noyss.hr.controller.api;

import com.noyss.hr.repository.custom.IAppUserRepositoryCustom;
import flexjson.JSONSerializer;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "AppUserController", description = "REST Apis related to App User Entity!!!!")
@RestController
@RequestMapping("/api/appUser")
public class AppUserController {
    
    @Autowired
    private IAppUserRepositoryCustom iAppUserRepositoryCustom;

    @GetMapping
    public String findAll() {
        return new JSONSerializer().serialize(iAppUserRepositoryCustom.findAll());
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id) {
        return new JSONSerializer().serialize(iAppUserRepositoryCustom.findById(id));
    }

    @GetMapping("/findByUsername")
    public String findByUsername(@RequestParam("username") String username) {
        return new JSONSerializer().serialize(iAppUserRepositoryCustom.findByUsername(username));
    }

    @PostMapping
    public String save(@RequestBody String dataJson){
        return new JSONSerializer().serialize(iAppUserRepositoryCustom.save(dataJson));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        return new JSONSerializer().serialize(iAppUserRepositoryCustom.deleteById(id));
    }
}
