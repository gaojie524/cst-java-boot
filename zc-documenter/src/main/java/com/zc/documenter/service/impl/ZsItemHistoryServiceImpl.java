package com.zc.documenter.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zc.documenter.mapper.ZsItemHistoryMapper;
import com.zc.documenter.domain.ZsItemHistory;
import com.zc.documenter.service.IZsItemHistoryService;

/**
 * 物料历史
Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-10
 */
@Service
public class ZsItemHistoryServiceImpl extends ServiceImpl<ZsItemHistoryMapper, ZsItemHistory> implements IZsItemHistoryService
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
     * 查询物料历史列表
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
    public int insertZsItemHistory(ZsItemHistory zsItemHistory) {
        return zsItemHistoryMapper.insert(zsItemHistory);
    }

    /**
     * 修改物料历史

     *
     * @param zsItemHistory 物料历史

     * @return 结果
     */
    @Override
    public int updateZsItemHistory(ZsItemHistory zsItemHistory) {
        return zsItemHistoryMapper.updateById(zsItemHistory);

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
