package ${pkg};
import org.springframework.web.bind.annotation.*;

<#list  imports as impt>
    import ${impt};
</#list>
@RestController
public class InterfaceDemo {

@PostMapping("${url}")
public ${outputParam} ${interfaceObj}(@RequestBody ${inputParam} ${inputParamObj}) {
<#if outputParam==""><#else> return null; </#if>
}
}