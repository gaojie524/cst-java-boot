package com.zc.documenter.service;

import java.util.List;

import com.zc.documenter.domain.ZcItem;

/**
 * 物料Service接口
 * 
 * @author ruoyi
 * @date 2025-07-10
 */
public interface IZcItemService 
{
    /**
     * 查询物料
     * 
     * @param itemId 物料主键
     * @return 物料
     */
    public ZcItem selectZcItemByItemId(Long itemId);

    /**
     * 查询物料列表
     * 
     * @param zcItem 物料
     * @return 物料集合
     */
    public List<ZcItem> selectZcItemList(ZcItem zcItem);

    /**
     * 新增物料
     * 
     * @param zcItem 物料
     * @return 结果
     */
    public int insertZcItem(ZcItem zcItem);

    /**
     * 修改物料
     * 
     * @param zcItem 物料
     * @return 结果
     */
    public int updateZcItem(ZcItem zcItem);

    /**
     * 批量删除物料
     * 
     * @param itemIds 需要删除的物料主键集合
     * @return 结果
     */
    public int deleteZcItemByItemIds(Long[] itemIds);

    /**
     * 删除物料信息
     * 
     * @param itemId 物料主键
     * @return 结果
     */
    public int deleteZcItemByItemId(Long itemId);

    /**
     * 导入物料数据
     *
     * @param itemList 物料数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importItem(List<ZcItem> itemList, Boolean isUpdateSupport, String operName);

    /**
     * 通过物料编号查询物料
     *
     * @param itemCode 物料编号
     * @return 物料对象信息
     */
    public ZcItem selectItemByItemCode(String itemCode);
}