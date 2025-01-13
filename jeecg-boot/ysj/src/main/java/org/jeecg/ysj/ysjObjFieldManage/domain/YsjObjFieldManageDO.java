package org.jeecg.ysj.ysjObjFieldManage.domain;

import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.exception.JeecgBootBizTipException;
import org.jeecg.ysj.ysjObjFieldManage.entity.YsjObjFieldManage;

public class YsjObjFieldManageDO {

    /**
     * 校验YsjFieldAndYsjInnerObj
     *
     * @param ysjObjFieldManage
     */
    public void checkYsjFieldAndYsjInnerObj(YsjObjFieldManage ysjObjFieldManage) {
        // 添加逻辑校验
        if (StringUtils.isNoneEmpty(ysjObjFieldManage.getYsjField()) && StringUtils.isNoneEmpty(ysjObjFieldManage.getYsjInnerObj())) {
            // 只能填充一个字段或者对象，否则抛出异常
            throw new JeecgBootBizTipException("添加失败，【字段】与【对象中的对象】不能同时有值");
        }
    }
}
