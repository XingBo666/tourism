package com.tour.controller;

import com.tour.service.ActivityCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("activityCost")
public class ActivityCostController {

    @Autowired
    ActivityCostService activityCostService;
}
