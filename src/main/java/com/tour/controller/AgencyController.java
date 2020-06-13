package com.tour.controller;

import com.github.pagehelper.PageInfo;
import com.tour.entity.Agency;
import com.tour.service.AgencyService;
import com.tour.vo.AgencyPageVo;
import com.tour.vo.LoginVo;
import com.tour.vo.RegistryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("agency")
public class AgencyController {

    @Autowired
    AgencyService agencyService;

    //  首先是会员的增删改查
    //  分页查询
    @PostMapping("list")
    public ResponseEntity<PageInfo<Agency>> list (@RequestBody AgencyPageVo vo){
        PageInfo<Agency> agencyPageInfo = agencyService.list(vo);
        if (CollectionUtils.isEmpty(agencyPageInfo.getList())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(agencyPageInfo);
    }

    //  增加一个会员
    @PostMapping("")
    public ResponseEntity<Boolean> add(@RequestBody Agency agency) {
        Boolean bool = agencyService.add(agency);
        if (bool == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    //  修改会员
    @PutMapping("")
    public ResponseEntity<Boolean> update(@RequestBody Agency agency) {
        Boolean bool = agencyService.update(agency);
        if (bool == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    //  删除一个会员，逻辑删除
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
        Boolean bool = agencyService.delete(id);
        if (bool == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    //  根据id查询一个会员
    @GetMapping("{id}")
    public ResponseEntity<Agency> findById(@PathVariable("id") Long id){
        Agency agency = agencyService.findById(id);
        if (agency == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(agency);
    }

    //  登录的方法
    @PostMapping("login")
    public ResponseEntity<Agency> login (@RequestBody LoginVo vo){
        Agency agency = agencyService.login(vo);
        if (agency == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(agency);
    }

    //  会员注册功能
    @PostMapping("registry")
    public ResponseEntity<Boolean> registry(RegistryVo vo) {
        Boolean bool = agencyService.registry(vo);
        if (bool == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }
}
