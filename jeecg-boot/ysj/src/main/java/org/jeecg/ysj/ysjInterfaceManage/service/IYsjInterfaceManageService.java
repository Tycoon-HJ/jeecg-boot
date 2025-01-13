package org.jeecg.ysj.ysjInterfaceManage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.ysj.ysjInterfaceManage.entity.YsjInterfaceManage;

/**
 * @Description: 接口管理
 * @Author: jeecg-boot
 * @Date: 2025-01-09
 * @Version: V1.0
 */
public interface IYsjInterfaceManageService extends IService<YsjInterfaceManage> {
    void updateWithNull(YsjInterfaceManage ysjInterfaceManage);
}
