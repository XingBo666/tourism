package com.tour.controller;

import com.tour.service.TermRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("termRecord")
public class TermRecordController {

    @Autowired
    TermRecordService termRecordService;
}
