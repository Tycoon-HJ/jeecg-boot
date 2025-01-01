package org.jeecg.ysj.ysjTbManage.controller;

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
import org.jeecg.ysj.ysjTbManage.entity.YsjTbManage;
import org.jeecg.ysj.ysjTbManage.service.IYsjTbManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

 /**
 * @Description: 表管理
 * @Author: jeecg-boot
 * @Date:   2025-01-01
 * @Version: V1.0
 */
@Tag(name="表管理")
@RestController
@RequestMapping("/ysjTbManage/ysjTbManage")
@Slf4j
public class YsjTbManageController extends JeecgController<YsjTbManage, IYsjTbManageService> {
	@Autowired
	private IYsjTbManageService ysjTbManageService;
	
	/**
	 * 分页列表查询
	 *
	 * @param ysjTbManage
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "表管理-分页列表查询")
	@Operation(summary="表管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<YsjTbManage>> queryPageList(YsjTbManage ysjTbManage,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        QueryWrapper<YsjTbManage> queryWrapper = QueryGenerator.initQueryWrapper(ysjTbManage, req.getParameterMap());
		Page<YsjTbManage> page = new Page<YsjTbManage>(pageNo, pageSize);
		IPage<YsjTbManage> pageList = ysjTbManageService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param ysjTbManage
	 * @return
	 */
	@AutoLog(value = "表管理-添加")
	@Operation(summary="表管理-添加")
	@RequiresPermissions("ysjTbManage:ysj_tb_manage:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody YsjTbManage ysjTbManage) {
		ysjTbManageService.save(ysjTbManage);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param ysjTbManage
	 * @return
	 */
	@AutoLog(value = "表管理-编辑")
	@Operation(summary="表管理-编辑")
	@RequiresPermissions("ysjTbManage:ysj_tb_manage:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody YsjTbManage ysjTbManage) {
		ysjTbManageService.updateById(ysjTbManage);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "表管理-通过id删除")
	@Operation(summary="表管理-通过id删除")
	@RequiresPermissions("ysjTbManage:ysj_tb_manage:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		ysjTbManageService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "表管理-批量删除")
	@Operation(summary="表管理-批量删除")
	@RequiresPermissions("ysjTbManage:ysj_tb_manage:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ysjTbManageService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "表管理-通过id查询")
	@Operation(summary="表管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<YsjTbManage> queryById(@RequestParam(name="id",required=true) String id) {
		YsjTbManage ysjTbManage = ysjTbManageService.getById(id);
		if(ysjTbManage==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(ysjTbManage);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param ysjTbManage
    */
    @RequiresPermissions("ysjTbManage:ysj_tb_manage:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, YsjTbManage ysjTbManage) {
        return super.exportXls(request, ysjTbManage, YsjTbManage.class, "表管理");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("ysjTbManage:ysj_tb_manage:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, YsjTbManage.class);
    }

}
