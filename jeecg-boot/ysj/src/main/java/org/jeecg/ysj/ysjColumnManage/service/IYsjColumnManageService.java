package org.jeecg.ysj.ysjColumnManage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.ysj.ysjColumnManage.entity.YsjColumnManage;

/**
 * @Description: 列管理
 * @Author: jeecg-boot
 * @Date: 2025-01-01
 * @Version: V1.0
 */
public interface IYsjColumnManageService extends IService<YsjColumnManage> {
    void updateWithNull(YsjColumnManage ysjColumnManage);
}
