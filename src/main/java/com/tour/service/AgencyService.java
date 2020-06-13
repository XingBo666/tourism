package com.tour.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tour.entity.Agency;
import com.tour.mapper.AgencyMapper;
import com.tour.vo.AgencyPageVo;
import com.tour.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class AgencyService {

    @Autowired
    AgencyMapper agencyMapper;

    public PageInfo<Agency> list(AgencyPageVo vo) {

        Example example = new Example(Agency.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag",0);
        //  对条件进行判断
        if(!StringUtils.isEmpty(vo.getName())){
            criteria.andLike("name",vo.getName());
        }
        if(!StringUtils.isEmpty(vo.getEmail())){
            criteria.andLike("email",vo.getEmail());
        }
        if(!StringUtils.isEmpty(vo.getPhone())){
            criteria.andLike("phone",vo.getPhone());
        }
        //if(vo.getBrithday()!= null){}
        if (vo.getSex()!= null){
            criteria.andEqualTo("sex",vo.getSex());
        }

        PageHelper.startPage(vo.getPageNo(),vo.getPageSize());
        List<Agency> agencies = agencyMapper.selectByExample(example);

        PageInfo<Agency> agencyPageInfo = new PageInfo<>(agencies);


        return agencyPageInfo;
    }

    //  新增会员的方法
    public Boolean add(Agency agency) {
        if (StringUtils.isEmpty(agency.getEmail() ) || StringUtils.isEmpty(agency.getName()) || StringUtils.isEmpty(agency.getPhone())){
            return null;
        }
        agency.setPassword("123456");
        agency.setId(null);
        agency.setCreateTime(new Date());
        agency.setLevel(2);
        agency.setDelFlag(0);

        try {
            agencyMapper.insert(agency);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Boolean update(Agency agency) {
        if (agency.getId() == null ||StringUtils.isEmpty(agency.getEmail() ) || StringUtils.isEmpty(agency.getName()) || StringUtils.isEmpty(agency.getPhone())){
            return null;
        }

        try{
            agencyMapper.updateByPrimaryKeySelective(agency);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;


    }

    public Boolean delete(Long id) {
        if (id == null){
            return null;
        }
        try {
            //  先查询
            Agency agency = agencyMapper.selectByPrimaryKey(id);
            agency.setDelFlag(1);
            agencyMapper.updateByPrimaryKeySelective(agency);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Agency findById(Long id) {
        return agencyMapper.selectByPrimaryKey(id);
    }

    public Agency login(LoginVo vo) {

        Agency agency = new Agency();
        agency.setNickName(vo.getNickName());
        agency.setPassword(vo.getPassword());
        List<Agency> agencies = agencyMapper.select(agency);

        if (CollectionUtils.isEmpty(agencies)){
            return null;
        }

        return agencies.get(0);


    }
}
