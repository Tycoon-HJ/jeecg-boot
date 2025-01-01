package org.jeecg.ysj.ysjTbManage.entity;

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
 * @Description: 表管理
 * @Author: jeecg-boot
 * @Date:   2025-01-01
 * @Version: V1.0
 */
@Data
@TableName("ysj_tb_manage")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description="表管理")
public class YsjTbManage implements Serializable {
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
	/**表名称*/
	@Excel(name = "表名称", width = 15)
    @Schema(description = "表名称")
    private String ysjTbName;
	/**表中文名*/
	@Excel(name = "表中文名", width = 15)
    @Schema(description = "表中文名")
    private String ysjTbCname;
	/**表的主键*/
	@Excel(name = "表的主键", width = 15, dictTable = "ysj_field_manage", dicText = "ysj_field", dicCode = "ysj_field")
	@Dict(dictTable = "ysj_field_manage", dicText = "ysj_field", dicCode = "ysj_field")
    @Schema(description = "表的主键")
    private String ysjTbPrimary;
}
