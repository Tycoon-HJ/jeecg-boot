package ${pkg};

<#list  imports as impt>
    import ${impt};
</#list>

/**
* the ${entityName} type
* @author HaiJun.Yin
*/
public class ${entityName} {

<#list  fields as field>
    private ${field.fieldType}  ${field.fieldName};
</#list>

}