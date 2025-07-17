package com.zc.web.controller.organization;

import com.zc.common.annotation.Log;
import com.zc.common.core.controller.BaseController;
import com.zc.common.core.domain.AjaxResult;
import com.zc.common.core.page.TableDataInfo;
import com.zc.common.enums.BusinessType;
import com.zc.common.utils.poi.ExcelUtil;
import com.zc.organization.domain.ZsRouteHead;
import com.zc.organization.service.IZsRouteHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * 工艺路线头Controller
 * 
 * @author zc
 * @date 2025-07-17
 */
@RestController
@RequestMapping("/organization/head")
public class ZsRouteHeadController extends BaseController
{
    @Autowired
    private IZsRouteHeadService zsRouteHeadService;

    /**
     * 查询工艺路线头列表
     */
    @PreAuthorize("@ss.hasPermi('organization:head:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZsRouteHead zsRouteHead)
    {
        startPage();
        List<ZsRouteHead> list = zsRouteHeadService.selectZsRouteHeadList(zsRouteHead);
        return getDataTable(list);
    }

    /**
     * 导出工艺路线头列表
     */
    @PreAuthorize("@ss.hasPermi('organization:head:export')")
    @Log(title = "工艺路线头", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ZsRouteHead zsRouteHead)
    {
        List<ZsRouteHead> list = zsRouteHeadService.selectZsRouteHeadList(zsRouteHead);
        ExcelUtil<ZsRouteHead> util = new ExcelUtil<ZsRouteHead>(ZsRouteHead.class);
        util.exportExcel(response, list, "工艺路线头数据");
    }

    /**
     * 获取工艺路线头详细信息
     */
    @PreAuthorize("@ss.hasPermi('organization:head:query')")
    @GetMapping(value = "/{routeHeadId}")
    public AjaxResult getInfo(@PathVariable("routeHeadId") Long routeHeadId)
    {
        return success(zsRouteHeadService.selectZsRouteHeadByRouteHeadId(routeHeadId));
    }

    /**
     * 新增工艺路线头
     */
    @PreAuthorize("@ss.hasPermi('organization:head:add')")
    @Log(title = "工艺路线头", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZsRouteHead zsRouteHead)
    {
        zsRouteHead.setRouteHeadId((long)Math.abs(UUID.randomUUID().toString().hashCode()));
        zsRouteHead.setRouteCode(UUID.randomUUID().toString());
        zsRouteHead.setRouteVersion(zsRouteHead.getRouteCode()+"/v3.9.0");
        return toAjax(zsRouteHeadService.insertZsRouteHead(zsRouteHead));
    }

    /**
     * 修改工艺路线头
     */
    @PreAuthorize("@ss.hasPermi('organization:head:edit')")
    @Log(title = "工艺路线头", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZsRouteHead zsRouteHead)
    {
        return toAjax(zsRouteHeadService.updateZsRouteHead(zsRouteHead));
    }

    /**
     * 删除工艺路线头
     */
    @PreAuthorize("@ss.hasPermi('organization:head:remove')")
    @Log(title = "工艺路线头", businessType = BusinessType.DELETE)
	@DeleteMapping("/{routeHeadIds}")
    public AjaxResult remove(@PathVariable Long[] routeHeadIds)
    {
        return toAjax(zsRouteHeadService.deleteZsRouteHeadByRouteHeadIds(routeHeadIds));
    }
}
