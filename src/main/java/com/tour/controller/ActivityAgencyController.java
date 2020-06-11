package com.tour.controller;

import com.tour.service.ActivityAgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("activityAgency")
public class ActivityAgencyController {
    @Autowired
    ActivityAgencyService activityAgencyService;
}
