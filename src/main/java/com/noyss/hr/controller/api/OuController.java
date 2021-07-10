package com.noyss.hr.controller.api;

import com.noyss.hr.repository.custom.IOuRepositoryCustom;
import flexjson.JSONSerializer;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "OuController", description = "REST Apis related to OU Entity!!!!")
@RestController
@RequestMapping("/api/ou")
public class OuController {

    @Autowired
    private IOuRepositoryCustom iOuRepositoryCustom;

    @GetMapping
    public String findAll() {
       return new JSONSerializer().serialize(iOuRepositoryCustom.findAll());
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id) {
        return new JSONSerializer().serialize(iOuRepositoryCustom.findById(id));
    }

    @GetMapping("/findByCode")
    public String findByCode(@RequestParam("code") String code) {
        return new JSONSerializer().serialize(iOuRepositoryCustom.findByCode(code));
    }

    @PostMapping
    public String save(@RequestBody String dataJson){
        return new JSONSerializer().serialize(iOuRepositoryCustom.save(dataJson));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        return new JSONSerializer().serialize(iOuRepositoryCustom.deleteById(id));
    }
}
