package org.jeecg.common.constant.enums;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 字段类型枚举
 */
public enum FieldTypeEnum {

    STRING("String", String.class, "0"),
    BIG_DECIMAL("BigDecimal", BigDecimal.class, "1"),
    LOCAL_DATE("LocalDate", LocalDate.class, "2"),
    LOCAL_DATE_TIME("LocalDateTime", LocalDateTime.class, "3");

    /**
     * 字段类型
     */
    private String fieldType;

    /**
     * 类对象
     */
    private Class<?> clazz;

    /**
     * key
     */
    private String key;

    FieldTypeEnum(String fieldType, Class<?> clazz, String key) {
        this.fieldType = fieldType;
        this.clazz = clazz;
        this.key = key;
    }

    public static Class<?> getClazz(String key) {
        for (FieldTypeEnum val : values()) {
            if (val.getKey().equals(key)) {
                return val.getClazz();
            }
        }
        return null;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
