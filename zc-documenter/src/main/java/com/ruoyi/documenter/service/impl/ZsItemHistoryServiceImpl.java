package com.ruoyi.documenter.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.documenter.mapper.ZsItemHistoryMapper;
import com.ruoyi.documenter.domain.ZsItemHistory;
import com.ruoyi.documenter.service.IZsItemHistoryService;

/**
 * 物料历史
Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-10
 */
@Service
public class ZsItemHistoryServiceImpl implements IZsItemHistoryService 
{
    @Autowired
    private ZsItemHistoryMapper zsItemHistoryMapper;

    /**
     * 查询物料历史

     * 
     * @param eventId 物料历史
主键
     * @return 物料历史

     */
    @Override
    public ZsItemHistory selectZsItemHistoryByEventId(Long eventId)
    {
        return zsItemHistoryMapper.selectZsItemHistoryByEventId(eventId);
    }

    /**
     * 查询物料历史
列表
     * 
     * @param zsItemHistory 物料历史

     * @return 物料历史

     */
    @Override
    public List<ZsItemHistory> selectZsItemHistoryList(ZsItemHistory zsItemHistory)
    {
        return zsItemHistoryMapper.selectZsItemHistoryList(zsItemHistory);
    }

    /**
     * 新增物料历史

     * 
     * @param zsItemHistory 物料历史

     * @return 结果
     */
    @Override
    public int insertZsItemHistory(ZsItemHistory zsItemHistory)
    {
        zsItemHistory.setCreateTime(DateUtils.getNowDate());
        return zsItemHistoryMapper.insertZsItemHistory(zsItemHistory);
    }

    /**
     * 修改物料历史

     * 
     * @param zsItemHistory 物料历史

     * @return 结果
     */
    @Override
    public int updateZsItemHistory(ZsItemHistory zsItemHistory)
    {
        zsItemHistory.setUpdateTime(DateUtils.getNowDate());
        return zsItemHistoryMapper.updateZsItemHistory(zsItemHistory);
    }

    /**
     * 批量删除物料历史

     * 
     * @param eventIds 需要删除的物料历史
主键
     * @return 结果
     */
    @Override
    public int deleteZsItemHistoryByEventIds(Long[] eventIds)
    {
        return zsItemHistoryMapper.deleteZsItemHistoryByEventIds(eventIds);
    }

    /**
     * 删除物料历史信息
     * 
     * @param eventId 物料历史主键
     * @return 结果
     */
    @Override
    public int deleteZsItemHistoryByEventId(Long eventId)
    {
        return zsItemHistoryMapper.deleteZsItemHistoryByEventId(eventId);
    }
}
