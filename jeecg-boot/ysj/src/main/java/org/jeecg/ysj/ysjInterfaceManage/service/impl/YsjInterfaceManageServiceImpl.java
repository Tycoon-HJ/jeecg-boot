package org.jeecg.ysj.ysjInterfaceManage.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.ysj.ysjInterfaceManage.entity.YsjInterfaceManage;
import org.jeecg.ysj.ysjInterfaceManage.mapper.YsjInterfaceManageMapper;
import org.jeecg.ysj.ysjInterfaceManage.service.IYsjInterfaceManageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Description: 接口管理
 * @Author: jeecg-boot
 * @Date: 2025-01-09
 * @Version: V1.0
 */
@Service
public class YsjInterfaceManageServiceImpl extends ServiceImpl<YsjInterfaceManageMapper, YsjInterfaceManage> implements IYsjInterfaceManageService {
    @Resource
    private YsjInterfaceManageMapper ysjInterfaceManageMapper;

    @Override
    public void updateWithNull(YsjInterfaceManage ysjInterfaceManage) {
        LambdaUpdateWrapper<YsjInterfaceManage> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(YsjInterfaceManage::getYsjInterfaceUrl, ysjInterfaceManage.getYsjInterfaceUrl());
        updateWrapper.set(YsjInterfaceManage::getYsjInterfaceName, ysjInterfaceManage.getYsjInterfaceName());
        updateWrapper.set(YsjInterfaceManage::getYsjInterfaceObj, ysjInterfaceManage.getYsjInterfaceObj());
        updateWrapper.set(YsjInterfaceManage::getYsjInterfaceType, ysjInterfaceManage.getYsjInterfaceType());
        updateWrapper.set(YsjInterfaceManage::getYsjInterfaceInParam, ysjInterfaceManage.getYsjInterfaceInParam());
        updateWrapper.set(YsjInterfaceManage::getYsjInterfaceOutParam, ysjInterfaceManage.getYsjInterfaceOutParam());
        updateWrapper.set(YsjInterfaceManage::getUpdateBy, ((LoginUser) SecurityUtils.getSubject().getPrincipal()).getUsername());
        updateWrapper.set(YsjInterfaceManage::getUpdateTime, LocalDateTime.now());
        updateWrapper.eq(YsjInterfaceManage::getId, ysjInterfaceManage.getId());
        ysjInterfaceManageMapper.update(null, updateWrapper);
    }
}
