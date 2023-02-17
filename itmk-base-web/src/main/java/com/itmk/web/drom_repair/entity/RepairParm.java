package com.itmk.web.drom_repair.entity;

import lombok.Data;


@Data
public class RepairParm {
    private String dromName;
    private Long currentPage;
    private Long pageSize;
}
