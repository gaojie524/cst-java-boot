package com.zc.web.controller.documenter;

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
import com.zc.documenter.domain.ZcRouteLine;
import com.zc.documenter.service.IZcRouteLineService;
import com.zc.common.utils.poi.ExcelUtil;
import com.zc.common.core.page.TableDataInfo;

/**
 * 工艺路线行Controller
 * 
 * @author zc
 * @date 2025-07-26
 */
@RestController
@RequestMapping("/documenter/routeLine")
public class ZcRouteLineController extends BaseController
{
    @Autowired
    private IZcRouteLineService zcRouteLineService;

    /**
     * 查询工艺路线行列表
     */
    @PreAuthorize("@ss.hasPermi('documenter:routeLine:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZcRouteLine zcRouteLine)
    {
        startPage();
        List<ZcRouteLine> list = zcRouteLineService.selectZcRouteLineList(zcRouteLine);
        return getDataTable(list);
    }

    /**
     * 导出工艺路线行列表
     */
    @PreAuthorize("@ss.hasPermi('documenter:routeLine:export')")
    @Log(title = "工艺路线行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ZcRouteLine zcRouteLine)
    {
        List<ZcRouteLine> list = zcRouteLineService.selectZcRouteLineList(zcRouteLine);
        ExcelUtil<ZcRouteLine> util = new ExcelUtil<ZcRouteLine>(ZcRouteLine.class);
        util.exportExcel(response, list, "工艺路线行数据");
    }

    /**
     * 获取工艺路线行详细信息
     */
    @PreAuthorize("@ss.hasPermi('documenter:routeLine:query')")
    @GetMapping(value = "/{routeLineId}")
    public AjaxResult getInfo(@PathVariable("routeLineId") Long routeLineId)
    {
        return success(zcRouteLineService.selectZcRouteLineByRouteLineId(routeLineId));
    }

    /**
     * 新增工艺路线行
     */
    @PreAuthorize("@ss.hasPermi('documenter:routeLine:add')")
    @Log(title = "工艺路线行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZcRouteLine zcRouteLine)
    {
        return toAjax(zcRouteLineService.insertZcRouteLine(zcRouteLine));
    }

    /**
     * 修改工艺路线行
     */
    @PreAuthorize("@ss.hasPermi('documenter:routeLine:edit')")
    @Log(title = "工艺路线行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZcRouteLine zcRouteLine)
    {
        return toAjax(zcRouteLineService.updateZcRouteLine(zcRouteLine));
    }

    /**
     * 删除工艺路线行
     */
    @PreAuthorize("@ss.hasPermi('documenter:routeLine:remove')")
    @Log(title = "工艺路线行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{routeLineIds}")
    public AjaxResult remove(@PathVariable Long[] routeLineIds)
    {
        return toAjax(zcRouteLineService.deleteZcRouteLineByRouteLineIds(routeLineIds));
    }
}
