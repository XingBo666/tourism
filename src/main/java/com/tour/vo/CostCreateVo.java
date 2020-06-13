package com.tour.vo;

import lombok.Data;

import java.util.List;

@Data
public class CostCreateVo {
    private String content;

    private Integer money;

    private String account;

    private Long termId;

    private List<Long> selectList;
}
