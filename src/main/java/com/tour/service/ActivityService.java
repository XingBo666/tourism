package com.tour.service;

import com.tour.entity.Activity;
import com.tour.entity.ActivityCost;
import com.tour.entity.Term;
import com.tour.mapper.ActivityCostMapper;
import com.tour.mapper.ActivityMapper;
import com.tour.mapper.TermMapper;
import com.tour.vo.CostCreateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityService {

    @Autowired
    ActivityMapper activityMapper;

    @Autowired
    ActivityCostMapper activityCostMapper;

    @Autowired
    TermMapper termMapper;

    @Transactional
    //  活动记录表中新增信息，同时操作副表，具体参与活动人员表
    public Boolean add(CostCreateVo vo) {
       try {
           //  先添加主表的信息
           Activity activity = new Activity();
           activity.setAccount(vo.getAccount());
           activity.setContent(vo.getContent());
           activity.setMoney(vo.getMoney());
           activity.setTermId(vo.getTermId());

           //  先存主表
           int insert = activityMapper.insert(activity);
           //  遍历数组，存入副表
           List<Long> ids = vo.getSelectList();
           ids.forEach(id -> {
               ActivityCost activityCost = new ActivityCost();
               activityCost.setActivityId(new Long((long) insert));
               activityCost.setAgencyId(id);
               activityCost.setActivityName(vo.getContent());
               activityCost.setMoney(vo.getMoney() / ids.size());

               //   存副表
               activityCostMapper.insert(activityCost);
           });
       }catch (Exception e){
           e.printStackTrace();
           return false;
       }
       return true;

    }

    //  根据用户id找到用户创建的所有活动
    public List<Map<String,Object>> getByCreateId(Long id) {
        List<Map<String,Object>> maps = new ArrayList<>();
        //  首先根据id获取用户创建的团队
        Term term = new Term();
        term.setCreateId(id);
        List<Term> terms = termMapper.select(term);

        //  遍历团队集合，获取创建的活动集合
        /*List<Activity> activities = new ArrayList<>();*/
        terms.forEach(t -> {

            Activity activity = new Activity();
            activity.setTermId(t.getId());
            List<Activity> activities1 = activityMapper.select(activity);

            activities1.forEach( a -> {

                Map<String,Object> map = new HashMap<>();
                map.put("termName",t.getContent());
                map.put("activityname",a.getContent());
                map.put("status",t.getStatus());
                map.put("startTime",t.getStartTime());
                map.put("termId",t.getId());
                maps.add(map);
            });



        });
        return maps;
    }
}
