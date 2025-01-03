package org.jeecg.ysj.ysjObjManage.controller;

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
import org.jeecg.ysj.ysjObjManage.entity.YsjObjManage;
import org.jeecg.ysj.ysjObjManage.service.IYsjObjManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

 /**
 * @Description: 对象管理
 * @Author: jeecg-boot
 * @Date:   2025-01-03
 * @Version: V1.0
 */
@Tag(name="对象管理")
@RestController
@RequestMapping("/ysjObjManage/ysjObjManage")
@Slf4j
public class YsjObjManageController extends JeecgController<YsjObjManage, IYsjObjManageService> {
	@Autowired
	private IYsjObjManageService ysjObjManageService;
	
	/**
	 * 分页列表查询
	 *
	 * @param ysjObjManage
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "对象管理-分页列表查询")
	@Operation(summary="对象管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<YsjObjManage>> queryPageList(YsjObjManage ysjObjManage,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        QueryWrapper<YsjObjManage> queryWrapper = QueryGenerator.initQueryWrapper(ysjObjManage, req.getParameterMap());
		Page<YsjObjManage> page = new Page<YsjObjManage>(pageNo, pageSize);
		IPage<YsjObjManage> pageList = ysjObjManageService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param ysjObjManage
	 * @return
	 */
	@AutoLog(value = "对象管理-添加")
	@Operation(summary="对象管理-添加")
	@RequiresPermissions("ysjObjManage:ysj_obj_manage:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody YsjObjManage ysjObjManage) {
		ysjObjManageService.save(ysjObjManage);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param ysjObjManage
	 * @return
	 */
	@AutoLog(value = "对象管理-编辑")
	@Operation(summary="对象管理-编辑")
	@RequiresPermissions("ysjObjManage:ysj_obj_manage:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody YsjObjManage ysjObjManage) {
		ysjObjManageService.updateById(ysjObjManage);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "对象管理-通过id删除")
	@Operation(summary="对象管理-通过id删除")
	@RequiresPermissions("ysjObjManage:ysj_obj_manage:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		ysjObjManageService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "对象管理-批量删除")
	@Operation(summary="对象管理-批量删除")
	@RequiresPermissions("ysjObjManage:ysj_obj_manage:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ysjObjManageService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "对象管理-通过id查询")
	@Operation(summary="对象管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<YsjObjManage> queryById(@RequestParam(name="id",required=true) String id) {
		YsjObjManage ysjObjManage = ysjObjManageService.getById(id);
		if(ysjObjManage==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(ysjObjManage);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param ysjObjManage
    */
    @RequiresPermissions("ysjObjManage:ysj_obj_manage:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, YsjObjManage ysjObjManage) {
        return super.exportXls(request, ysjObjManage, YsjObjManage.class, "对象管理");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("ysjObjManage:ysj_obj_manage:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, YsjObjManage.class);
    }

}
