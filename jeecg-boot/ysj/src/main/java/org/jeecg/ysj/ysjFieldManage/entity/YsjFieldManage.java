package org.jeecg.ysj.ysjFieldManage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 字段管理
 * @Author: jeecg-boot
 * @Date:   2024-12-29
 * @Version: V1.0
 */
@Data
@TableName("ysj_field_manage")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description="字段管理")
public class YsjFieldManage implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键")
    private java.lang.String id;
	/**字段值*/
	@Excel(name = "字段值", width = 15)
    @Schema(description = "字段值")
    private java.lang.String ysjField;
	/**字段中文名称*/
	@Excel(name = "字段中文名称", width = 15)
    @Schema(description = "字段中文名称")
    private java.lang.String ysjFieldName;
	/**字段英文名称*/
	@Excel(name = "字段英文名称", width = 15)
    @Schema(description = "字段英文名称")
    private java.lang.String ysjFieldEnName;
	/**字段长度*/
	@Excel(name = "字段长度", width = 15)
    @Schema(description = "字段长度")
    private java.lang.Integer ysjFieldLength;
	/**字段类型*/
	@Excel(name = "字段类型", width = 15, dicCode = "filed_type")
	@Dict(dicCode = "filed_type")
    @Schema(description = "字段类型")
    private java.lang.String ysjFieldType;
	/**字典*/
	@Excel(name = "字典", width = 15, dicCode = "yn")
	@Dict(dicCode = "yn")
    @Schema(description = "字典")
    private java.lang.String ysjDict;
	/**字典编码*/
	@Excel(name = "字典编码", width = 15, dictTable = "jimu_dict", dicText = "dict_name", dicCode = "dict_code")
	@Dict(dictTable = "jimu_dict", dicText = "dict_name", dicCode = "dict_code")
    @Schema(description = "字典编码")
    private java.lang.String ysjDictCode;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @Schema(description = "备注")
    private java.lang.String remark;
	/**创建人*/
    @Schema(description = "创建人")
    private java.lang.String createBy;
	/**更新人*/
    @Schema(description = "更新人")
    private java.lang.String updateBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建日期")
    private java.util.Date createTime;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @Schema(description = "所属部门")
    private java.lang.String sysOrgCode;
}
