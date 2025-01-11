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
    private ${field.fieldType} ${field.fieldName};
</#list>

<#list  fields as field>
    public void ${field.fieldSetName}(${field.fieldType} ${field.fieldName}) {
    this.${field.fieldName} = ${field.fieldName};
    }

    public ${field.fieldType} ${field.fieldGetName}() {
    return ${field.fieldName};
    }

</#list>
}