package com.tour.service;

import com.tour.entity.Agency;
import com.tour.entity.Term;
import com.tour.entity.TermRecord;
import com.tour.mapper.AgencyMapper;
import com.tour.mapper.TermMapper;
import com.tour.mapper.TermRecordMapper;
import com.tour.vo.ManyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TermRecordService {

    @Autowired
    TermRecordMapper termRecordMapper;

    @Autowired
    TermMapper termMapper;

    @Autowired
    AgencyMapper agencyMapper;

    //  根据会员id查找记录
    public List<TermRecord> getById(Long id) {
        TermRecord termRecord = new TermRecord();
        termRecord.setAgencyId(id);
        List<TermRecord> termRecords = termRecordMapper.select(termRecord);
        return termRecords;
    }

    //  根据团队的id查找记录数组
    public List<TermRecord> getByTerm(Long id) {
        TermRecord termRecord = new TermRecord();
        termRecord.setTermId(id);
        List<TermRecord> termRecords = termRecordMapper.select(termRecord);
        return termRecords;
    }

    //  添加一条记录
    public Boolean add(TermRecord termRecord) {

        if (termRecord.getAgencyId() == null || termRecord.getTermId() == null){
            return null;
        }
        try {
            Term term = termMapper.selectByPrimaryKey(termRecord.getTermId());
            Agency agency = agencyMapper.selectByPrimaryKey(termRecord.getAgencyId());
            Agency create = agencyMapper.selectByPrimaryKey(term.getCreateId());

            termRecord.setAgencyName(agency.getName());
            termRecord.setCreateName(create.getName());
            termRecord.setTermContent(term.getContent());
            termRecord.setStartTime(term.getStartTime());
            termRecord.setStatus(2);

            //  存入表中
            termRecordMapper.insert(termRecord);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;

    }

    //  批量邀请成员
    @Transactional //开启事务
    public Boolean manyAdd(ManyVo vo) {

        try {
            List<Long> ids = vo.getAgencies();

            Term term = termMapper.selectByPrimaryKey(vo.getTermId());
            Agency agency = agencyMapper.selectByPrimaryKey(term.getCreateId());
            ids.forEach( id-> {
                //
                Agency invite = agencyMapper.selectByPrimaryKey(id);

                TermRecord termRecord = new TermRecord();
                termRecord.setStatus(0);
                termRecord.setTermId(term.getId());
                termRecord.setAgencyId(id);
                termRecord.setAgencyName(invite.getNickName());
                termRecord.setStartTime(term.getStartTime());
                termRecord.setTermContent(term.getContent());
                termRecord.setCreateName(agency.getNickName());

                termRecordMapper.insert(termRecord);
            });
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
