package com.zc.web.controller.organization;

import java.util.List;
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
import com.zc.common.annotation.Log;
import com.zc.common.core.controller.BaseController;
import com.zc.common.core.domain.AjaxResult;
import com.zc.common.enums.BusinessType;
import com.zc.organization.domain.ZsBomLine;
import com.zc.organization.service.IZsBomLineService;
import com.zc.common.utils.poi.ExcelUtil;
import com.zc.common.core.page.TableDataInfo;

/**
 * BOM行Controller
 * 
 * @author zc
 * @date 2025-07-21
 */
@RestController
@RequestMapping("/organization/bomLine")
public class ZsBomLineController extends BaseController
{
    @Autowired
    private IZsBomLineService zsBomLineService;

    /**
     * 查询BOM行列表
     */
    @PreAuthorize("@ss.hasPermi('organization:bomLine:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZsBomLine zsBomLine)
    {
        startPage();
        List<ZsBomLine> list = zsBomLineService.selectZsBomLineList(zsBomLine);
        return getDataTable(list);
    }

    /**
     * 导出BOM行列表
     */
    @PreAuthorize("@ss.hasPermi('organization:bomLine:export')")
    @Log(title = "BOM行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ZsBomLine zsBomLine)
    {
        List<ZsBomLine> list = zsBomLineService.selectZsBomLineList(zsBomLine);
        ExcelUtil<ZsBomLine> util = new ExcelUtil<ZsBomLine>(ZsBomLine.class);
        util.exportExcel(response, list, "BOM行数据");
    }

    /**
     * 获取BOM行详细信息
     */
    @PreAuthorize("@ss.hasPermi('organization:bomLine:query')")
    @GetMapping(value = "/{bomLineId}")
    public AjaxResult getInfo(@PathVariable("bomLineId") Long bomLineId)
    {
        return success(zsBomLineService.selectZsBomLineByBomLineId(bomLineId));
    }

    /**
     * 新增BOM行
     */
    @PreAuthorize("@ss.hasPermi('organization:bomLine:add')")
    @Log(title = "BOM行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZsBomLine zsBomLine)
    {
        return toAjax(zsBomLineService.insertZsBomLine(zsBomLine));
    }

    /**
     * 修改BOM行
     */
    @PreAuthorize("@ss.hasPermi('organization:bomLine:edit')")
    @Log(title = "BOM行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZsBomLine zsBomLine)
    {
        return toAjax(zsBomLineService.updateZsBomLine(zsBomLine));
    }

    /**
     * 删除BOM行
     */
    @PreAuthorize("@ss.hasPermi('organization:bomLine:remove')")
    @Log(title = "BOM行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bomLineIds}")
    public AjaxResult remove(@PathVariable Long[] bomLineIds)
    {
        return toAjax(zsBomLineService.deleteZsBomLineByBomLineIds(bomLineIds));
    }
}
