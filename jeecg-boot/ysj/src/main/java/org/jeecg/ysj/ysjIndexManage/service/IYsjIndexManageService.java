package org.jeecg.ysj.ysjIndexManage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.ysj.ysjIndexManage.entity.YsjIndexManage;

/**
 * @Description: 索引管理
 * @Author: jeecg-boot
 * @Date: 2025-01-01
 * @Version: V1.0
 */
public interface IYsjIndexManageService extends IService<YsjIndexManage> {
    void updateWithNull(YsjIndexManage ysjIndexManage);
}
