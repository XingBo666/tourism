package com.tour.controller;

import com.tour.service.ActivityCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("activityCost")
public class ActivityCostController {

    @Autowired
    ActivityCostService activityCostService;


    //  打印活动消费
    @GetMapping("excel/{id}")
    public ResponseEntity<List<Map<String,Object>>> cost(@PathVariable("id") Long id) {
        List<Map<String,Object>> list = activityCostService.cost(id);
        if (CollectionUtils.isEmpty(list)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }
}
