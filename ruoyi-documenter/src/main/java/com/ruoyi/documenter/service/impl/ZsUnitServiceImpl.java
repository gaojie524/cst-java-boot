package com.ruoyi.documenter.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.documenter.mapper.ZsUnitMapper;
import com.ruoyi.documenter.domain.ZsUnit;
import com.ruoyi.documenter.service.IZsUnitService;

/**
 * 单位Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
@Service
public class ZsUnitServiceImpl implements IZsUnitService 
{
    @Autowired
    private ZsUnitMapper zsUnitMapper;

    /**
     * 查询单位
     * 
     * @param unitId 单位主键
     * @return 单位
     */
    @Override
    public ZsUnit selectZsUnitByUnitId(Long unitId)
    {
        return zsUnitMapper.selectZsUnitByUnitId(unitId);
    }

    /**
     * 查询单位列表
     * 
     * @param zsUnit 单位
     * @return 单位
     */
    @Override
    public List<ZsUnit> selectZsUnitList(ZsUnit zsUnit)
    {
        return zsUnitMapper.selectZsUnitList(zsUnit);
    }

    /**
     * 新增单位
     * 
     * @param zsUnit 单位
     * @return 结果
     */
    @Override
    public int insertZsUnit(ZsUnit zsUnit)
    {
        zsUnit.setCreateTime(DateUtils.getNowDate());
        return zsUnitMapper.insertZsUnit(zsUnit);
    }

    /**
     * 修改单位
     * 
     * @param zsUnit 单位
     * @return 结果
     */
    @Override
    public int updateZsUnit(ZsUnit zsUnit)
    {
        zsUnit.setUpdateTime(DateUtils.getNowDate());
        return zsUnitMapper.updateZsUnit(zsUnit);
    }

    /**
     * 批量删除单位
     * 
     * @param unitIds 需要删除的单位主键
     * @return 结果
     */
    @Override
    public int deleteZsUnitByUnitIds(Long[] unitIds)
    {
        return zsUnitMapper.deleteZsUnitByUnitIds(unitIds);
    }

    /**
     * 删除单位信息
     * 
     * @param unitId 单位主键
     * @return 结果
     */
    @Override
    public int deleteZsUnitByUnitId(Long unitId)
    {
        return zsUnitMapper.deleteZsUnitByUnitId(unitId);
    }
}
