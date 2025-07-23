package com.zc.documenter.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.documenter.mapper.ZcItemHistoryMapper;
import com.zc.documenter.service.IZcItemHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zc.documenter.domain.ZcItemHistory;

/**
 * 物料历史
Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-10
 */
@Service
public class ZcItemHistoryServiceImpl extends ServiceImpl<ZcItemHistoryMapper, ZcItemHistory> implements IZcItemHistoryService
{
    @Autowired
    private ZcItemHistoryMapper zcItemHistoryMapper;

    /**
     * 查询物料历史
     * 
     * @param eventId 物料历史
主键
     * @return 物料历史
     */
    @Override
    public ZcItemHistory selectZcItemHistoryByEventId(Long eventId)
    {
        return zcItemHistoryMapper.selectZcItemHistoryByEventId(eventId);
    }

    /**
     * 查询物料历史列表
     * 
     * @param zcItemHistory 物料历史
     * @return 物料历史
     */
    @Override
    public List<ZcItemHistory> selectZcItemHistoryList(ZcItemHistory zcItemHistory)
    {
        return zcItemHistoryMapper.selectZcItemHistoryList(zcItemHistory);
    }

    /**
     * 新增物料历史
     *
     * @param zcItemHistory 物料历史
     * @return 结果
     */
    @Override
    public int insertZcItemHistory(ZcItemHistory zcItemHistory) {
        return zcItemHistoryMapper.insert(zcItemHistory);
    }

    /**
     * 修改物料历史
     *
     * @param zcItemHistory 物料历史
     * @return 结果
     */
    @Override
    public int updateZcItemHistory(ZcItemHistory zcItemHistory) {
        return zcItemHistoryMapper.updateById(zcItemHistory);
    }

    /**
     * 批量删除物料历史
     * 
     * @param eventIds 需要删除的物料历史
主键
     * @return 结果
     */
    @Override
    public int deleteZcItemHistoryByEventIds(Long[] eventIds)
    {
        return zcItemHistoryMapper.deleteZcItemHistoryByEventIds(eventIds);
    }

    /**
     * 删除物料历史信息
     * 
     * @param eventId 物料历史主键
     * @return 结果
     */
    @Override
    public int deleteZcItemHistoryByEventId(Long eventId)
    {
        return zcItemHistoryMapper.deleteZcItemHistoryByEventId(eventId);
    }
}