package com.tour.service;

import com.tour.mapper.TermRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TermRecordService {

    @Autowired
    TermRecordMapper termRecordMapper;
}
