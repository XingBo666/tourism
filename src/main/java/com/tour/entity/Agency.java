package com.tour.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

//  会员实体类
@Data
@Table( name = "tb_agency")
public class Agency {

    @Id
    @GeneratedValue( generator = "JDBC" ,strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    private Integer sex;

    private Date brithday;

    private String email;

    private Date createTime;

    private Integer delFlag;

    private String nickName;

    private Integer level;

    private String password;

}
