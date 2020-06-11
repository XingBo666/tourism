package com.tour.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

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

}
