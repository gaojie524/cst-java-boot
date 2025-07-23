package com.zc.web.controller.organization;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zc.organization.domain.ZcBomHead;
import com.zc.organization.domain.ZcBomLine;
import com.zc.organization.service.IZcBomHeadService;
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
 * BOM头表Controller
 * 
 * @author zc
 * @date 2025-07-20
 */
@RestController
@RequestMapping("/organization/bomHead")
public class ZcBomHeadController extends BaseController
{
    @Autowired
    private IZcBomHeadService zcBomHeadService;
    @Autowired
    private IZcBomLineService zcBomLineService;


    /**
     * 查询BOM头表列表
     */
    @PreAuthorize("@ss.hasPermi('organization:bomHead:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZcBomHead zsBomHead)
    {
        startPage();
        List<ZcBomHead> list = zcBomHeadService.selectZcBomHeadList(zsBomHead);
        return getDataTable(list);
    }

    /**
     * 导出BOM头表列表
     */
    @PreAuthorize("@ss.hasPermi('organization:bomHead:export')")
    @Log(title = "BOM头表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ZcBomHead zsBomHead)
    {
        List<ZcBomHead> list = zcBomHeadService.selectZcBomHeadList(zsBomHead);
        ExcelUtil<ZcBomHead> util = new ExcelUtil<ZcBomHead>(ZcBomHead.class);
        util.exportExcel(response, list, "BOM头表数据");
    }

    /**
     * 获取BOM头表详细信息
     */
    @PreAuthorize("@ss.hasPermi('organization:bomHead:query')")
    @GetMapping(value = "/{bomHeadId}")
    public AjaxResult getInfo(@PathVariable("bomHeadId") Long bomHeadId)
    {
        return success(zcBomHeadService.selectZcBomHeadByBomHeadId(bomHeadId));
    }

    /**
     * 新增BOM头表
     */
    @PreAuthorize("@ss.hasPermi('organization:bomHead:add')")
    @Log(title = "BOM头表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZcBomHead zcBomHead)
    {
        //查询 bom_code 是不是 重复
        QueryWrapper<ZcBomHead> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ZcBomHead::getBomCode,zcBomHead.getBomCode());
        ZcBomHead zcBomHeads = zcBomHeadService.getOne(queryWrapper);
        if(zcBomHeads!=null){
            return AjaxResult.warn("BOM头表编号"+zcBomHead.getBomCode()+"已存在");
        }
        return toAjax(zcBomHeadService.insertZcBomHead(zcBomHead));
    }

    /**
     * 修改BOM头表
     */
    @PreAuthorize("@ss.hasPermi('organization:bomHead:edit')")
    @Log(title = "BOM头表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZcBomHead zcBomHead)
    {
        return toAjax(zcBomHeadService.updateZcBomHead(zcBomHead));
    }

    /**
     * 删除BOM头表
     */
    @PreAuthorize("@ss.hasPermi('organization:bomHead:remove')")
    @Log(title = "BOM头表", businessType = BusinessType.DELETE)
    @DeleteMapping("/{bomHeadIds}")
    public AjaxResult remove(@PathVariable Long[] bomHeadIds)
    {
        // 遍历每个 BOM 头 ID
        for (Long bomHeadId : bomHeadIds) {
            // 查找 BOM 头的 BOM 线信息
            ZcBomLine zcBomLine = new ZcBomLine();
            zcBomLine.setBomHeadId(bomHeadId); // 设置 bomHeadId
            List<ZcBomLine> zsBomLines = zcBomLineService.selectZcBomLineList(zcBomLine);

            // 如果存在子节点，不能删除该 BOM 头
            if (zsBomLines != null && !zsBomLines.isEmpty()) {
                // 获取 BOM 头表编号
                ZcBomHead zsBomHeadServiceById = zcBomHeadService.getById(bomHeadId);
                return AjaxResult.warn("BOM头表编号 " + zsBomHeadServiceById.getBomCode() + " 存在子节点，无法删除!");
            }
        }

        // 如果没有任何 BOM 头存在子节点，进行删除操作
        return toAjax(zcBomHeadService.deleteZcBomHeadByBomHeadIds(bomHeadIds));
    }

    /**
     * 获取BOM树列表
     */
    @PreAuthorize("@ss.hasPermi('organization:bomHead:list')")
    @GetMapping("/bomTree")
    public AjaxResult bomTree(ZcBomHead zcBomHead)
    {
        return success(zcBomHeadService.selectBomTreeList(zcBomHead));
    }
}
