package com.tour.service;

import com.tour.mapper.ActivityAgencyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityAgencyService {

    @Autowired
    ActivityAgencyMapper activityAgencyMapper;
}
