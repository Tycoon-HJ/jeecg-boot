package org.jeecg.ysj.ysjObjFieldManage.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.ysj.ysjObjFieldManage.entity.YsjObjFieldManage;
import org.jeecg.ysj.ysjObjFieldManage.mapper.YsjObjFieldManageMapper;
import org.jeecg.ysj.ysjObjFieldManage.service.IYsjObjFieldManageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Description: 对象字段管理
 * @Author: jeecg-boot
 * @Date:   2025-01-03
 * @Version: V1.0
 */
@Service
public class YsjObjFieldManageServiceImpl extends ServiceImpl<YsjObjFieldManageMapper, YsjObjFieldManage> implements IYsjObjFieldManageService {

    @Resource
    private YsjObjFieldManageMapper ysjObjFieldManageMapper;

    /**
     * 更新null值
     *
     * @param ysjObjFieldManage
     */
    public void updateWithNull(YsjObjFieldManage ysjObjFieldManage) {
        LambdaUpdateWrapper<YsjObjFieldManage> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(YsjObjFieldManage::getYsjObj, ysjObjFieldManage.getYsjObj());
        updateWrapper.set(YsjObjFieldManage::getYsjField, ysjObjFieldManage.getYsjField());
        updateWrapper.set(YsjObjFieldManage::getYsjInnerObj, ysjObjFieldManage.getYsjInnerObj());
        updateWrapper.set(YsjObjFieldManage::getUpdateBy, ((LoginUser) SecurityUtils.getSubject().getPrincipal()).getUsername());
        updateWrapper.set(YsjObjFieldManage::getUpdateTime, LocalDateTime.now());
        updateWrapper.eq(YsjObjFieldManage::getId, ysjObjFieldManage.getId());
        ysjObjFieldManageMapper.update(null, updateWrapper);
    }

}
