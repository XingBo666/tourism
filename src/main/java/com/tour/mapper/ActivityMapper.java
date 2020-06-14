package com.tour.mapper;

import com.tour.entity.Activity;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ActivityMapper extends Mapper<Activity> {
}
