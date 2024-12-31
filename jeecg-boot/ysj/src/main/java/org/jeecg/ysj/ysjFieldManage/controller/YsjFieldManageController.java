package org.jeecg.ysj.ysjFieldManage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.ysj.ysjFieldManage.entity.YsjFieldManage;
import org.jeecg.ysj.ysjFieldManage.service.IYsjFieldManageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

/**
 * @Description: 字段管理
 * @Author: jeecg-boot
 * @Date: 2024-12-29
 * @Version: V1.0
 */
@Tag(name = "字段管理")
@RestController
@RequestMapping("/ysjFieldManage/ysjFieldManage")
@Slf4j
public class YsjFieldManageController extends JeecgController<YsjFieldManage, IYsjFieldManageService> {
    @Resource
    private IYsjFieldManageService ysjFieldManageService;

    /**
     * 分页列表查询
     *
     * @param ysjFieldManage
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    //@AutoLog(value = "字段管理-分页列表查询")
    @Operation(summary = "字段管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<YsjFieldManage>> queryPageList(YsjFieldManage ysjFieldManage,
                                                       @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                       HttpServletRequest req) {
        QueryWrapper<YsjFieldManage> queryWrapper = QueryGenerator.initQueryWrapper(ysjFieldManage, req.getParameterMap());
        Page<YsjFieldManage> page = new Page<YsjFieldManage>(pageNo, pageSize);
        IPage<YsjFieldManage> pageList = ysjFieldManageService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param ysjFieldManage
     * @return
     */
    @AutoLog(value = "字段管理-添加")
    @Operation(summary = "字段管理-添加")
    @RequiresPermissions("ysjFieldManage:ysj_field_manage:add")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody YsjFieldManage ysjFieldManage) {
        // 新增时判断是否是字典值，是的话才可以填字典编码，否则提示异常信息
        if (ysjFieldManage.getYsjDict().equals("0")) {
            if (StringUtils.isNoneEmpty(ysjFieldManage.getYsjDictCode())) {
                return Result.error("字典为否，字典编码不允许填值");
            }
        }
        ysjFieldManageService.save(ysjFieldManage);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param ysjFieldManage
     * @return
     */
    @AutoLog(value = "字段管理-编辑")
    @Operation(summary = "字段管理-编辑")
    @RequiresPermissions("ysjFieldManage:ysj_field_manage:edit")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<String> edit(@RequestBody YsjFieldManage ysjFieldManage) {
        ysjFieldManageService.updateById(ysjFieldManage);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "字段管理-通过id删除")
    @Operation(summary = "字段管理-通过id删除")
    @RequiresPermissions("ysjFieldManage:ysj_field_manage:delete")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name = "id", required = true) String id) {
        ysjFieldManageService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "字段管理-批量删除")
    @Operation(summary = "字段管理-批量删除")
    @RequiresPermissions("ysjFieldManage:ysj_field_manage:deleteBatch")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.ysjFieldManageService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "字段管理-通过id查询")
    @Operation(summary = "字段管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<YsjFieldManage> queryById(@RequestParam(name = "id", required = true) String id) {
        YsjFieldManage ysjFieldManage = ysjFieldManageService.getById(id);
        if (ysjFieldManage == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(ysjFieldManage);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param ysjFieldManage
     */
    @RequiresPermissions("ysjFieldManage:ysj_field_manage:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, YsjFieldManage ysjFieldManage) {
        return super.exportXls(request, ysjFieldManage, YsjFieldManage.class, "字段管理");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequiresPermissions("ysjFieldManage:ysj_field_manage:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, YsjFieldManage.class);
    }

}
