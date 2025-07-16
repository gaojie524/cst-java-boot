package com.zc.documenter.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.documenter.domain.ZsItemHistory;

/**
 * 物料历史
Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-10
 */
public interface ZsItemHistoryMapper extends BaseMapper<ZsItemHistory>
{
    /**
     * 查询物料历史

     * 
     * @param eventId 物料历史
主键
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
     * 删除物料历史

     * 
     * @param eventId 物料历史主键
     * @return 结果
     */
    public int deleteZsItemHistoryByEventId(Long eventId);

    /**
     * 批量删除物料历史

     * 
     * @param eventIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZsItemHistoryByEventIds(Long[] eventIds);
}
