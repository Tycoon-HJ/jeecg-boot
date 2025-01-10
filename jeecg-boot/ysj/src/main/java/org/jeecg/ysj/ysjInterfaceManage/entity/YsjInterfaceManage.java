package org.jeecg.ysj.ysjInterfaceManage.entity;

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
 * @Description: 接口管理
 * @Author: jeecg-boot
 * @Date: 2025-01-09
 * @Version: V1.0
 */
@Data
@TableName("ysj_interface_manage")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(description="接口管理")
public class YsjInterfaceManage implements Serializable {
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
	/**接口地址*/
	@Excel(name = "接口地址", width = 15)
    @Schema(description = "接口地址")
    private String ysjInterfaceUrl;
	/**接口名称*/
	@Excel(name = "接口名称", width = 15)
    @Schema(description = "接口名称")
    private String ysjInterfaceName;
    /**
     * 接口对象
     */
    @Excel(name = "接口对象", width = 15)
    @Schema(description = "接口对象")
    private String ysjInterfaceObj;
	/**接口类型*/
	@Excel(name = "接口类型", width = 15, dicCode = "interface_type")
	@Dict(dicCode = "interface_type")
    @Schema(description = "接口类型")
    private String ysjInterfaceType;
	/**接口入参*/
	@Excel(name = "接口入参", width = 15, dictTable = "ysj_obj_manage", dicText = "ysj_obj", dicCode = "ysj_obj")
	@Dict(dictTable = "ysj_obj_manage", dicText = "ysj_obj", dicCode = "ysj_obj")
    @Schema(description = "接口入参")
    private String ysjInterfaceInParam;
	/**接口出参*/
	@Excel(name = "接口出参", width = 15, dictTable = "ysj_obj_manage", dicText = "ysj_obj", dicCode = "ysj_obj")
	@Dict(dictTable = "ysj_obj_manage", dicText = "ysj_obj", dicCode = "ysj_obj")
    @Schema(description = "接口出参")
    private String ysjInterfaceOutParam;
}
