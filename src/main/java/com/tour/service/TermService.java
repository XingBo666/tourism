package com.tour.service;

import com.tour.entity.Term;
import com.tour.entity.TermRecord;
import com.tour.mapper.ActivityMapper;
import com.tour.mapper.AgencyMapper;
import com.tour.mapper.TermMapper;
import com.tour.mapper.TermRecordMapper;
import com.tour.vo.JoinVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TermService {

    @Autowired
    TermMapper termMapper;

    @Autowired
    AgencyMapper agencyMapper;

    @Autowired
    TermRecordService termRecordService;

    @Autowired
    ActivityMapper activityMapper;

    @Autowired
    TermRecordMapper termRecordMapper;

    public List<Term> list() {
        Term term = new Term();
        term.setStatus(0);
        return termMapper.select(term);
    }

    //  开启事务，创建一个团队
    public Long add(Term term) {
        Long insert1;

        if (term.getCreateId() == null){
            return null;
        }
        //
        term.setId(null);
        try {
            term.setStatus(0);
            term.setCreateName(agencyMapper.selectByPrimaryKey(term.getCreateId()).getName());
            termMapper.insert(term);
            Long insert = term.getId();
            //  创建队伍的时候把团长加进去
            TermRecord termRecord = new TermRecord();
            termRecord.setAgencyId(term.getCreateId());
            termRecord.setTermId(new Long((long) insert));
            termRecordService.add(termRecord);
            //  创建队伍的同时，生成一个活动，活动内容和队伍内容一致
//            Activity activity = new Activity();
//            activity.setTermId(new Long((long) insert));
//            activity.setMoney(term.getTotalBudget());
//            activity.setContent(term.getContent());
//            activity.setAccount(agencyMapper.selectByPrimaryKey(term.getCreateId()).getEmail());
//            activity.setId(null);
//            activityMapper.insert(activity);

            insert1  = new Long((long) insert);


        }catch (Exception e){
            e.printStackTrace();
            return 0L;
        }
        return insert1;
    }

    //  查找我创建的团队
    public List<Term> myterm(Long id) {
        Term term = new Term();
        term.setCreateId(id);
        List<Term> select = termMapper.select(term);
        if (select.size() == 0){
            return select;
        }
        for (int i = 0 ; i < select.size(); i ++){
            if (select.get(i).getStatus() == 2){
                select.remove(i);
                i --;
            }
        }
        return select;
    }

    //  更新组团的状态
    public Boolean updateStatus(Long id) {
        //  首先找到这条记录
        try {
            Term term = termMapper.selectByPrimaryKey(id);
            term.setStatus(2);
            termMapper.updateByPrimaryKey(term);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //  加入一个团队
    public Boolean join(JoinVo vo) {

        try {
            //  首先查询是否已经在了
            TermRecord termRecord = new TermRecord();
            termRecord.setAgencyId(vo.getAgencyId());
            termRecord.setTermId(vo.getTermId());

            List<TermRecord> termRecords = termRecordMapper.select(termRecord);
            if (termRecords.size() == 0){
                //  没有在记录中，新增一个
                Term term = termMapper.selectByPrimaryKey(vo.getTermId());
                termRecord.setCreateName(agencyMapper.selectByPrimaryKey(term.getCreateId()).getName());
                termRecord.setTermContent(term.getContent());
                termRecord.setStatus(2);
                termRecord.setAgencyName(agencyMapper.selectByPrimaryKey(vo.getAgencyId()).getNickName());
                termRecord.setStartTime(term.getStartTime());
                termRecordMapper.insert(termRecord);
            }else {
                termRecord = termRecords.get(0);
                termRecord.setStatus(2);
                termRecordMapper.updateByPrimaryKeySelective(termRecord);
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
