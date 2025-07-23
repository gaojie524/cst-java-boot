package com.zc.web.controller.documenter;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.zc.documenter.service.IZcItemHistoryService;
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
import com.zc.documenter.domain.ZcItemHistory;
import com.zc.common.utils.poi.ExcelUtil;
import com.zc.common.core.page.TableDataInfo;

/**
 * 物料历史
Controller
 * 
 * @author ruoyi
 * @date 2025-07-10
 */
@RestController
@RequestMapping("/documenter/itemHistory")
public class ZcItemHistoryController extends BaseController
{
    @Autowired
    private IZcItemHistoryService zcItemHistoryService;

    /**
     * 查询物料历史
列表
     */
    @PreAuthorize("@ss.hasPermi('documenter:itemHistory:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZcItemHistory zsItemHistory)
    {
        startPage();
        List<ZcItemHistory> list = zcItemHistoryService.selectZcItemHistoryList(zsItemHistory);
        return getDataTable(list);
    }

    /**
     * 导出物料历史列表
     */
    @PreAuthorize("@ss.hasPermi('documenter:itemHistory:export')")
    @Log(title = "物料历史", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ZcItemHistory zsItemHistory)
    {
        List<ZcItemHistory> list = zcItemHistoryService.selectZcItemHistoryList(zsItemHistory);
        ExcelUtil<ZcItemHistory> util = new ExcelUtil<ZcItemHistory>(ZcItemHistory.class);
        util.exportExcel(response, list, "物料历史数据");
    }

    /**
     * 获取物料历史详细信息
     */
    @PreAuthorize("@ss.hasPermi('documenter:itemHistory:query')")
    @GetMapping(value = "/{eventId}")
    public AjaxResult getInfo(@PathVariable("eventId") Long eventId)
    {
        return success(zcItemHistoryService.selectZcItemHistoryByEventId(eventId));
    }

    /**
     * 新增物料历史

     */
    @PreAuthorize("@ss.hasPermi('documenter:itemHistory:add')")
    @Log(title = "物料历史", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZcItemHistory zsItemHistory)
    {
        return toAjax(zcItemHistoryService.insertZcItemHistory(zsItemHistory));
    }

    /**
     * 修改物料历史

     */
    @PreAuthorize("@ss.hasPermi('documenter:itemHistory:edit')")
    @Log(title = "物料历史", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZcItemHistory zsItemHistory)
    {
        return toAjax(zcItemHistoryService.updateZcItemHistory(zsItemHistory));
    }

    /**
     * 删除物料历史

     */
    @PreAuthorize("@ss.hasPermi('documenter:itemHistory:remove')")
    @Log(title = "物料历史", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eventIds}")
    public AjaxResult remove(@PathVariable Long[] eventIds)
    {
        return toAjax(zcItemHistoryService.deleteZcItemHistoryByEventIds(eventIds));
    }
}
