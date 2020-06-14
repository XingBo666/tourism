package com.tour.service;

import com.tour.entity.*;
import com.tour.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityCostService {

    @Autowired
    TermMapper termMapper;

    @Autowired
    TermRecordMapper termRecordMapper;

    @Autowired
    AgencyMapper agencyMapper;

    @Autowired
    ActivityMapper activityMapper;

    @Autowired
    ActivityCostMapper activityCostMapper;

    //
    public List<Map<String, Object>> cost(Long id) {
        //  首先确定需要的数据
        //  活动名，费用，开始日期。昵称等
        Term term = termMapper.selectByPrimaryKey(id);
        //  在termRecord表中找到所有数据
        List<Map<String,Object>> maps = new ArrayList<>();
        TermRecord termRecord = new TermRecord();
        termRecord.setTermId(id);
        List<TermRecord> termRecords = termRecordMapper.select(termRecord);
        //  遍历termRecord
        termRecords.forEach( t -> {
            Agency agency = agencyMapper.selectByPrimaryKey(t.getAgencyId());

            Map<String,Object> map = new HashMap<>();
            map.put("activityname",term.getContent());
            map.put("money",term.getPersonBudget());
            map.put("startTime",term.getStartTime());
            map.put("nickName",agency.getNickName());

            maps.add(map);
        });

        //  根据termId找到活动记录
        List<ActivityCost> allActivity  = new ArrayList<>();
        //  首先根据termId找到对应的所有活动
        Activity activity = new Activity();
        activity.setTermId(id);
        List<Activity> activities = activityMapper.select(activity);
        //  遍历这个集合，找到对应的所有活动经费记录
        activities.forEach( a -> {
            ActivityCost activityCost = new ActivityCost();
            activityCost.setActivityId(a.getId());
            List<ActivityCost> activityCosts = activityCostMapper.select(activityCost);
            //  将这个数组追加到allActivity中
            allActivity.addAll(activityCosts);
        });
        //  遍历all数组
        allActivity.forEach( a -> {
            Map<String,Object> map = new HashMap<>();
            map.put("activityname",a.getActivityName());
            map.put("money",a.getMoney());
            map.put("startTime",term.getStartTime());
            map.put("nickName",agencyMapper.selectByPrimaryKey(a.getAgencyId()).getNickName());
            maps.add(map);
        });

        return maps;
    }
}
