package com.tour.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tour.entity.Agency;
import com.tour.mapper.AgencyMapper;
import com.tour.vo.AgencyPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AgencyService {

    @Autowired
    AgencyMapper agencyMapper;

    public PageInfo<Agency> list(AgencyPageVo vo) {
        PageHelper.startPage(vo.getPageNo(),vo.getPageSize());
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

        List<Agency> agencies = agencyMapper.selectByExample(example);

        PageInfo<Agency> agencyPageInfo = new PageInfo<>(agencies);


        return agencyPageInfo;
    }
}
