package org.jeecg.ysj.ysjObjFieldManage.entity;

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

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 对象字段管理
 * @Author: jeecg-boot
 * @Date:   2025-01-03
 * @Version: V1.0
 */
@Data
@TableName("ysj_obj_field_manage")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description="对象字段管理")
public class YsjObjFieldManage implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "主键")
    private String id;
	/**创建人*/
    @Schema(description = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建日期")
    private Date createTime;
	/**更新人*/
    @Schema(description = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新日期")
    private Date updateTime;
	/**所属部门*/
    @Schema(description = "所属部门")
    private String sysOrgCode;
	/**对象*/
	@Excel(name = "对象", width = 15, dictTable = "ysj_obj_manage", dicText = "ysj_obj", dicCode = "ysj_obj")
	@Dict(dictTable = "ysj_obj_manage", dicText = "ysj_obj", dicCode = "ysj_obj")
    @Schema(description = "对象")
    private String ysjObj;
	/**字段*/
	@Excel(name = "字段", width = 15, dictTable = "ysj_field_manage", dicText = "ysj_field", dicCode = "ysj_field")
	@Dict(dictTable = "ysj_field_manage", dicText = "ysj_field", dicCode = "ysj_field")
    @Schema(description = "字段")
    private String ysjField;
	/**对象中的对象*/
	@Excel(name = "对象中的对象", width = 15, dictTable = "ysj_obj_manage", dicText = "ysj_obj", dicCode = "ysj_obj")
	@Dict(dictTable = "ysj_obj_manage", dicText = "ysj_obj", dicCode = "ysj_obj")
    @Schema(description = "对象中的对象")
    private String ysjInnerObj;
	/**字段基数*/
	@Excel(name = "字段基数", width = 15, dicCode = "filed_base_num")
	@Dict(dicCode = "filed_base_num")
    @Schema(description = "字段基数")
    private String ysjFieldBaseNum;
}
