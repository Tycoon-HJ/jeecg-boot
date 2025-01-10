package org.jeecg.common.util;

import java.util.Objects;

public class YsjCommonUtil {

    /**
     * 转换字符串
     *
     * @param fieldType
     * @return
     */
    public static String convertFieldTypeToStr(String fieldType) {
        if (Objects.equals(fieldType, "0")) {
            return "varchar";
        }
        if (Objects.equals(fieldType, "1")) {
            return "NUMERIC";
        }
        if (Objects.equals(fieldType, "2")) {
            return "datetime";
        }
        if (Objects.equals(fieldType, "3")) {
            return "DATE";
        }
        if (Objects.equals(fieldType, "4")) {
            return "BLOB";
        }
        return "varchar";
    }
}
