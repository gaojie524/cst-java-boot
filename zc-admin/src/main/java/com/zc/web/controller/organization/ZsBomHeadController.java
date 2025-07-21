package com.zc.web.controller.organization;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zc.organization.domain.ZsBomLine;
import com.zc.organization.service.IZsBomLineService;
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
import com.zc.organization.domain.ZsBomHead;
import com.zc.organization.service.IZsBomHeadService;
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
public class ZsBomHeadController extends BaseController
{
    @Autowired
    private IZsBomHeadService zsBomHeadService;
    @Autowired
    private IZsBomLineService  zsBomLineService;


    /**
     * 查询BOM头表列表
     */
    @PreAuthorize("@ss.hasPermi('organization:bomHead:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZsBomHead zsBomHead)
    {
        startPage();
        List<ZsBomHead> list = zsBomHeadService.selectZsBomHeadList(zsBomHead);
        return getDataTable(list);
    }

    /**
     * 导出BOM头表列表
     */
    @PreAuthorize("@ss.hasPermi('organization:bomHead:export')")
    @Log(title = "BOM头表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ZsBomHead zsBomHead)
    {
        List<ZsBomHead> list = zsBomHeadService.selectZsBomHeadList(zsBomHead);
        ExcelUtil<ZsBomHead> util = new ExcelUtil<ZsBomHead>(ZsBomHead.class);
        util.exportExcel(response, list, "BOM头表数据");
    }

    /**
     * 获取BOM头表详细信息
     */
    @PreAuthorize("@ss.hasPermi('organization:bomHead:query')")
    @GetMapping(value = "/{bomHeadId}")
    public AjaxResult getInfo(@PathVariable("bomHeadId") Long bomHeadId)
    {
        return success(zsBomHeadService.selectZsBomHeadByBomHeadId(bomHeadId));
    }

    /**
     * 新增BOM头表
     */
    @PreAuthorize("@ss.hasPermi('organization:bomHead:add')")
    @Log(title = "BOM头表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ZsBomHead zsBomHead)
    {
        //查询 bom_code 是不是 重复
        QueryWrapper<ZsBomHead> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ZsBomHead::getBomCode,zsBomHead.getBomCode());
        ZsBomHead zsBomHeads = zsBomHeadService.getOne(queryWrapper);
        if(zsBomHeads!=null){
            return AjaxResult.warn("BOM头表编号"+zsBomHead.getBomCode()+"已存在");
        }
        return toAjax(zsBomHeadService.insertZsBomHead(zsBomHead));
    }

    /**
     * 修改BOM头表
     */
    @PreAuthorize("@ss.hasPermi('organization:bomHead:edit')")
    @Log(title = "BOM头表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZsBomHead zsBomHead)
    {
        return toAjax(zsBomHeadService.updateZsBomHead(zsBomHead));
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
            ZsBomLine zsBomLine = new ZsBomLine();
            zsBomLine.setBomHeadId(bomHeadId); // 设置 bomHeadId
            List<ZsBomLine> zsBomLines = zsBomLineService.selectZsBomLineList(zsBomLine);

            // 如果存在子节点，不能删除该 BOM 头
            if (zsBomLines != null && !zsBomLines.isEmpty()) {
                // 获取 BOM 头表编号
                ZsBomHead zsBomHeadServiceById = zsBomHeadService.getById(bomHeadId);
                return AjaxResult.warn("BOM头表编号 " + zsBomHeadServiceById.getBomCode() + " 存在子节点，无法删除!");
            }
        }

        // 如果没有任何 BOM 头存在子节点，进行删除操作
        return toAjax(zsBomHeadService.deleteZsBomHeadByBomHeadIds(bomHeadIds));
    }

    /**
     * 获取BOM树列表
     */
    @PreAuthorize("@ss.hasPermi('organization:bomHead:list')")
    @GetMapping("/bomTree")
    public AjaxResult bomTree(ZsBomHead zsBomHead)
    {
        return success(zsBomHeadService.selectBomTreeList(zsBomHead));
    }
}
