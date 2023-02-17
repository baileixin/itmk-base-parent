package com.itmk.web.dorm_room.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itmk.web.drom_room_bed.entity.DromRoomBed;
import com.itmk.web.drom_room_bed.entity.DromRoomVo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@TableName("dorm_room")
public class DormRoom {
    //床位列表
    @TableField(exist = false)
    List<DromRoomVo> bedList = new ArrayList<>();
    @TableId(type = IdType.AUTO)
    private Long roomId;
    private Long storeyId;
    private String rootType;
    private String roomCode;
    private Integer totalBed;
}
