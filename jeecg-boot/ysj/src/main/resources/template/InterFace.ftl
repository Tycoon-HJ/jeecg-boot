package ${pkg};
import org.springframework.web.bind.annotation.*;

public class InterfaceDemo {

@PostMapping("${url}")
public ${outputParam} ${interfaceObj}(@RequestBody ${inputParam} ${inputParamObj}) {
<#if outputParam==""><#else> return null; </#if>
}
}