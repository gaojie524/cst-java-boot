package com.zc.web.controller.documenter;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.zc.documenter.domain.ZcUnit;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zc.common.annotation.Log;
import com.zc.common.core.controller.BaseController;
import com.zc.common.core.domain.AjaxResult;
import com.zc.common.enums.BusinessType;

import com.zc.common.utils.poi.ExcelUtil;
import com.zc.common.core.page.TableDataInfo;

/**
 * 单位管理Controller
 * 
 * @author ruoyi
 * @date 2025-07-16
 */
@RestController
@RequestMapping("/documenter/unit")
public class ZsUnitController extends BaseController
{
    @Autowired
    private IZsUnitService zsUnitService;

    /**
     * 查询单位管理列表
     */
    @PreAuthorize("@ss.hasPermi('documenter:unit:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZcUnit zsUnit)
    {
        startPage();
        List<ZcUnit> list = zsUnitService.selectZsUnitList(zsUnit);
        return getDataTable(list);
    }

    /**
     * 导出单位管理列表
     */
    @PreAuthorize("@ss.hasPermi('documenter:unit:export')")
    @Log(title = "单位管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ZcUnit zsUnit)
    {
        List<ZcUnit> list = zsUnitService.selectZsUnitList(zsUnit);
        ExcelUtil<ZcUnit> util = new ExcelUtil<ZcUnit>(ZcUnit.class);
        util.exportExcel(response, list, "单位管理数据");
    }

    /**
     * 获取单位管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('documenter:unit:query')")
    @GetMapping(value = "/{unitId}")
    public AjaxResult getInfo(@PathVariable("unitId") Long unitId)
    {
        return success(zsUnitService.selectZsUnitByUnitId(unitId));
    }

    /**
     * 新增单位管理
     */
    @PreAuthorize("@ss.hasPermi('documenter:unit:add')")
    @Log(title = "单位管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZcUnit zsUnit)
    {
        return toAjax(zsUnitService.insertZsUnit(zsUnit));
    }

    /**
     * 修改单位管理
     */
    @PreAuthorize("@ss.hasPermi('documenter:unit:edit')")
    @Log(title = "单位管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZcUnit zsUnit)
    {
        return toAjax(zsUnitService.updateZsUnit(zsUnit));
    }

    /**
     * 删除单位管理
     */
    @PreAuthorize("@ss.hasPermi('documenter:unit:remove')")
    @Log(title = "单位管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{unitIds}")
    public AjaxResult remove(@PathVariable Long[] unitIds)
    {
        return toAjax(zsUnitService.deleteZsUnitByUnitIds(unitIds));
    }
}
