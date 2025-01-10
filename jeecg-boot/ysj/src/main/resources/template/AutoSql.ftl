create table if not exists ${tableName}
(
<#list columnSet as column>
    ${column.field} <#if column.fieldType=="varchar">${column.fieldType}(${column.fieldLen})</#if><#if column.fieldType=="datetime">${column.fieldType}</#if><#if column.fieldType=="date">${column.fieldType}</#if><#if column.fieldType=="int">${column.fieldType}</#if> ${column.noNull} comment '${column.fieldName}'<#if column_has_next>,</#if>
</#list><#if indexSet??>,</#if>
<#list primaryIndexSet as index><#if index.indexType=="2"> primary key(<#list index.columns as primary>${primary}<#if primary_has_next>,</#if></#list>)<#if uniqueIndexSet??>,</#if></#if>
</#list>
<#list uniqueIndexSet as index><#if index.indexType=="1"> constraint ${index.indexName} unique (<#list index.columns as primary>${primary}<#if primary_has_next>,</#if></#list>)<#if index_has_next>,</#if></#if>
</#list>
);
<#list constraintIndexSet as index><#if index.indexType=="0"> create index ${index.indexName} on ${tableName} (<#list index.columns as primary>${primary}<#if primary_has_next>,</#if></#list>)<#if indexSet??>;</#if></#if>
</#list>


