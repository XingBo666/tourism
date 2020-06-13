package com.tour.service;

import com.tour.entity.Term;
import com.tour.entity.TermRecord;
import com.tour.mapper.AgencyMapper;
import com.tour.mapper.TermMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TermService {

    @Autowired
    TermMapper termMapper;

    @Autowired
    AgencyMapper agencyMapper;

    @Autowired
    TermRecordService termRecordService;

    public List<Term> list() {
        Term term = new Term();
        term.setStatus(0);
        return termMapper.select(term);
    }

    //  开启事务，创建一个团队
    @Transactional
    public Boolean add(Term term) {
        if (term.getCreateId() == null){
            return null;
        }
        //
        term.setId(null);
        try {
            term.setStatus(0);
            term.setCreateName(agencyMapper.selectByPrimaryKey(term.getCreateId()).getName());
            int insert = termMapper.insert(term);
            //  创建队伍的时候把团长加进去
            TermRecord termRecord = new TermRecord();
            termRecord.setAgencyId(term.getCreateId());
            termRecord.setTermId(new Long((long) insert));
            termRecordService.add(termRecord);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
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
}
