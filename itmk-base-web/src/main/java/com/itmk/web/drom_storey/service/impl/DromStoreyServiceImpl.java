package com.itmk.web.drom_storey.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.exception_advice.BusinessException;
import com.itmk.web.dorm_room.entity.DormRoom;
import com.itmk.web.dorm_room.service.DormRoomService;
import com.itmk.web.drom_build.entity.DromBuild;
import com.itmk.web.drom_build.service.DromBuildService;
import com.itmk.web.drom_room_bed.entity.DromRoomBed;
import com.itmk.web.drom_room_bed.service.DromRoomBedService;
import com.itmk.web.drom_storey.entity.DromStorey;
import com.itmk.web.drom_storey.mapper.DromStoreyMapper;
import com.itmk.web.drom_storey.service.DromStoreyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class DromStoreyServiceImpl extends ServiceImpl<DromStoreyMapper, DromStorey> implements DromStoreyService {
    @Autowired
    private DromBuildService dromBuildService;
    @Autowired
    private DormRoomService dormRoomService;
    @Autowired
    private DromRoomBedService dromRoomBedService;

    @Override
    public void addDrom(List<DromStorey> list) {
        this.baseMapper.addDrom(list);
    }

    @Override
    @Transactional
    public void add(DromStorey dromStorey) {
        int insert = this.baseMapper.insert(dromStorey);
        if (insert > 0) {
            DromBuild build = dromBuildService.getById(dromStorey.getBuildId());
            DromBuild newBuild = new DromBuild();
            newBuild.setBuildId(build.getBuildId());
            newBuild.setBuildStorey(build.getBuildStorey() + 1L);
            dromBuildService.updateById(newBuild);
        }
    }

    @Override
    @Transactional
    public void eidt(DromStorey dromStorey) {
        this.baseMapper.updateById(dromStorey);
//        if(insert >0){
//            DromBuild build = dromBuildService.getById(dromStorey.getBuildId());
//            DromBuild newBuild = new DromBuild();
//            newBuild.setBuildId(build.getBuildId());
//            newBuild.setBuildStorey(build.getBuildStorey() - 1L);
//            dromBuildService.updateById(newBuild);
//        }
    }

    @Override
    @Transactional
    public void delete(Long buildId, Long storeyId) {
        int insert = this.baseMapper.deleteById(storeyId);
        if (insert > 0) {
            DromBuild build = dromBuildService.getById(buildId);
            DromBuild newBuild = new DromBuild();
            newBuild.setBuildId(build.getBuildId());
            newBuild.setBuildStorey(build.getBuildStorey() - 1L);
            dromBuildService.updateById(newBuild);
        }
    }

    @Override
    public void initBed(DromStorey dromStorey) {
        //????????????????????????????????????
        QueryWrapper<DormRoom> query = new QueryWrapper<>();
        query.lambda().eq(DormRoom::getStoreyId, dromStorey.getStoreyId());
        List<DormRoom> list = dormRoomService.list(query);
        if (list.size() == 0) throw new BusinessException(500, "????????????????????????");
        //???????????????????????????
        List<DromRoomBed> bedList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            //?????????????????????
            int bedTotal = list.get(i).getTotalBed();
            for (int j = 1; j <= bedTotal; j++) {
                //??????????????????????????????
                QueryWrapper<DromRoomBed> queryBed = new QueryWrapper<>();
                queryBed.lambda().eq(DromRoomBed::getRoomId, list.get(i).getRoomId())
                        .eq(DromRoomBed::getBedCode, list.get(i).getRoomCode() + "-" + j); //A102-1
                DromRoomBed one = dromRoomBedService.getOne(queryBed);
                if (one == null) { //???????????????????????????
                    DromRoomBed bed = new DromRoomBed();
                    bed.setRoomId(list.get(i).getRoomId());
                    bed.setBedCode(list.get(i).getRoomCode() + "-" + j);
                    bedList.add(bed);
                }
            }
        }
        //???????????????
        if (bedList.size() > 0) {
            dromRoomBedService.saveBatch(bedList);
        }
    }
}
