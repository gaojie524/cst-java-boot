package com.zc.documenter.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.documenter.domain.ZsItem;

/**
 * 物料Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-10
 */
public interface ZsItemMapper extends BaseMapper<ZsItem>
{
    /**
     * 查询物料
     * 
     * @param itemId 物料主键
     * @return 物料
     */
    public ZsItem selectZsItemByItemId(Long itemId);

    /**
     * 查询物料列表
     * 
     * @param zsItem 物料
     * @return 物料集合
     */
    public List<ZsItem> selectZsItemList(ZsItem zsItem);


    /**
     * 删除物料
     * 
     * @param itemId 物料主键
     * @return 结果
     */
    public int deleteZsItemByItemId(Long itemId);

    /**
     * 批量删除物料
     * 
     * @param itemIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZsItemByItemIds(Long[] itemIds);

    /**
     * 通过物料编号查询物料
     *
     * @param itemCode 物料编号
     * @return 物料对象信息
     */
    public ZsItem selectItemByItemCode(String itemCode);
}
