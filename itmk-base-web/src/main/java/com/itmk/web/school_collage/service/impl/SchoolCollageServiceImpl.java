package com.itmk.web.school_collage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.school_collage.entity.ListParm;
import com.itmk.web.school_collage.entity.SchoolCollage;
import com.itmk.web.school_collage.mapper.SchoolCollageMapper;
import com.itmk.web.school_collage.service.SchoolCollageService;
import com.itmk.web.school_student.entity.SchoolStudent;
import org.springframework.stereotype.Service;


@Service
public class SchoolCollageServiceImpl extends ServiceImpl<SchoolCollageMapper, SchoolCollage> implements SchoolCollageService {

}
