package org.jeecg.ysj.ysjColumnManage.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.ysj.ysjColumnManage.entity.YsjColumnManage;
import org.jeecg.ysj.ysjColumnManage.mapper.YsjColumnManageMapper;
import org.jeecg.ysj.ysjColumnManage.service.IYsjColumnManageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Description: 列管理
 * @Author: jeecg-boot
 * @Date: 2025-01-01
 * @Version: V1.0
 */
@Service
public class YsjColumnManageServiceImpl extends ServiceImpl<YsjColumnManageMapper, YsjColumnManage> implements IYsjColumnManageService {
    @Resource
    private YsjColumnManageMapper ysjColumnManageMapper;

    @Override
    public void updateWithNull(YsjColumnManage ysjColumnManage) {
        LambdaUpdateWrapper<YsjColumnManage> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(YsjColumnManage::getYsjField, ysjColumnManage.getYsjField());
        updateWrapper.set(YsjColumnManage::getYsjTbName, ysjColumnManage.getYsjTbName());
        updateWrapper.set(YsjColumnManage::getYsjColumnIncr, ysjColumnManage.getYsjColumnIncr());
        updateWrapper.set(YsjColumnManage::getYsjColumnNoNull, ysjColumnManage.getYsjColumnNoNull());
        updateWrapper.set(YsjColumnManage::getUpdateBy, ((LoginUser) SecurityUtils.getSubject().getPrincipal()).getUsername());
        updateWrapper.set(YsjColumnManage::getUpdateTime, LocalDateTime.now());
        updateWrapper.eq(YsjColumnManage::getId, ysjColumnManage.getId());
        ysjColumnManageMapper.update(null, updateWrapper);
    }
}
