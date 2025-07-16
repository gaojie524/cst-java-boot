package com.zc.web.controller.documenter;

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
import com.zc.common.annotation.Log;
import com.zc.common.core.controller.BaseController;
import com.zc.common.core.domain.AjaxResult;
import com.zc.common.enums.BusinessType;
import com.zc.documenter.domain.ZsProcess;
import com.zc.documenter.service.IZsProcessService;
import com.zc.common.utils.poi.ExcelUtil;
import com.zc.common.core.page.TableDataInfo;

/**
 * 工序Controller
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
@RestController
@RequestMapping("/documenter/process")
public class ZsProcessController extends BaseController
{
    @Autowired
    private IZsProcessService zsProcessService;

    /**
     * 查询工序列表
     */
    @PreAuthorize("@ss.hasPermi('documenter:process:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZsProcess zsProcess)
    {
        startPage();
        List<ZsProcess> list = zsProcessService.selectZsProcessList(zsProcess);
        return getDataTable(list);
    }

    /**
     * 导出工序列表
     */
    @PreAuthorize("@ss.hasPermi('documenter:process:export')")
    @Log(title = "工序", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ZsProcess zsProcess)
    {
        List<ZsProcess> list = zsProcessService.selectZsProcessList(zsProcess);
        ExcelUtil<ZsProcess> util = new ExcelUtil<ZsProcess>(ZsProcess.class);
        util.exportExcel(response, list, "工序数据");
    }

    /**
     * 获取工序详细信息
     */
    @PreAuthorize("@ss.hasPermi('documenter:process:query')")
    @GetMapping(value = "/{processId}")
    public AjaxResult getInfo(@PathVariable("processId") Long processId)
    {
        return success(zsProcessService.selectZsProcessByProcessId(processId));
    }

    /**
     * 新增工序
     */
    @PreAuthorize("@ss.hasPermi('documenter:process:add')")
    @Log(title = "工序", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZsProcess zsProcess)
    {
        zsProcess.setProcessCode(UUID.randomUUID().toString());
        return toAjax(zsProcessService.insertZsProcess(zsProcess));
    }

    /**
     * 修改工序
     */
    @PreAuthorize("@ss.hasPermi('documenter:process:edit')")
    @Log(title = "工序", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZsProcess zsProcess)
    {
        return toAjax(zsProcessService.updateZsProcess(zsProcess));
    }

    /**
     * 删除工序
     */
    @PreAuthorize("@ss.hasPermi('documenter:process:remove')")
    @Log(title = "工序", businessType = BusinessType.DELETE)
	@DeleteMapping("/{processIds}")
    public AjaxResult remove(@PathVariable Long[] processIds)
    {
        return toAjax(zsProcessService.deleteZsProcessByProcessIds(processIds));
    }
}
