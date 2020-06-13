package com.tour.controller;

import com.tour.entity.Term;
import com.tour.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("term")
public class TermController {

    @Autowired
    TermService termService;

    //  查询所有的term
    @GetMapping("list")
    public ResponseEntity<List<Term>> list() {
        List<Term> terms = termService.list();
        if (CollectionUtils.isEmpty(terms)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(terms);
    }

    //  添加一个队伍
    @PostMapping("")
    public ResponseEntity<Boolean> add(@RequestBody Term term) {
        Boolean bool = termService.add(term);
        //

        if (bool == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    //  根据id找到自己创建的队伍
    @GetMapping("myterm/{id}")
    public ResponseEntity<List<Term>> myterm(@PathVariable("id") Long id) {
        List<Term> terms = termService.myterm(id);
        if (CollectionUtils.isEmpty(terms)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(terms);
    }

    //  结束行程，终结此次组团
    @PutMapping("status/{id}")
    public ResponseEntity<Boolean> updateStatus(@PathVariable("id") Long id){
        Boolean bool = termService.updateStatus(id);
        if ( bool == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }
}
