package com.zc.web.controller.organization;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.zc.organization.domain.ZsRouteLine;
import com.zc.organization.service.IZsRouteLineService;
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
 * 工艺路线行Controller
 * 
 * @author zc
 * @date 2025-07-17
 */
@RestController
@RequestMapping("/organization/routeLine")
public class ZsRouteLineController extends BaseController
{
    @Autowired
    private IZsRouteLineService zsRouteLineService;

    /**
     * 查询工艺路线行列表
     */
    @PreAuthorize("@ss.hasPermi('organization:routeLine:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZsRouteLine zsRouteLine)
    {
        startPage();
        List<ZsRouteLine> list = zsRouteLineService.selectZsRouteLineList(zsRouteLine);
        return getDataTable(list);
    }

    /**
     * 导出工艺路线行列表
     */
    @PreAuthorize("@ss.hasPermi('organization:routeLine:export')")
    @Log(title = "工艺路线行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ZsRouteLine zsRouteLine)
    {
        List<ZsRouteLine> list = zsRouteLineService.selectZsRouteLineList(zsRouteLine);
        ExcelUtil<ZsRouteLine> util = new ExcelUtil<ZsRouteLine>(ZsRouteLine.class);
        util.exportExcel(response, list, "工艺路线行数据");
    }

    /**
     * 获取工艺路线行详细信息
     */
    @PreAuthorize("@ss.hasPermi('organization:routeLine:query')")
    @GetMapping(value = "/{routeLineId}")
    public AjaxResult getInfo(@PathVariable("routeLineId") Long routeLineId)
    {
        return success(zsRouteLineService.selectZsRouteLineByRouteLineId(routeLineId));
    }

    /**
     * 新增工艺路线行
     */
    @PreAuthorize("@ss.hasPermi('organization:routeLine:add')")
    @Log(title = "工艺路线行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZsRouteLine zsRouteLine)
    {
        return toAjax(zsRouteLineService.insertZsRouteLine(zsRouteLine));
    }

    /**
     * 修改工艺路线行
     */
    @PreAuthorize("@ss.hasPermi('organization:routeLine:edit')")
    @Log(title = "工艺路线行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZsRouteLine zsRouteLine)
    {
        return toAjax(zsRouteLineService.updateZsRouteLine(zsRouteLine));
    }

    /**
     * 删除工艺路线行
     */
    @PreAuthorize("@ss.hasPermi('organization:routeLine:remove')")
    @Log(title = "工艺路线行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{routeLineIds}")
    public AjaxResult remove(@PathVariable Long[] routeLineIds)
    {
        return toAjax(zsRouteLineService.deleteZsRouteLineByRouteLineIds(routeLineIds));
    }
}
