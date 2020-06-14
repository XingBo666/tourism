package com.tour.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table( name = "tb_term")
public class Term {

    @Id
    @GeneratedValue( generator = "JDBC" ,strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Date startTime;

    private Integer totalBudget;

    private Integer personBudget;

    private Long createId;

    private String createName;

    private Integer status;
}
