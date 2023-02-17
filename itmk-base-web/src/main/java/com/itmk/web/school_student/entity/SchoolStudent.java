package com.itmk.web.school_student.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("school_student")
public class SchoolStudent implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long stuId;
    private Long classId;
    @TableField(exist = false)
    private Long collageId;
    @TableField(exist = false)
    private Long majorId;
    @TableField(exist = false)
    private Long roleId;
    private String stuName;
    private String sex;
    private String stuNum;
    private String idCard;
    private String phone;
    private String password;
    @TableField(exist = false)
    private String className;
    @TableField(exist = false)
    private String majorName;
    @TableField(exist = false)
    private String collageName;

}
