package com.tour.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table( name = "tb_activity_agency")
public class ActivityAgency {

    @Id
    @GeneratedValue( generator = "JDBC" ,strategy = GenerationType.IDENTITY)
    private Long id;

    private Long agencyId;

    private String agencyName;

    private Long activityId;
}
