package com.zc.documenter.service;

import java.util.List;
import com.zc.documenter.domain.ZcItemHistory;

/**
 * 物料历史
Service接口
 * 
 * @author ruoyi
 * @date 2025-07-10
 */
public interface IZcItemHistoryService 
{
    /**
     * 查询物料历史

     * 
     * @param eventId 物料历史主键
     * @return 物料历史

     */
    public ZcItemHistory selectZcItemHistoryByEventId(Long eventId);

    /**
     * 查询物料历史列表
     * 
     * @param zcItemHistory 物料历史

     * @return 物料历史集合
     */
    public List<ZcItemHistory> selectZcItemHistoryList(ZcItemHistory zcItemHistory);

    /**
     * 新增物料历史

     * 
     * @param zcItemHistory 物料历史

     * @return 结果
     */
    public int insertZcItemHistory(ZcItemHistory zcItemHistory);

    /**
     * 修改物料历史

     * 
     * @param zcItemHistory 物料历史

     * @return 结果
     */
    public int updateZcItemHistory(ZcItemHistory zcItemHistory);

    /**
     * 批量删除物料历史

     * 
     * @param eventIds 需要删除的物料历史主键集合
     * @return 结果
     */
    public int deleteZcItemHistoryByEventIds(Long[] eventIds);

    /**
     * 删除物料历史信息
     * 
     * @param eventId 物料历史主键
     * @return 结果
     */
    public int deleteZcItemHistoryByEventId(Long eventId);
}