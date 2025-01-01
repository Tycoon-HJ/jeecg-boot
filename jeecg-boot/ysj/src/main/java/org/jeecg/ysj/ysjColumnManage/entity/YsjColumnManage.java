package org.jeecg.ysj.ysjColumnManage.entity;

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
import java.util.Date;

/**
 * @Description: 列管理
 * @Author: jeecg-boot
 * @Date:   2025-01-01
 * @Version: V1.0
 */
@Data
@TableName("ysj_column_manage")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description="列管理")
public class YsjColumnManage implements Serializable {
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
	/**列值*/
	@Excel(name = "列值", width = 15, dictTable = "ysj_field_manage", dicText = "ysj_field", dicCode = "ysj_field")
	@Dict(dictTable = "ysj_field_manage", dicText = "ysj_field", dicCode = "ysj_field")
    @Schema(description = "列值")
    private String ysjField;
	/**列是否必填*/
	@Excel(name = "列是否必填", width = 15, dicCode = "yn")
	@Dict(dicCode = "yn")
    @Schema(description = "列是否必填")
    private String ysjColumnNoNull;
	/**列中的值是否递增*/
	@Excel(name = "列中的值是否递增", width = 15, dicCode = "yn")
	@Dict(dicCode = "yn")
    @Schema(description = "列中的值是否递增")
    private String ysjColumnIncr;
	/**列所在的表*/
	@Excel(name = "列所在的表", width = 15, dictTable = "ysj_tb_manage", dicText = "ysj_tb_name", dicCode = "ysj_tb_name")
	@Dict(dictTable = "ysj_tb_manage", dicText = "ysj_tb_name", dicCode = "ysj_tb_name")
    @Schema(description = "列所在的表")
    private String ysjTbName;
}
