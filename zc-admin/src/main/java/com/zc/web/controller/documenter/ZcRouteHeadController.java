package com.zc.web.controller.documenter;

import com.zc.common.annotation.Log;
import com.zc.common.core.controller.BaseController;
import com.zc.common.core.domain.AjaxResult;
import com.zc.common.core.domain.entity.SysUser;
import com.zc.common.core.page.TableDataInfo;
import com.zc.common.enums.BusinessType;
import com.zc.common.utils.poi.ExcelUtil;
import com.zc.documenter.domain.ZcRouteHead;
import com.zc.documenter.service.IZcRouteHeadService;
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
@RequestMapping("/documenter/routeHead")
public class ZcRouteHeadController extends BaseController
{
    @Autowired
    private IZcRouteHeadService zcRouteHeadService;

    /**
     * 查询工艺路线头列表
     */
    @PreAuthorize("@ss.hasPermi('documenter:routeHead:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZcRouteHead zcRouteHead)
    {
        startPage();
        List<ZcRouteHead> list = zcRouteHeadService.selectZcRouteHeadList(zcRouteHead);
        return getDataTable(list);
    }

    /**
     * 导出工艺路线头列表
     */
    @PreAuthorize("@ss.hasPermi('documenter:routeHead:export')")
    @Log(title = "工艺路线头", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ZcRouteHead zcRouteHead)
    {
        List<ZcRouteHead> list = zcRouteHeadService.selectZcRouteHeadList(zcRouteHead);
        ExcelUtil<ZcRouteHead> util = new ExcelUtil<ZcRouteHead>(ZcRouteHead.class);
        util.exportExcel(response, list, "工艺路线头数据");
    }

    /**
     * 获取工艺路线头详细信息
     */
    @PreAuthorize("@ss.hasPermi('documenter:routeHead:query')")
    @GetMapping(value = "/{routeHeadId}")
    public AjaxResult getInfo(@PathVariable("routeHeadId") Long routeHeadId)
    {
        return success(zcRouteHeadService.selectZcRouteHeadByRouteHeadId(routeHeadId));
    }

    /**
     * 新增工艺路线头
     */
    @PreAuthorize("@ss.hasPermi('documenter:routeHead:add')")
    @Log(title = "工艺路线头", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZcRouteHead zcRouteHead)
    {
        return toAjax(zcRouteHeadService.insertZcRouteHead(zcRouteHead));
    }

    /**
     * 修改工艺路线头
     */
    @PreAuthorize("@ss.hasPermi('documenter:routeHead:edit')")
    @Log(title = "工艺路线头", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZcRouteHead zcRouteHead)
    {
        zcRouteHead.setRouteSmallVersion(zcRouteHead.getRouteSmallVersion()+1);
        return toAjax(zcRouteHeadService.updateZcRouteHead(zcRouteHead));
    }

    /**
     * 删除工艺路线头
     */
    @PreAuthorize("@ss.hasPermi('documenter:routeHead:remove')")
    @Log(title = "工艺路线头", businessType = BusinessType.DELETE)
	@DeleteMapping("/{routeHeadIds}")
    public AjaxResult remove(@PathVariable Long[] routeHeadIds)
    {
        return toAjax(zcRouteHeadService.deleteZcRouteHeadByRouteHeadIds(routeHeadIds));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('documenter:routeHead:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody ZcRouteHead zcRouteHead)
    {
        zcRouteHead.setUpdateBy(getUsername());
        zcRouteHead.setRouteSmallVersion(zcRouteHead.getRouteSmallVersion()+1);
        return toAjax(zcRouteHeadService.updateRouteHeadStatus(zcRouteHead));
    }
}
