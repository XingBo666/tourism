package com.tour.controller;

import com.tour.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("term")
public class TermController {

    @Autowired
    TermService termService;
}
