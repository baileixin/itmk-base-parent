package com.itmk.web.school_student.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.school_class.entity.SchoolClass;
import com.itmk.web.school_class.service.SchoolClassService;
import com.itmk.web.school_student.entity.SchoolStudent;
import com.itmk.web.school_student.entity.StuParm;
import com.itmk.web.school_student.service.SchoolStudentService;
import com.itmk.web.sys_role.entity.SysRole;
import com.itmk.web.sys_role.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/student")
public class SchoolStudentController {
    @Autowired
    private SchoolStudentService schoolStudentService;
    @Autowired
    private SchoolClassService schoolClassService;
    @Autowired
    private SysRoleService sysRoleService;

    //新增
    @PostMapping
    public ResultVo add(@RequestBody SchoolStudent schoolStudent) {
        schoolStudent.setPassword(DigestUtils.md5DigestAsHex(schoolStudent.getPassword().getBytes()));
        schoolStudentService.addStu(schoolStudent);
        return ResultUtils.success("新增成功!");
    }

    @PostMapping("/resetPassword")
    public ResultVo resetPassword(@RequestBody SchoolStudent schoolStudent) {
        schoolStudent.setPassword(DigestUtils.md5DigestAsHex(schoolStudent.getPassword().getBytes()));
        boolean b = schoolStudentService.updateById(schoolStudent);
        if (b) {
            return ResultUtils.success("密码重置成功!");
        }
        return ResultUtils.error("密码重置失败!");
    }

    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody SchoolStudent schoolStudent) {
        schoolStudentService.editStu(schoolStudent);
        return ResultUtils.success("编辑成功!");
    }

    //根据id查询学生信息
    @GetMapping("/getById")
    public ResultVo getById(Long stuId) {
        SchoolStudent stu = schoolStudentService.getStuById(stuId);
        return ResultUtils.success("查询成功", stu);
    }

    //删除
    @DeleteMapping("/{stuId}")
    public ResultVo delete(@PathVariable("stuId") Long stuId) {
        schoolStudentService.deleteStu(stuId);
        return ResultUtils.success("删除成功!");
    }

    //列表
    @GetMapping("/list")
    public ResultVo getList(StuParm parm) {
        IPage<SchoolStudent> stu = schoolStudentService.getList(parm);
        return ResultUtils.success("查询成功", stu);
    }

    //列表
    @GetMapping("/getClassList")
    public ResultVo getClassList(Long major) {
        QueryWrapper<SchoolClass> query = new QueryWrapper<>();
        query.lambda().eq(SchoolClass::getMajorId, major);
        List<SchoolClass> list = schoolClassService.list(query);
        return ResultUtils.success("查询成功", list);
    }

    //查询角色列表
    @GetMapping("/getRoleList")
    public ResultVo getRoleList() {
        List<SysRole> list = sysRoleService.list();
        return ResultUtils.success("查询成功", list);
    }

    //根据班级Id查询学生列表
    @GetMapping("/getClassByClassId")
    public ResultVo getClassByClassId(Long classId) {
        QueryWrapper<SchoolStudent> query = new QueryWrapper<>();
        query.lambda().eq(SchoolStudent::getClassId, classId);
        List<SchoolStudent> list = schoolStudentService.list(query.select("stu_id", "stu_name"));
        return ResultUtils.success("查询成功", list);
    }
}
