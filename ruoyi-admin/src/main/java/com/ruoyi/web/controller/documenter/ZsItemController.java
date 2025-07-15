package com.ruoyi.web.controller.documenter;

import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.documenter.domain.ZsItemHistory;
import com.ruoyi.documenter.service.IZsItemHistoryService;

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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.documenter.domain.ZsItem;
import com.ruoyi.documenter.service.IZsItemService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 物料Controller
 * 
 * @author ruoyi
 * @date 2025-07-10
 */
@RestController
@RequestMapping("/documenter/item")
public class ZsItemController extends BaseController
{
    @Autowired
    private IZsItemService zsItemService;
    @Autowired
    private IZsItemHistoryService zsItemHistoryService;


    /**
     * 查询物料列表
     */
    @PreAuthorize("@ss.hasPermi('documenter:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(ZsItem zsItem)
    {
        startPage();
        List<ZsItem> list = zsItemService.selectZsItemList(zsItem);
        return getDataTable(list);
    }

    /**
     * 导出物料列表
     */
    @PreAuthorize("@ss.hasPermi('documenter:item:export')")
    @Log(title = "物料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ZsItem zsItem)
    {
        List<ZsItem> list = zsItemService.selectZsItemList(zsItem);
        ExcelUtil<ZsItem> util = new ExcelUtil<ZsItem>(ZsItem.class);
        util.exportExcel(response, list, "物料数据");
    }

    /**
     * 获取物料详细信息
     */
    @PreAuthorize("@ss.hasPermi('documenter:item:query')")
    @GetMapping(value = "/{itemId}")
    public AjaxResult getInfo(@PathVariable("itemId") Long itemId)
    {
        return success(zsItemService.selectZsItemByItemId(itemId));
    }

    /**
     * 新增物料
     */
    @PreAuthorize("@ss.hasPermi('documenter:item:add')")
    @Log(title = "物料", businessType = BusinessType.INSERT)
    @PostMapping
    @Transactional  // 添加事务注解
    public AjaxResult add(@RequestBody ZsItem zsItem)
    {
        ZsItemHistory zsItemHistory = new ZsItemHistory();
        zsItem.setItemId((long)Math.abs(UUID.randomUUID().toString().hashCode()));
        zsItem.setItemCode(UUID.randomUUID().toString());
        zsItem.setCreateBy(getUsername());
        zsItem.setUpdateBy(getUsername());
        //往物料历史表 增加记录
        BeanUtils.copyProperties(zsItem, zsItemHistory);
        //todo 这里的数据字典将来是否改成枚举
        zsItemHistory.setOperation("0");
        zsItemHistoryService.insertZsItemHistory(zsItemHistory);
        return toAjax(zsItemService.insertZsItem(zsItem));
    }

    /**
     * 修改物料
     */
    @PreAuthorize("@ss.hasPermi('documenter:item:edit')")
    @Log(title = "物料", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ZsItem zsItem)
    {
        ZsItemHistory zsItemHistory = new ZsItemHistory();
        zsItem.setUpdateBy(getUsername());
        //往物料历史表 增加记录
        BeanUtils.copyProperties(zsItem, zsItemHistory);
        zsItemHistory.setOperation("1");
        zsItemHistoryService.insertZsItemHistory(zsItemHistory);
        return toAjax(zsItemService.updateZsItem(zsItem));
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
            ZsItem zsItem = zsItemService.selectZsItemByItemId(itemId);
            ZsItemHistory zsItemHistory = new ZsItemHistory();
            BeanUtils.copyProperties(zsItem,zsItemHistory);
            zsItemHistory.setOperation("2");
            zsItemHistory.setUpdateBy(getUsername());
            zsItemHistoryService.insertZsItemHistory(zsItemHistory);
        }
        return toAjax(zsItemService.deleteZsItemByItemIds(itemIds));
    }


    @Log(title = "物料管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('documenter:item:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ZsItem> util = new ExcelUtil<ZsItem>(ZsItem.class);
        List<ZsItem> itemList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = zsItemService.importItem(itemList, updateSupport, operName);
        return success(message);
    }


    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<ZsItem> util = new ExcelUtil<ZsItem>(ZsItem.class);
        util.importTemplateExcel(response, "物料数据");
    }
}
