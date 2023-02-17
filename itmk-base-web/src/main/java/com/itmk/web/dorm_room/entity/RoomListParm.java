package com.itmk.web.dorm_room.entity;

import lombok.Data;


@Data
public class RoomListParm {
    private Long currentPage;
    private Long pageSize;
    private Long storeyId;
}
