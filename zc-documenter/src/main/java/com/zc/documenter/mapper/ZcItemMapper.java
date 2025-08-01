package com.zc.documenter.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.documenter.domain.ZcItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * 物料Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-10
 */
@Mapper
public interface ZcItemMapper extends BaseMapper<ZcItem>
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
     * 删除物料
     * 
     * @param itemId 物料主键
     * @return 结果
     */
    public int deleteZcItemByItemId(Long itemId);

    /**
     * 批量删除物料
     * 
     * @param itemIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZcItemByItemIds(Long[] itemIds);

    /**
     * 通过物料编号查询物料
     *
     * @param itemCode 物料编号
     * @return 物料对象信息
     */
    public ZcItem selectItemByItemCode(String itemCode);
}