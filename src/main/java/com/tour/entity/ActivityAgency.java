package com.tour.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table( name = "tb_activity_agency")
public class ActivityAgency {

    @Id
    private Long id;

    private Long agencyId;

    private String agencyName;

    private Long activityId;
}
