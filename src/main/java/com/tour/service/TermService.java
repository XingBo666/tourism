package com.tour.service;

import com.tour.mapper.TermMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TermService {

    @Autowired
    TermMapper termMapper;
}
