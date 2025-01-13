package org.jeecg.ysj.ysjIndexManage.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.ysj.ysjIndexManage.entity.YsjIndexManage;
import org.jeecg.ysj.ysjIndexManage.mapper.YsjIndexManageMapper;
import org.jeecg.ysj.ysjIndexManage.service.IYsjIndexManageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Description: 索引管理
 * @Author: jeecg-boot
 * @Date: 2025-01-01
 * @Version: V1.0
 */
@Service
public class YsjIndexManageServiceImpl extends ServiceImpl<YsjIndexManageMapper, YsjIndexManage> implements IYsjIndexManageService {

    @Resource
    private YsjIndexManageMapper ysjIndexManageMapper;

    @Override
    public void updateWithNull(YsjIndexManage ysjIndexManage) {
        LambdaUpdateWrapper<YsjIndexManage> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(YsjIndexManage::getYsjIndexName, ysjIndexManage.getYsjIndexName());
        updateWrapper.set(YsjIndexManage::getYsjTbName, ysjIndexManage.getYsjTbName());
        updateWrapper.set(YsjIndexManage::getYsjIndexType, ysjIndexManage.getYsjIndexType());
        updateWrapper.set(YsjIndexManage::getYsjIndexFieldList, ysjIndexManage.getYsjIndexFieldList());
        updateWrapper.set(YsjIndexManage::getUpdateBy, ((LoginUser) SecurityUtils.getSubject().getPrincipal()).getUsername());
        updateWrapper.set(YsjIndexManage::getUpdateTime, LocalDateTime.now());
        updateWrapper.eq(YsjIndexManage::getId, ysjIndexManage.getId());
        ysjIndexManageMapper.update(null, updateWrapper);
    }
}
