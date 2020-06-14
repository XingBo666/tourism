package com.tour.vo;

import lombok.Data;

import java.util.List;

@Data
public class ManyVo {

    private Long termId;

    private List<Long> agencies;
}
