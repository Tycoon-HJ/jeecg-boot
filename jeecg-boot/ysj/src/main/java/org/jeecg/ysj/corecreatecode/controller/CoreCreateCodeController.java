package org.jeecg.ysj.corecreatecode.controller;

import jakarta.annotation.Resource;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.ysj.corecreatecode.service.CoreCrateCodeService;
import org.jeecg.ysj.corecreatecode.template.entity.EntityTemplate;
import org.jeecg.ysj.ysjObjManage.entity.YsjObjManage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RequestMapping("/ysjTbManage/ysjTbManage")
@RestController
public class CoreCreateCodeController {
    @Resource
    private CoreCrateCodeService coreCrateCodeService;

    @GetMapping("/autoCrateSql")
    @RequiresPermissions("ysjTbManage:ysj_tb_manage:autoCrateSql")
    public Result<String> coreCreateSql(@RequestParam(name = "id", required = true) String id) throws Exception {
        // 路径根据自己项目的特点调整
        String targetSqlPath = "/Users/mr.ahai/IdeaProjects/JeecgBoot/jeecg-boot/ysj/src/main/java/org/jeecg/ysj/corecreatecode/service/a.sql";
        String templatePath = "/Users/mr.ahai/IdeaProjects/JeecgBoot/jeecg-boot/ysj/src/main/resources/template";
        String templateName = "AutoSql.ftl";
        return Result.ok(coreCrateCodeService.crateTable(id, templatePath, templateName, targetSqlPath));
    }

    @GetMapping("/xx")
    public void coreCreateJavaCode() throws Exception {
        // 路径根据自己项目的特点调整
        String targetSqlPath = "/Users/mr.ahai/IdeaProjects/JeecgBoot/jeecg-boot/ysj/src/main/java/org/jeecg/ysj/corecreatecode/service/InterfaceDemo.java";
        String templatePath = "/Users/mr.ahai/IdeaProjects/JeecgBoot/jeecg-boot/ysj/src/main/resources/template";
        String interfaceTemplateName = "InterFace.ftl";
        String entityTemplateName = "InOutDTOTemplate.ftl";
        String packageName = "org.jeecg.ysj.corecreatecode.service";
        String url = "/api/coll/mgn/pagecollparam";
        String rootPath = "/Users/mr.ahai/IdeaProjects/JeecgBoot/jeecg-boot/ysj/src/main/java";
        Map<String, YsjObjManage> stringYsjObjManageMap = coreCrateCodeService.coreCreateJavaCode(url, targetSqlPath, templatePath, interfaceTemplateName, packageName);
        YsjObjManage inParamObj = stringYsjObjManageMap.get("in");
        YsjObjManage outParamObj = stringYsjObjManageMap.get("out");
        if (Objects.nonNull(inParamObj)) {
            EntityTemplate entityTemplate = new EntityTemplate(inParamObj.getYsjObj(), packageName);
            // InDTO代码生成
            coreCrateCodeService.crateEntityJavaCode(rootPath, templatePath, entityTemplateName, entityTemplate);
        }
        if (Objects.nonNull(outParamObj)) {
            EntityTemplate entityTemplate = new EntityTemplate(outParamObj.getYsjObj(), packageName);
            // OutDTO代码生成
            coreCrateCodeService.crateEntityJavaCode(rootPath, templatePath, entityTemplateName, entityTemplate);
        }

    }


}
