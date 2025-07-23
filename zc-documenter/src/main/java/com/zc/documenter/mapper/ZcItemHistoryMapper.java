package com.zc.documenter.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.documenter.domain.ZcItemHistory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 物料历史
Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-10
 */
@Mapper
public interface ZcItemHistoryMapper extends BaseMapper<ZcItemHistory>
{
    /**
     * 查询物料历史

     * 
     * @param eventId 物料历史
主键
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
     * 删除物料历史

     * 
     * @param eventId 物料历史主键
     * @return 结果
     */
    public int deleteZcItemHistoryByEventId(Long eventId);

    /**
     * 批量删除物料历史

     * 
     * @param eventIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZcItemHistoryByEventIds(Long[] eventIds);
}