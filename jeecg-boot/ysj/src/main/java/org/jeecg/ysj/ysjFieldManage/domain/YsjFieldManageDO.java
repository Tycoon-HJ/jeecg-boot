package org.jeecg.ysj.ysjFieldManage.domain;

import org.jeecg.common.exception.JeecgBootBizTipException;
import org.jeecg.ysj.ysjFieldManage.entity.YsjFieldManage;

import java.util.Objects;

public class YsjFieldManageDO {

    public void checkDict(YsjFieldManage ysjFieldManage) {
        if (Objects.equals(ysjFieldManage.getYsjDict(), "1")) {
            if (ysjFieldManage.getYsjDictCode() == null) {
                throw new JeecgBootBizTipException("【字典】为是，则【字典编码】为必填");
            }
        }

        if (Objects.equals(ysjFieldManage.getYsjDict(), "0")) {
            if (ysjFieldManage.getYsjDictCode() != null) {
                throw new JeecgBootBizTipException("【字典】为否，则【字典编码】不能有值");
            }
        }
    }
}
