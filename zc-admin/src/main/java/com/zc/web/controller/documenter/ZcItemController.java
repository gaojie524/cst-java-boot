package com.zc.web.controller.documenter;

import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;


import com.zc.common.utils.bean.BeanUtils;
import com.zc.documenter.domain.ZcItemHistory;

import com.zc.documenter.service.IZcItemHistoryService;
import com.zc.documenter.service.IZcItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
import com.zc.documenter.domain.ZcItem;
import com.zc.common.utils.poi.ExcelUtil;
import com.zc.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 物料Controller
 * 
 * @author ruoyi
 * @date 2025-07-10
 */
@RestController
@RequestMapping("/documenter/item")
public class ZcItemController extends BaseController
{
    @Autowired
    private IZcItemService zcItemService;
    @Autowired
    private IZcItemHistoryService zcItemHistoryService;


    /**
     * 查询物料列表
     */
    @PreAuthorize("@ss.hasPermi('documenter:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZcItem zsItem)
    {
        startPage();
        List<ZcItem> list = zcItemService.selectZcItemList(zsItem);
        return getDataTable(list);
    }

    /**
     * 导出物料列表
     */
    @PreAuthorize("@ss.hasPermi('documenter:item:export')")
    @Log(title = "物料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ZcItem zsItem)
    {
        List<ZcItem> list = zcItemService.selectZcItemList(zsItem);
        ExcelUtil<ZcItem> util = new ExcelUtil<ZcItem>(ZcItem.class);
        util.exportExcel(response, list, "物料数据");
    }

    /**
     * 获取物料详细信息
     */
    @PreAuthorize("@ss.hasPermi('documenter:item:query')")
    @GetMapping(value = "/{itemId}")
    public AjaxResult getInfo(@PathVariable("itemId") Long itemId)
    {
        return success(zcItemService.selectZcItemByItemId(itemId));
    }

    /**
     * 新增物料
     */
    @PreAuthorize("@ss.hasPermi('documenter:item:add')")
    @Log(title = "物料", businessType = BusinessType.INSERT)
    @PostMapping
    @Transactional  // 添加事务注解
    public AjaxResult add(@RequestBody ZcItem zsItem)
    {
        ZcItemHistory zsItemHistory = new ZcItemHistory();
        zsItem.setItemId((long)Math.abs(UUID.randomUUID().toString().hashCode()));
        zsItem.setItemCode(UUID.randomUUID().toString());
        //往物料历史表 增加记录
        BeanUtils.copyProperties(zsItem, zsItemHistory);
        zsItemHistory.setOperation("0");
        zcItemHistoryService.insertZcItemHistory(zsItemHistory);
        return toAjax(zcItemService.insertZcItem(zsItem));
    }

    /**
     * 修改物料
     */
    @PreAuthorize("@ss.hasPermi('documenter:item:edit')")
    @Log(title = "物料", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZcItem zsItem)
    {
        ZcItemHistory zsItemHistory = new ZcItemHistory();
        //往物料历史表 增加记录
        BeanUtils.copyProperties(zsItem, zsItemHistory);
        zsItemHistory.setOperation("1");
        zcItemHistoryService.insertZcItemHistory(zsItemHistory);
        return toAjax(zcItemService.updateZcItem(zsItem));
    }

    /**
     * 删除物料
     */
    @PreAuthorize("@ss.hasPermi('documenter:item:remove')")
    @Log(title = "物料", businessType = BusinessType.DELETE)
	@DeleteMapping("/{itemIds}")
    public AjaxResult remove(@PathVariable Long[] itemIds)
    {
        for (Long itemId : itemIds) {
            ZcItem zsItem = zcItemService.selectZcItemByItemId(itemId);
            ZcItemHistory zcItemHistory = new ZcItemHistory();
            BeanUtils.copyProperties(zsItem,zcItemHistory);
            zcItemHistory.setOperation("2");
            zcItemHistoryService.insertZcItemHistory(zcItemHistory);
        }
        return toAjax(zcItemService.deleteZcItemByItemIds(itemIds));
    }


    @Log(title = "物料管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('documenter:item:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ZcItem> util = new ExcelUtil<ZcItem>(ZcItem.class);
        List<ZcItem> itemList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = zcItemService.importItem(itemList, updateSupport, operName);
        return success(message);
    }


    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<ZcItem> util = new ExcelUtil<ZcItem>(ZcItem.class);
        util.importTemplateExcel(response, "物料数据");
    }
}
