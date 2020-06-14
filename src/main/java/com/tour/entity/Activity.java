package com.tour.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table( name = "tb_activity")
public class Activity {
    @Id
    @GeneratedValue( generator = "JDBC" ,strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String account;

    private Long termId;

    private Integer money;


}
