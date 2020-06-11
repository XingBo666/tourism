package com.tour.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table( name = "tb_activity_cost")
public class ActivityCost {

    @Id
    private Long id;

    private Long activityId;

    private String activityName;

    private Long agencyId;

    private Integer type;
}
