package org.jeecg.ysj.corecreatecode.template.entity;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class EntityTemplate {
    // 包名
    private final String pkg;
    // 类名
    private final String entityName;
    // 属性集合  需要改写 equals hash 保证名字可不重复 类型可重复
    private final Set<Field> fields = new LinkedHashSet<>();
    // 导入类的不重复集合
    private final Set<String> imports = new LinkedHashSet<>();


    public EntityTemplate(String entityName, String pkg) {
        this.entityName = entityName;
        this.pkg = pkg;
    }

    public void addField(Class<?> type, String fieldName, String fieldBaseNum) {
        // 处理 java.lang
        final String pattern = "java.lang";
        String fieldType = type.getName();
        if (!fieldType.startsWith(pattern)) {
            // 处理导包
            imports.add(fieldType);
        }
        this.fillField(fieldType, fieldName, fieldBaseNum);
    }

    /**
     * 创建DTO中的类型为自建对象时，为避免无法使用Class，使用字符串进行设值
     *
     * @param fieldType
     * @param fieldName
     * @param fieldBaseNum
     */
    public void addField(String fieldType, String fieldName, String fieldBaseNum) {
        fieldName = StringUtils.uncapitalize(fieldName);
        imports.add(fieldType);
        this.fillField(fieldType, fieldName, fieldBaseNum);
    }

    public String getPkg() {
        return pkg;
    }


    public String getEntityName() {
        return entityName;
    }


    public Set<Field> getFields() {
        return fields;
    }

    public Set<String> getImports() {
        return imports;
    }

    private void fillField(String fieldType, String fieldName, String fieldBaseNum) {
        Field field = new Field();
        // 处理成员属性的格式
        int i = fieldType.lastIndexOf(".");
        if (Objects.equals(fieldBaseNum, "2") || Objects.equals(fieldBaseNum, "3")) {
            imports.add("java.util.List");
            field.setFieldType("List<" + fieldType.substring(i + 1) + ">");
            field.setFieldName(fieldName + "List");
            field.setFieldSetName("set" + StringUtils.capitalize(fieldName) + "List");
            field.setFieldGetName("get" + StringUtils.capitalize(fieldName) + "List");
            fields.add(field);
            return;
        }
        field.setFieldType(fieldType.substring(i + 1));
        field.setFieldName(fieldName);
        field.setFieldSetName("set" + StringUtils.capitalize(fieldName));
        field.setFieldGetName("get" + StringUtils.capitalize(fieldName));
        fields.add(field);
    }

    /**
     * 成员属性封装对象.
     */
    @Data
    public static class Field {
        // 成员属性类型
        private String fieldType;
        // 成员属性名称
        private String fieldName;
        private String fieldSetName;
        private String fieldGetName;
        /**
         * 一个类的成员属性 一个名称只能出现一次
         * 我们可以通过覆写equals hash 方法 然后放入Set
         *
         * @param o 另一个成员属性
         * @return 比较结果
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Field field = (Field) o;
            return Objects.equals(fieldName, field.fieldName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(fieldType, fieldName);
        }
    }
}
