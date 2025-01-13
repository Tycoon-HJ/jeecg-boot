package org.jeecg.ysj.ysjFieldManage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.ysj.ysjFieldManage.entity.YsjFieldManage;

/**
 * @Description: 字段管理
 * @Author: jeecg-boot
 * @Date:   2025-01-01
 * @Version: V1.0
 */
public interface IYsjFieldManageService extends IService<YsjFieldManage> {
    void updateWithNull(YsjFieldManage ysjFieldManage);
}
