package com.tour.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table( name = "tb_term_record")
public class TermRecord {
    @Id
    private Long id;

    private Long termId;

    private String termContent;

    private Long agencyId;

    private String agencyName;

    private Integer status;

    private String createName;

    private Date startTime;

}
