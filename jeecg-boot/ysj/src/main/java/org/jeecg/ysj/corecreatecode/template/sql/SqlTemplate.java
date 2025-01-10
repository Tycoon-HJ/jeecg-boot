package org.jeecg.ysj.corecreatecode.template.sql;

import java.util.Set;

public class SqlTemplate {
    private String tableName;
    private Set<Column> columnSet;
    private Set<Index> indexSet;
    private Set<Index> primaryIndexSet;
    private Set<Index> constraintIndexSet;
    private Set<Index> uniqueIndexSet;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Set<Column> getColumnSet() {
        return columnSet;
    }

    public void setColumnSet(Set<Column> columnSet) {
        this.columnSet = columnSet;
    }

    public Set<Index> getIndexSet() {
        return indexSet;
    }

    public void setIndexSet(Set<Index> indexSet) {
        this.indexSet = indexSet;
    }

    public Set<Index> getPrimaryIndexSet() {
        return primaryIndexSet;
    }

    public void setPrimaryIndexSet(Set<Index> primaryIndexSet) {
        this.primaryIndexSet = primaryIndexSet;
    }

    public Set<Index> getConstraintIndexSet() {
        return constraintIndexSet;
    }

    public void setConstraintIndexSet(Set<Index> constraintIndexSet) {
        this.constraintIndexSet = constraintIndexSet;
    }

    public Set<Index> getUniqueIndexSet() {
        return uniqueIndexSet;
    }

    public void setUniqueIndexSet(Set<Index> uniqueIndexSet) {
        this.uniqueIndexSet = uniqueIndexSet;
    }
}
