package org.jeecg.ysj.ysjIndexManage.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import org.jeecg.common.constant.ProvinceCityArea;
import org.jeecg.common.util.SpringContextUtils;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 索引管理
 * @Author: jeecg-boot
 * @Date:   2025-01-01
 * @Version: V1.0
 */
@Data
@TableName("ysj_index_manage")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description="索引管理")
public class YsjIndexManage implements Serializable {
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
	/**索引名称*/
	@Excel(name = "索引名称", width = 15)
    @Schema(description = "索引名称")
    private String ysjIndexName;
	/**表名*/
	@Excel(name = "表名", width = 15, dictTable = "ysj_tb_manage", dicText = "ysj_tb_name", dicCode = "ysj_tb_name")
	@Dict(dictTable = "ysj_tb_manage", dicText = "ysj_tb_name", dicCode = "ysj_tb_name")
    @Schema(description = "表名")
    private String ysjTbName;
	/**索引类型*/
	@Excel(name = "索引类型", width = 15, dicCode = "index_type")
	@Dict(dicCode = "index_type")
    @Schema(description = "索引类型")
    private String ysjIndexType;
	/**索引字段*/
	@Excel(name = "索引字段", width = 15, dictTable = "ysj_field_manage", dicText = "ysj_field", dicCode = "ysj_field")
	@Dict(dictTable = "ysj_field_manage", dicText = "ysj_field", dicCode = "ysj_field")
    @Schema(description = "索引字段")
    private String ysjIndexFieldList;
}
