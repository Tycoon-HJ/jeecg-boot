package org.jeecg.ysj.corecreatecode.service;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.base.CaseFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.annotation.Resource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.common.constant.enums.FieldTypeEnum;
import org.jeecg.common.util.YsjCommonUtil;
import org.jeecg.ysj.corecreatecode.template.entity.EntityTemplate;
import org.jeecg.ysj.corecreatecode.template.interfaces.InterfaceTemplate;
import org.jeecg.ysj.corecreatecode.template.sql.Column;
import org.jeecg.ysj.corecreatecode.template.sql.Index;
import org.jeecg.ysj.corecreatecode.template.sql.SqlTemplate;
import org.jeecg.ysj.ysjColumnManage.entity.YsjColumnManage;
import org.jeecg.ysj.ysjColumnManage.service.IYsjColumnManageService;
import org.jeecg.ysj.ysjFieldManage.entity.YsjFieldManage;
import org.jeecg.ysj.ysjFieldManage.service.IYsjFieldManageService;
import org.jeecg.ysj.ysjIndexManage.entity.YsjIndexManage;
import org.jeecg.ysj.ysjIndexManage.service.IYsjIndexManageService;
import org.jeecg.ysj.ysjInterfaceManage.entity.YsjInterfaceManage;
import org.jeecg.ysj.ysjInterfaceManage.service.IYsjInterfaceManageService;
import org.jeecg.ysj.ysjObjFieldManage.entity.YsjObjFieldManage;
import org.jeecg.ysj.ysjObjFieldManage.service.IYsjObjFieldManageService;
import org.jeecg.ysj.ysjObjManage.entity.YsjObjManage;
import org.jeecg.ysj.ysjObjManage.service.IYsjObjManageService;
import org.jeecg.ysj.ysjTbManage.entity.YsjTbManage;
import org.jeecg.ysj.ysjTbManage.service.IYsjTbManageService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CoreCrateCodeService {

    @Resource
    private IYsjColumnManageService iYsjColumnManageService;
    @Resource
    private IYsjFieldManageService iYsjFieldManageService;
    @Resource
    private IYsjIndexManageService iYsjIndexManageService;
    @Resource
    private IYsjInterfaceManageService iYsjInterfaceManageService;
    @Resource
    private IYsjObjManageService iYsjObjManageService;
    @Resource
    private IYsjObjFieldManageService iYsjObjFieldManageService;
    @Resource
    private IYsjTbManageService iYsjTbManageService;

    /**
     * 自动生成sql
     *
     * @param templatePath
     * @param templateName
     * @param targetSqlPath
     * @param t
     * @throws Exception
     */
    public <T> String autoCrateDataTemplate(String templatePath, String templateName, String targetSqlPath, T t) throws Exception {
        String sql = "";
        // freemarker 配置
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("UTF-8");
        // 指定模板的路径
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        // 根据模板名称获取路径下的模板
        Template template = configuration.getTemplate(templateName);
        // 定义一个输出流来导出代码文件
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(targetSqlPath));
        // freemarker 引擎将动态数据绑定的模板并导出为文件
        template.process(t, outputStreamWriter);
        String source = FileUtils.readFileToString(new File(targetSqlPath), "UTF-8");
        // 格式化SQL代码
        if (t instanceof SqlTemplate) {
            sql = SQLUtils.format(source, JdbcConstants.MYSQL);
        }
        return sql;
    }

    /**
     * 创造表
     *
     * @param id
     * @param templatePath
     * @param templateName
     * @param targetSqlPath
     * @throws Exception
     */
    public String crateTable(String id, String templatePath, String templateName, String targetSqlPath) throws Exception {
        LambdaQueryWrapper<YsjTbManage> ysjTbManageLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ysjTbManageLambdaQueryWrapper.eq(YsjTbManage::getId, id);
        YsjTbManage ysjTbManage = iYsjTbManageService.getOne(ysjTbManageLambdaQueryWrapper);
        if (ysjTbManage == null) {
            return "";
        }
        String tableName = ysjTbManage.getYsjTbName();
        SqlTemplate sqlTemplate = new SqlTemplate();
        sqlTemplate.setTableName(tableName);
        // 这里必须要LinkedHashSet，否则会出错
        LambdaQueryWrapper<YsjColumnManage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(YsjColumnManage::getYsjTbName, tableName);
        // 获取表的所有列
        List<YsjColumnManage> ysjColumnManageList = iYsjColumnManageService.list(lambdaQueryWrapper);
        LambdaQueryWrapper<YsjIndexManage> ysjIndexManageLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ysjIndexManageLambdaQueryWrapper.eq(YsjIndexManage::getYsjTbName, tableName);
        // 表的索引
        List<YsjIndexManage> ysjIndexManageList = iYsjIndexManageService.list(ysjIndexManageLambdaQueryWrapper);
        Set<Index> indexSet = this.getIndexSet(ysjIndexManageList);
        Set<Column> columnSet = new LinkedHashSet<>();
        ysjColumnManageList.forEach(item -> {
            Column column = new Column();
            column.setField(item.getYsjField());
            LambdaQueryWrapper<YsjFieldManage> ysjFieldManageLambdaQueryWrapper = new LambdaQueryWrapper<>();
            ysjFieldManageLambdaQueryWrapper.eq(YsjFieldManage::getYsjField, item.getYsjField());
            // 获取字段
            YsjFieldManage ysjFieldManage = iYsjFieldManageService.getOne(ysjFieldManageLambdaQueryWrapper);
            column.setFieldType(YsjCommonUtil.convertFieldTypeToStr(ysjFieldManage.getYsjFieldType()));
            column.setFieldLen(String.valueOf(ysjFieldManage.getYsjFieldLength()));
            column.setNoNull(Objects.equals(item.getYsjColumnNoNull(), "1") ? "not null" : "null");
            column.setFieldName(ysjFieldManage.getYsjFieldName());
            columnSet.add(column);
        });
        sqlTemplate.setColumnSet(columnSet);
        sqlTemplate.setIndexSet(indexSet);
        // 主键索引
        sqlTemplate.setPrimaryIndexSet(indexSet.stream().filter(e -> Objects.equals(e.getIndexType(), "2")).collect(Collectors.toSet()));
        // 唯一索引
        sqlTemplate.setUniqueIndexSet(indexSet.stream().filter(e -> Objects.equals(e.getIndexType(), "1")).collect(Collectors.toSet()));
        // 一般索引
        sqlTemplate.setConstraintIndexSet(indexSet.stream().filter(e -> Objects.equals(e.getIndexType(), "0")).collect(Collectors.toSet()));
        return this.autoCrateDataTemplate(templatePath, templateName, targetSqlPath, sqlTemplate);
    }

    /**
     * 创建java代码
     *
     * @param url
     * @param targetSqlPath
     * @param templatePath
     * @param templateName
     * @param packageName
     * @return
     * @throws Exception
     */
    public Map<String, YsjObjManage> coreCreateJavaCode(String url, String targetSqlPath, String templatePath, String templateName, String packageName) throws Exception {

        Map<String, YsjObjManage> result = new HashMap<>();
        LambdaQueryWrapper<YsjInterfaceManage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(YsjInterfaceManage::getYsjInterfaceUrl, url);
        YsjInterfaceManage ysjInterfaceManage = iYsjInterfaceManageService.getOne(lambdaQueryWrapper);

        // 入参
        String ysjInterfaceInParam = ysjInterfaceManage.getYsjInterfaceInParam();
        // 出参
        String ysjInterfaceOutParam = ysjInterfaceManage.getYsjInterfaceOutParam();

        YsjObjManage ysjInObjManage = null;
        YsjObjManage ysjOutObjManage = null;
        // 若存在入参
        if (StringUtils.isNoneEmpty(ysjInterfaceInParam)) {
            LambdaQueryWrapper<YsjObjManage> ysjObjManageLambdaQueryWrapper = new LambdaQueryWrapper<>();
            ysjObjManageLambdaQueryWrapper.eq(YsjObjManage::getYsjObj, ysjInterfaceInParam);
            ysjInObjManage = iYsjObjManageService.getOne(ysjObjManageLambdaQueryWrapper);
        }
        // 若存在出参
        if (StringUtils.isNoneEmpty(ysjInterfaceOutParam)) {
            LambdaQueryWrapper<YsjObjManage> ysjObjManageLambdaQueryWrapper = new LambdaQueryWrapper<>();
            ysjObjManageLambdaQueryWrapper.eq(YsjObjManage::getYsjObj, ysjInterfaceOutParam);
            ysjOutObjManage = iYsjObjManageService.getOne(ysjObjManageLambdaQueryWrapper);
        }

        InterfaceTemplate interfaceTemplate = new InterfaceTemplate();
        interfaceTemplate.setPkg(packageName);
        interfaceTemplate.setUrl(url);
        interfaceTemplate.setInterfaceName(ysjInterfaceManage.getYsjInterfaceName());
        interfaceTemplate.setInterfaceObj(ysjInterfaceManage.getYsjInterfaceObj());
        interfaceTemplate.setInterfaceType(ysjInterfaceManage.getYsjInterfaceType());
        interfaceTemplate.setInputParam(ysjInterfaceInParam);
        interfaceTemplate.setInputParamObj(StringUtils.uncapitalize(ysjInterfaceInParam));
        if (Objects.equals(ysjInObjManage.getYsjObjBaseNum(), "3") || Objects.equals(ysjInObjManage.getYsjObjBaseNum(), "4")) {
            interfaceTemplate.setInputParam("List<" + ysjInterfaceInParam + ">");
            interfaceTemplate.setImports("java.util.List");
        }
        if (Objects.equals(ysjOutObjManage.getYsjObjBaseNum(), "3") || Objects.equals(ysjOutObjManage.getYsjObjBaseNum(), "4")) {
            interfaceTemplate.setOutputParam("List<" + ysjInterfaceInParam + ">");
            interfaceTemplate.setImports("java.util.List");
        }
        interfaceTemplate.setOutputParam(ysjInterfaceOutParam);
        interfaceTemplate.setInputParamBaseNum(ysjInObjManage.getYsjObjBaseNum());
        interfaceTemplate.setOutputParamBaseNum(ysjOutObjManage.getYsjObjBaseNum());
        this.autoCrateDataTemplate(templatePath, templateName, targetSqlPath, interfaceTemplate);
        result.put("in", ysjInObjManage);
        result.put("out", ysjOutObjManage);
        return result;
    }


    /**
     * 创建输入和输出DTO
     */
    public void crateEntityJavaCode(String rootPath, String templatePath, String templateName, EntityTemplate entityTemplate) throws Exception {

        // 对象类名
        String entityName = entityTemplate.getEntityName();
        LambdaQueryWrapper<YsjObjFieldManage> ysjObjManageLambdaQueryWrapper = new LambdaQueryWrapper<>();
        ysjObjManageLambdaQueryWrapper.eq(YsjObjFieldManage::getYsjObj, entityName);
        List<YsjObjFieldManage> ysjObjFieldManageList = iYsjObjFieldManageService.list(ysjObjManageLambdaQueryWrapper);
        ysjObjFieldManageList.forEach(e -> {
            // 替换String.class类型
            LambdaQueryWrapper<YsjFieldManage> ysjFieldManageLambdaQueryWrapper = new LambdaQueryWrapper<>();
            ysjFieldManageLambdaQueryWrapper.eq(YsjFieldManage::getYsjField, e.getYsjField());
            YsjFieldManage ysjFieldManage = iYsjFieldManageService.getOne(ysjFieldManageLambdaQueryWrapper);
            if (Objects.nonNull(e.getYsjField())) {
                entityTemplate.addField(Objects.requireNonNull(FieldTypeEnum.getClazz(ysjFieldManage.getYsjFieldType())), CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, e.getYsjField()), e.getYsjFieldBaseNum());
            } else {
                // 填充对象
                entityTemplate.addField(entityTemplate.getPkg() + "." + e.getYsjInnerObj(), e.getYsjInnerObj(), e.getYsjFieldBaseNum());
            }
        });
        // freemarker 配置
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

        configuration.setDefaultEncoding("UTF-8");
        // 指定模板的路径
        configuration.setDirectoryForTemplateLoading(new File(templatePath));
        // 根据模板名称获取路径下的模板
        Template template = configuration.getTemplate(templateName);
        // 处理路径问题
        final String ext = ".java";
        String javaName = entityTemplate.getEntityName().concat(ext);
        String packageName = entityTemplate.getPkg();

        String out = rootPath.concat(Stream.of(packageName.split("\\."))
                .collect(Collectors.joining("/", "/", "/" + javaName)));

        // 定义一个输出流来导出代码文件
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(out));
        // freemarker 引擎将动态数据绑定的模板并导出为文件
        template.process(entityTemplate, outputStreamWriter);
    }

    /**
     * 获取表的索引
     *
     * @param ysjIndexManageList
     * @return
     */
    @NotNull
    private Set<Index> getIndexSet(List<YsjIndexManage> ysjIndexManageList) {
        Set<Index> indexSet = new LinkedHashSet<>();
        for (YsjIndexManage ysjIndexManage : ysjIndexManageList) {
            Index index = new Index();
            index.setIndexName(ysjIndexManage.getYsjIndexName());
            index.setIndexType(ysjIndexManage.getYsjIndexType());
            String[] split = ysjIndexManage.getYsjIndexFieldList().split(",");
            Set<String> linkedHashSet = new LinkedHashSet<>(Arrays.asList(split));
            index.setColumns(linkedHashSet);
            indexSet.add(index);
        }
        return indexSet;
    }
}
