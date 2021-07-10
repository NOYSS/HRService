package com.noyss.hr.controller.api;

import flexjson.JSONSerializer;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@Api(value = "OuController", description = "REST Apis related to OU Entity!!!!")
@RestController
@RequestMapping("/api/ou")
public class OuController {

    @GetMapping
    public String findAll() {
//        return new JSONSerializer().serialize(districtService.findAll());
        return "";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id) {
//        return new JSONSerializer().serialize(districtService.findById(id));
        return "";
    }

    @GetMapping("/findByCode")
    public String findByCode(@RequestParam("code") String code) {
//        return new JSONSerializer().serialize(districtService.findByCode(code));
        return "";
    }
}
