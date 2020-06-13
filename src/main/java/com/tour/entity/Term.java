package com.tour.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table( name = "tb_term")
public class Term {

    @Id
    private Long id;

    private String content;

    private Date startTime;

    private Integer totalBudget;

    private Integer personBudget;

    private Long createId;

    private String createName;

    private Integer status;
}
