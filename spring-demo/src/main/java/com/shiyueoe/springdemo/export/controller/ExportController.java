package com.shiyueoe.springdemo.export.controller;


import com.shiyueoe.springdemo.export.service.ExportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("export")
public class ExportController {

    @Resource
    private ExportService exportService;

    @GetMapping("test")
    public String export() {
        return exportService.exportCSV();
    }

}