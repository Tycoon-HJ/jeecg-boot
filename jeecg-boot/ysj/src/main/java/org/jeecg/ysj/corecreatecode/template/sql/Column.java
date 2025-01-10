package org.jeecg.ysj.corecreatecode.template.sql;

public class Column {
    private String field;
    private String fieldType;
    private String fieldLen;
    private String noNull;
    private String fieldName;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldLen() {
        return fieldLen;
    }

    public void setFieldLen(String fieldLen) {
        this.fieldLen = fieldLen;
    }

    public String getNoNull() {
        return noNull;
    }

    public void setNoNull(String noNull) {
        this.noNull = noNull;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
