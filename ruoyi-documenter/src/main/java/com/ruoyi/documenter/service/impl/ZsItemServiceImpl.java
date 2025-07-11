package com.ruoyi.documenter.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.documenter.mapper.ZsItemMapper;
import com.ruoyi.documenter.domain.ZsItem;
import com.ruoyi.documenter.service.IZsItemService;

/**
 * 物料Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-10
 */
@Service
public class ZsItemServiceImpl implements IZsItemService 
{
    @Autowired
    private ZsItemMapper zsItemMapper;

    /**
     * 查询物料
     * 
     * @param itemId 物料主键
     * @return 物料
     */
    @Override
    public ZsItem selectZsItemByItemId(Long itemId)
    {
        return zsItemMapper.selectZsItemByItemId(itemId);
    }

    /**
     * 查询物料列表
     * 
     * @param zsItem 物料
     * @return 物料
     */
    @Override
    public List<ZsItem> selectZsItemList(ZsItem zsItem)
    {
        return zsItemMapper.selectZsItemList(zsItem);
    }

    /**
     * 新增物料
     * 
     * @param zsItem 物料
     * @return 结果
     */
    @Override
    public int insertZsItem(ZsItem zsItem)
    {
        zsItem.setCreateTime(DateUtils.getNowDate());
        return zsItemMapper.insertZsItem(zsItem);
    }

    /**
     * 修改物料
     * 
     * @param zsItem 物料
     * @return 结果
     */
    @Override
    public int updateZsItem(ZsItem zsItem)
    {
        zsItem.setUpdateTime(DateUtils.getNowDate());
        return zsItemMapper.updateZsItem(zsItem);
    }

    /**
     * 批量删除物料
     * 
     * @param itemIds 需要删除的物料主键
     * @return 结果
     */
    @Override
    public int deleteZsItemByItemIds(Long[] itemIds)
    {
        return zsItemMapper.deleteZsItemByItemIds(itemIds);
    }

    /**
     * 删除物料信息
     * 
     * @param itemId 物料主键
     * @return 结果
     */
    @Override
    public int deleteZsItemByItemId(Long itemId)
    {
        return zsItemMapper.deleteZsItemByItemId(itemId);
    }
}
