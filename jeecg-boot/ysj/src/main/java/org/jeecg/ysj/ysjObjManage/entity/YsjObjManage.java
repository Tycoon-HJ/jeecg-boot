package org.jeecg.ysj.ysjObjManage.entity;

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
 * @Description: 对象管理
 * @Author: jeecg-boot
 * @Date:   2025-01-03
 * @Version: V1.0
 */
@Data
@TableName("ysj_obj_manage")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description="对象管理")
public class YsjObjManage implements Serializable {
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
	@Excel(name = "对象", width = 15)
    @Schema(description = "对象")
    private String ysjObj;
	/**对象名称*/
	@Excel(name = "对象名称", width = 15)
    @Schema(description = "对象名称")
    private String ysjObjName;
	/**对象方向*/
	@Excel(name = "对象方向", width = 15, dicCode = "obj_direction")
	@Dict(dicCode = "obj_direction")
    @Schema(description = "对象方向")
    private String ysjObjDirection;
	/**对象基数*/
	@Excel(name = "对象基数", width = 15, dicCode = "obj_base_num")
	@Dict(dicCode = "obj_base_num")
    @Schema(description = "对象基数")
    private String ysjObjBaseNum;
}
