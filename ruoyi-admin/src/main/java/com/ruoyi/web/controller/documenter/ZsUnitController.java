package com.ruoyi.web.controller.documenter;

import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.documenter.domain.ZsUnit;
import com.ruoyi.documenter.service.IZsUnitService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 单位Controller
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
@RestController
@RequestMapping("/documenter/unit")
public class ZsUnitController extends BaseController
{
    @Autowired
    private IZsUnitService zsUnitService;

    /**
     * 查询单位列表
     */
    @PreAuthorize("@ss.hasPermi('documenter:unit:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZsUnit zsUnit)
    {
        startPage();
        List<ZsUnit> list = zsUnitService.selectZsUnitList(zsUnit);
        return getDataTable(list);
    }

    /**
     * 导出单位列表
     */
    @PreAuthorize("@ss.hasPermi('documenter:unit:export')")
    @Log(title = "单位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ZsUnit zsUnit)
    {
        List<ZsUnit> list = zsUnitService.selectZsUnitList(zsUnit);
        ExcelUtil<ZsUnit> util = new ExcelUtil<ZsUnit>(ZsUnit.class);
        util.exportExcel(response, list, "单位数据");
    }

    /**
     * 获取单位详细信息
     */
    @PreAuthorize("@ss.hasPermi('documenter:unit:query')")
    @GetMapping(value = "/{unitId}")
    public AjaxResult getInfo(@PathVariable("unitId") Long unitId)
    {
        return success(zsUnitService.selectZsUnitByUnitId(unitId));
    }

    /**
     * 新增单位
     */
    @PreAuthorize("@ss.hasPermi('documenter:unit:add')")
    @Log(title = "单位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZsUnit zsUnit)
    {
        zsUnit.setCreateBy(getUsername());
        zsUnit.setUpdateBy(getUsername());
        return toAjax(zsUnitService.insertZsUnit(zsUnit));
    }

    /**
     * 修改单位
     */
    @PreAuthorize("@ss.hasPermi('documenter:unit:edit')")
    @Log(title = "单位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZsUnit zsUnit)
    {
        zsUnit.setUpdateBy(getUsername());
        return toAjax(zsUnitService.updateZsUnit(zsUnit));
    }

    /**
     * 删除单位
     */
    @PreAuthorize("@ss.hasPermi('documenter:unit:remove')")
    @Log(title = "单位", businessType = BusinessType.DELETE)
	@DeleteMapping("/{unitIds}")
    public AjaxResult remove(@PathVariable Long[] unitIds)
    {
        return toAjax(zsUnitService.deleteZsUnitByUnitIds(unitIds));
    }
}
