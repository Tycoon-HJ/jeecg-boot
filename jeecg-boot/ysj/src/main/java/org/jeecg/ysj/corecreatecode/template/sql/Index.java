package org.jeecg.ysj.corecreatecode.template.sql;

import java.util.Set;

public class Index {
    private String indexName;
    private String indexType;
    private Set<String> columns;

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public Set<String> getColumns() {
        return columns;
    }

    public void setColumns(Set<String> columns) {
        this.columns = columns;
    }
}
