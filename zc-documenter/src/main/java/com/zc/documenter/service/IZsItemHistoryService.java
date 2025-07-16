package com.zc.documenter.service;

import java.util.List;
import com.zc.documenter.domain.ZsItemHistory;

/**
 * 物料历史
Service接口
 * 
 * @author ruoyi
 * @date 2025-07-10
 */
public interface IZsItemHistoryService 
{
    /**
     * 查询物料历史

     * 
     * @param eventId 物料历史主键
     * @return 物料历史

     */
    public ZsItemHistory selectZsItemHistoryByEventId(Long eventId);

    /**
     * 查询物料历史列表
     * 
     * @param zsItemHistory 物料历史

     * @return 物料历史集合
     */
    public List<ZsItemHistory> selectZsItemHistoryList(ZsItemHistory zsItemHistory);

    /**
     * 新增物料历史

     * 
     * @param zsItemHistory 物料历史

     * @return 结果
     */
    public int insertZsItemHistory(ZsItemHistory zsItemHistory);

    /**
     * 修改物料历史

     * 
     * @param zsItemHistory 物料历史

     * @return 结果
     */
    public int updateZsItemHistory(ZsItemHistory zsItemHistory);

    /**
     * 批量删除物料历史

     * 
     * @param eventIds 需要删除的物料历史主键集合
     * @return 结果
     */
    public int deleteZsItemHistoryByEventIds(Long[] eventIds);

    /**
     * 删除物料历史信息
     * 
     * @param eventId 物料历史主键
     * @return 结果
     */
    public int deleteZsItemHistoryByEventId(Long eventId);
}
