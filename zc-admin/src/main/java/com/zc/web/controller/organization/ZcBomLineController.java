package com.zc.web.controller.organization;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.zc.organization.domain.ZcBomLine;
import com.zc.organization.service.IZcBomLineService;
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
 * BOM行Controller
 * 
 * @author zc
 * @date 2025-07-21
 */
@RestController
@RequestMapping("/organization/bomLine")
public class ZcBomLineController extends BaseController
{
    @Autowired
    private IZcBomLineService zcBomLineService;

    /**
     * 查询BOM行列表
     */
    @PreAuthorize("@ss.hasPermi('organization:bomLine:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZcBomLine zcBomLine)
    {
        startPage();
        List<ZcBomLine> list = zcBomLineService.selectZcBomLineList(zcBomLine);
        return getDataTable(list);
    }

    /**
     * 导出BOM行列表
     */
    @PreAuthorize("@ss.hasPermi('organization:bomLine:export')")
    @Log(title = "BOM行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ZcBomLine zcBomLine)
    {
        List<ZcBomLine> list = zcBomLineService.selectZcBomLineList(zcBomLine);
        ExcelUtil<ZcBomLine> util = new ExcelUtil<ZcBomLine>(ZcBomLine.class);
        util.exportExcel(response, list, "BOM行数据");
    }

    /**
     * 获取BOM行详细信息
     */
    @PreAuthorize("@ss.hasPermi('organization:bomLine:query')")
    @GetMapping(value = "/{bomLineId}")
    public AjaxResult getInfo(@PathVariable("bomLineId") Long bomLineId)
    {
        return success(zcBomLineService.selectZcBomLineByBomLineId(bomLineId));
    }

    /**
     * 新增BOM行
     */
    @PreAuthorize("@ss.hasPermi('organization:bomLine:add')")
    @Log(title = "BOM行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZcBomLine zsBomLine)
    {
        return toAjax(zcBomLineService.insertZcBomLine(zsBomLine));
    }

    /**
     * 修改BOM行
     */
    @PreAuthorize("@ss.hasPermi('organization:bomLine:edit')")
    @Log(title = "BOM行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZcBomLine zsBomLine)
    {
        return toAjax(zcBomLineService.updateZcBomLine(zsBomLine));
    }

    /**
     * 删除BOM行
     */
    @PreAuthorize("@ss.hasPermi('organization:bomLine:remove')")
    @Log(title = "BOM行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bomLineIds}")
    public AjaxResult remove(@PathVariable Long[] bomLineIds)
    {
        return toAjax(zcBomLineService.deleteZcBomLineByBomLineIds(bomLineIds));
    }
}
