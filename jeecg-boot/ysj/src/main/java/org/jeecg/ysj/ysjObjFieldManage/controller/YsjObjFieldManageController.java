package org.jeecg.ysj.ysjObjFieldManage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.query.QueryRuleEnum;
import org.jeecg.ysj.ysjObjFieldManage.entity.YsjObjFieldManage;
import org.jeecg.ysj.ysjObjFieldManage.service.IYsjObjFieldManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

 /**
 * @Description: 对象字段管理
 * @Author: jeecg-boot
 * @Date:   2025-01-03
 * @Version: V1.0
 */
@Tag(name="对象字段管理")
@RestController
@RequestMapping("/ysjObjFieldManage/ysjObjFieldManage")
@Slf4j
public class YsjObjFieldManageController extends JeecgController<YsjObjFieldManage, IYsjObjFieldManageService> {
	@Autowired
	private IYsjObjFieldManageService ysjObjFieldManageService;
	
	/**
	 * 分页列表查询
	 *
	 * @param ysjObjFieldManage
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "对象字段管理-分页列表查询")
	@Operation(summary="对象字段管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<YsjObjFieldManage>> queryPageList(YsjObjFieldManage ysjObjFieldManage,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        // 自定义查询规则
        Map<String, QueryRuleEnum> customeRuleMap = new HashMap<>();
        // 自定义多选的查询规则为：LIKE_WITH_OR
        customeRuleMap.put("ysjObj", QueryRuleEnum.LIKE_WITH_OR);
        customeRuleMap.put("ysjField", QueryRuleEnum.LIKE_WITH_OR);
        customeRuleMap.put("ysjInnerObj", QueryRuleEnum.LIKE_WITH_OR);
        QueryWrapper<YsjObjFieldManage> queryWrapper = QueryGenerator.initQueryWrapper(ysjObjFieldManage, req.getParameterMap(),customeRuleMap);
		Page<YsjObjFieldManage> page = new Page<YsjObjFieldManage>(pageNo, pageSize);
		IPage<YsjObjFieldManage> pageList = ysjObjFieldManageService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param ysjObjFieldManage
	 * @return
	 */
	@AutoLog(value = "对象字段管理-添加")
	@Operation(summary="对象字段管理-添加")
	@RequiresPermissions("ysjObjFieldManage:ysj_obj_field_manage:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody YsjObjFieldManage ysjObjFieldManage) {
		ysjObjFieldManageService.save(ysjObjFieldManage);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param ysjObjFieldManage
	 * @return
	 */
	@AutoLog(value = "对象字段管理-编辑")
	@Operation(summary="对象字段管理-编辑")
	@RequiresPermissions("ysjObjFieldManage:ysj_obj_field_manage:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody YsjObjFieldManage ysjObjFieldManage) {
		ysjObjFieldManageService.updateById(ysjObjFieldManage);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "对象字段管理-通过id删除")
	@Operation(summary="对象字段管理-通过id删除")
	@RequiresPermissions("ysjObjFieldManage:ysj_obj_field_manage:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		ysjObjFieldManageService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "对象字段管理-批量删除")
	@Operation(summary="对象字段管理-批量删除")
	@RequiresPermissions("ysjObjFieldManage:ysj_obj_field_manage:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ysjObjFieldManageService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "对象字段管理-通过id查询")
	@Operation(summary="对象字段管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<YsjObjFieldManage> queryById(@RequestParam(name="id",required=true) String id) {
		YsjObjFieldManage ysjObjFieldManage = ysjObjFieldManageService.getById(id);
		if(ysjObjFieldManage==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(ysjObjFieldManage);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param ysjObjFieldManage
    */
    @RequiresPermissions("ysjObjFieldManage:ysj_obj_field_manage:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, YsjObjFieldManage ysjObjFieldManage) {
        return super.exportXls(request, ysjObjFieldManage, YsjObjFieldManage.class, "对象字段管理");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("ysjObjFieldManage:ysj_obj_field_manage:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, YsjObjFieldManage.class);
    }

}
