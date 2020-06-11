package com.tour.controller;

import com.github.pagehelper.PageInfo;
import com.tour.entity.Agency;
import com.tour.service.AgencyService;
import com.tour.vo.AgencyPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
