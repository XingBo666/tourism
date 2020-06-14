package com.tour.controller;

import com.tour.entity.TermRecord;
import com.tour.service.TermRecordService;
import com.tour.vo.ManyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("termRecord")
public class TermRecordController {

    @Autowired
    TermRecordService termRecordService;

    @GetMapping("{id}")
    public ResponseEntity<List<TermRecord>> getById(@PathVariable("id") Long id){
        List<TermRecord> termRecords = termRecordService.getById(id);
        if (CollectionUtils.isEmpty(termRecords)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(termRecords);
    }

    @GetMapping("getByTerm/{id}")
    public ResponseEntity<List<TermRecord>> getByTerm(@PathVariable("id") Long id){
        List<TermRecord> termRecords = termRecordService.getByTerm(id);
        if (CollectionUtils.isEmpty(termRecords)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(termRecords);
    }

    //  在某个团队添加一位成员
    @PostMapping("")
    public ResponseEntity<Boolean> add(@RequestBody TermRecord termRecord){
        Boolean bool = termRecordService.add(termRecord);

        if (bool == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);

    }

    //  批量添加成员
    @PostMapping("/invite")
    public ResponseEntity<Boolean> add(@RequestBody ManyVo vo){
        Boolean bool = termRecordService.manyAdd(vo);

        if (bool == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }



}
