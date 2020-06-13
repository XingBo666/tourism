package com.tour.controller;

import com.tour.service.ActivityService;
import com.tour.vo.CostCreateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @PostMapping("")
    public ResponseEntity<Boolean> add(@RequestBody CostCreateVo vo){
        Boolean bool = activityService.add(vo);
        if (bool == null ){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    //  找出自己创建的活动
    @GetMapping("{id}")
    public ResponseEntity<List<Map<String,Object>>> getByCreateId(@PathVariable("id") Long id) {
        List<Map<String,Object>> maps = activityService.getByCreateId(id);
        if (CollectionUtils.isEmpty(maps)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(maps);
    }
}
