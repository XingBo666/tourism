package com.tour.mapper;

import com.tour.entity.Term;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface TermMapper extends Mapper<Term> {
}
