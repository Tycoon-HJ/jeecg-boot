package org.jeecg.ysj.ysjFieldManage.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.ysj.ysjFieldManage.entity.YsjFieldManage;
import org.jeecg.ysj.ysjFieldManage.mapper.YsjFieldManageMapper;
import org.jeecg.ysj.ysjFieldManage.service.IYsjFieldManageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Description: 字段管理
 * @Author: jeecg-boot
 * @Date:   2025-01-01
 * @Version: V1.0
 */
@Service
public class YsjFieldManageServiceImpl extends ServiceImpl<YsjFieldManageMapper, YsjFieldManage> implements IYsjFieldManageService {
    @Resource
    private YsjFieldManageMapper ysjFieldManageMapper;

    @Override
    public void updateWithNull(YsjFieldManage ysjFieldManage) {
        LambdaUpdateWrapper<YsjFieldManage> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(YsjFieldManage::getYsjField, ysjFieldManage.getYsjField());
        updateWrapper.set(YsjFieldManage::getYsjFieldName, ysjFieldManage.getYsjFieldName());
        updateWrapper.set(YsjFieldManage::getYsjFieldLength, ysjFieldManage.getYsjFieldLength());
        updateWrapper.set(YsjFieldManage::getYsjFieldType, ysjFieldManage.getYsjFieldType());
        updateWrapper.set(YsjFieldManage::getYsjDict, ysjFieldManage.getYsjDict());
        updateWrapper.set(YsjFieldManage::getYsjDictCode, ysjFieldManage.getYsjDictCode());
        updateWrapper.set(YsjFieldManage::getRemark, ysjFieldManage.getRemark());
        updateWrapper.set(YsjFieldManage::getUpdateBy, ((LoginUser) SecurityUtils.getSubject().getPrincipal()).getUsername());
        updateWrapper.set(YsjFieldManage::getUpdateTime, LocalDateTime.now());
        updateWrapper.eq(YsjFieldManage::getId, ysjFieldManage.getId());
        ysjFieldManageMapper.update(null, updateWrapper);
    }
}
