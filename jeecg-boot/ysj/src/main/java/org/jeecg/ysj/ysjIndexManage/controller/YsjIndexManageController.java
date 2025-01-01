package org.jeecg.ysj.ysjIndexManage.controller;

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
import org.jeecg.ysj.ysjIndexManage.entity.YsjIndexManage;
import org.jeecg.ysj.ysjIndexManage.service.IYsjIndexManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

 /**
 * @Description: 索引管理
 * @Author: jeecg-boot
 * @Date:   2025-01-01
 * @Version: V1.0
 */
@Tag(name="索引管理")
@RestController
@RequestMapping("/ysjIndexManage/ysjIndexManage")
@Slf4j
public class YsjIndexManageController extends JeecgController<YsjIndexManage, IYsjIndexManageService> {
	@Autowired
	private IYsjIndexManageService ysjIndexManageService;
	
	/**
	 * 分页列表查询
	 *
	 * @param ysjIndexManage
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "索引管理-分页列表查询")
	@Operation(summary="索引管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<YsjIndexManage>> queryPageList(YsjIndexManage ysjIndexManage,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
        QueryWrapper<YsjIndexManage> queryWrapper = QueryGenerator.initQueryWrapper(ysjIndexManage, req.getParameterMap());
		Page<YsjIndexManage> page = new Page<YsjIndexManage>(pageNo, pageSize);
		IPage<YsjIndexManage> pageList = ysjIndexManageService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param ysjIndexManage
	 * @return
	 */
	@AutoLog(value = "索引管理-添加")
	@Operation(summary="索引管理-添加")
	@RequiresPermissions("ysjIndexManage:ysj_index_manage:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody YsjIndexManage ysjIndexManage) {
		ysjIndexManageService.save(ysjIndexManage);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param ysjIndexManage
	 * @return
	 */
	@AutoLog(value = "索引管理-编辑")
	@Operation(summary="索引管理-编辑")
	@RequiresPermissions("ysjIndexManage:ysj_index_manage:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody YsjIndexManage ysjIndexManage) {
		ysjIndexManageService.updateById(ysjIndexManage);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "索引管理-通过id删除")
	@Operation(summary="索引管理-通过id删除")
	@RequiresPermissions("ysjIndexManage:ysj_index_manage:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		ysjIndexManageService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "索引管理-批量删除")
	@Operation(summary="索引管理-批量删除")
	@RequiresPermissions("ysjIndexManage:ysj_index_manage:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.ysjIndexManageService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "索引管理-通过id查询")
	@Operation(summary="索引管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<YsjIndexManage> queryById(@RequestParam(name="id",required=true) String id) {
		YsjIndexManage ysjIndexManage = ysjIndexManageService.getById(id);
		if(ysjIndexManage==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(ysjIndexManage);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param ysjIndexManage
    */
    @RequiresPermissions("ysjIndexManage:ysj_index_manage:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, YsjIndexManage ysjIndexManage) {
        return super.exportXls(request, ysjIndexManage, YsjIndexManage.class, "索引管理");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("ysjIndexManage:ysj_index_manage:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, YsjIndexManage.class);
    }

}
