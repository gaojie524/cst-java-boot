package com.zc.web.controller.documenter;

import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.zc.documenter.service.IZcProcessService;
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
import com.zc.documenter.domain.ZcProcess;
import com.zc.common.utils.poi.ExcelUtil;
import com.zc.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 工序Controller
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
@RestController
@RequestMapping("/documenter/process")
public class ZcProcessController extends BaseController
{
    @Autowired
    private IZcProcessService zcProcessService;

    /**
     * 查询工序列表
     */
    @PreAuthorize("@ss.hasPermi('documenter:process:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZcProcess zsProcess)
    {
        startPage();
        List<ZcProcess> list = zcProcessService.selectZcProcessList(zsProcess);
        return getDataTable(list);
    }

    /**
     * 导出工序列表
     */
    @PreAuthorize("@ss.hasPermi('documenter:process:export')")
    @Log(title = "工序", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ZcProcess zsProcess)
    {
        List<ZcProcess> list = zcProcessService.selectZcProcessList(zsProcess);
        ExcelUtil<ZcProcess> util = new ExcelUtil<ZcProcess>(ZcProcess.class);
        util.exportExcel(response, list, "工序数据");
    }

    /**
     * 获取工序详细信息
     */
    @PreAuthorize("@ss.hasPermi('documenter:process:query')")
    @GetMapping(value = "/{processId}")
    public AjaxResult getInfo(@PathVariable("processId") Long processId)
    {
        return success(zcProcessService.selectZcProcessByProcessId(processId));
    }

    /**
     * 新增工序
     */
    @PreAuthorize("@ss.hasPermi('documenter:process:add')")
    @Log(title = "工序", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZcProcess zcProcess)
    {
        return toAjax(zcProcessService.insertZcProcess(zcProcess));
    }

    /**
     * 修改工序
     */
    @PreAuthorize("@ss.hasPermi('documenter:process:edit')")
    @Log(title = "工序", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZcProcess zcProcess)
    {
        return toAjax(zcProcessService.updateZcProcess(zcProcess));
    }

    /**
     * 删除工序
     */
    @PreAuthorize("@ss.hasPermi('documenter:process:remove')")
    @Log(title = "工序", businessType = BusinessType.DELETE)
	@DeleteMapping("/{processIds}")
    public AjaxResult remove(@PathVariable Long[] processIds)
    {
        return toAjax(zcProcessService.deleteZcProcessByProcessIds(processIds));
    }


    @Log(title = "工序管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('documenter:process:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ZcProcess> util = new ExcelUtil<ZcProcess>(ZcProcess.class);
        List<ZcProcess> processList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = zcProcessService.importProcess(processList, updateSupport, operName);
        return success(message);
    }


    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<ZcProcess> util = new ExcelUtil<ZcProcess>(ZcProcess.class);
        util.importTemplateExcel(response, "工序数据");
    }
}
