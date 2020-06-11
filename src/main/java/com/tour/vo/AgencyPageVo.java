package com.tour.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AgencyPageVo extends BasePageVo{

    private String name;

    private String phone;

    private String email;

    private Date brithday;

    private Integer sex;
}
