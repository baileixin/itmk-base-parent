package com.itmk.web.school_collage.entity;

import lombok.Data;


@Data
public class ListParm {
    private Long currentPage;
    private Long pageSize;
    private Long collageId;
    private String collageName;
}
