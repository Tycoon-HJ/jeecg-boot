package org.jeecg.ysj.ysjColumnManage.controller;

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
import org.jeecg.ysj.ysjColumnManage.entity.YsjColumnManage;
import org.jeecg.ysj.ysjColumnManage.service.IYsjColumnManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

 /**
 * @Description: 列管理
 * @Author: jeecg-boot
 * @Date:   2025-01-01
 * @Version: V1.0
 */
@Tag(name="列管理")
@RestController
@RequestMapping("/ysjColumnManage/ysjColumnManage")
@Slf4j
public class YsjColumnManageController extends JeecgController<YsjColumnManage, IYsjColumnManageService> {
	@Autowired
	private IYsjColumnManageService ysjColumnManageService;
	
	/**
	 * 分页列表查询
	 *
	 * @param ysjColumnManage
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "列管理-分页列表查询")
	@Operation(summary="列管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<YsjColumnManage>> queryPageList(YsjColumnManage ysjColumnManage,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        QueryWrapper<YsjColumnManage> queryWrapper = QueryGenerator.initQueryWrapper(ysjColumnManage, req.getParameterMap());
		Page<YsjColumnManage> page = new Page<YsjColumnManage>(pageNo, pageSize);
		IPage<YsjColumnManage> pageList = ysjColumnManageService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param ysjColumnManage
	 * @return
	 */
	@AutoLog(value = "列管理-添加")
	@Operation(summary="列管理-添加")
	@RequiresPermissions("ysjColumnManage:ysj_column_manage:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody YsjColumnManage ysjColumnManage) {
		ysjColumnManageService.save(ysjColumnManage);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param ysjColumnManage
	 * @return
	 */
	@AutoLog(value = "列管理-编辑")
	@Operation(summary="列管理-编辑")
	@RequiresPermissions("ysjColumnManage:ysj_column_manage:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody YsjColumnManage ysjColumnManage) {
		ysjColumnManageService.updateWithNull(ysjColumnManage);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "列管理-通过id删除")
	@Operation(summary="列管理-通过id删除")
	@RequiresPermissions("ysjColumnManage:ysj_column_manage:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		ysjColumnManageService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "列管理-批量删除")
	@Operation(summary="列管理-批量删除")
	@RequiresPermissions("ysjColumnManage:ysj_column_manage:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ysjColumnManageService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "列管理-通过id查询")
	@Operation(summary="列管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<YsjColumnManage> queryById(@RequestParam(name="id",required=true) String id) {
		YsjColumnManage ysjColumnManage = ysjColumnManageService.getById(id);
		if(ysjColumnManage==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(ysjColumnManage);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param ysjColumnManage
    */
    @RequiresPermissions("ysjColumnManage:ysj_column_manage:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, YsjColumnManage ysjColumnManage) {
        return super.exportXls(request, ysjColumnManage, YsjColumnManage.class, "列管理");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("ysjColumnManage:ysj_column_manage:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, YsjColumnManage.class);
    }

}
