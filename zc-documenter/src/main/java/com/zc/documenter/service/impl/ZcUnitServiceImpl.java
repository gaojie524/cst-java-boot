package com.zc.documenter.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zc.documenter.mapper.ZcUnitMapper;
import com.zc.documenter.service.IZcUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zc.documenter.domain.ZcUnit;

/**
 * 单位管理Service业务层处理
 *
 * @author ruoyi
 * @date 2025-07-16
 */
@Service
public class ZcUnitServiceImpl extends ServiceImpl<ZcUnitMapper, ZcUnit> implements IZcUnitService
{
    @Autowired
    private ZcUnitMapper zcUnitMapper;

    /**
     * 查询单位管理
     *
     * @param unitId 单位管理主键
     * @return 单位管理
     */
    @Override
    public ZcUnit selectZcUnitByUnitId(Long unitId)
    {
        return zcUnitMapper.selectZcUnitByUnitId(unitId);
    }

    /**
     * 查询单位管理列表
     *
     * @param zcUnit 单位管理
     * @return 单位管理
     */
    @Override
    public List<ZcUnit> selectZcUnitList(ZcUnit zcUnit)
    {
        return zcUnitMapper.selectZcUnitList(zcUnit);
    }

    /**
     * 新增单位管理
     *
     * @param zcUnit 单位管理
     * @return 结果
     */
    @Override
    public int insertZcUnit(ZcUnit zcUnit)
    {
        return zcUnitMapper.insert(zcUnit);
    }

    /**
     * 修改单位管理
     *
     * @param zcUnit 单位管理
     * @return 结果
     */
    @Override
    public int updateZcUnit(ZcUnit zcUnit)
    {
        return zcUnitMapper.updateById(zcUnit);
    }

    /**
     * 批量删除单位管理
     *
     * @param unitIds 需要删除的单位管理主键
     * @return 结果
     */
    @Override
    public int deleteZcUnitByUnitIds(Long[] unitIds)
    {
        return zcUnitMapper.deleteZcUnitByUnitIds(unitIds);
    }

    /**
     * 删除单位管理信息
     *
     * @param unitId 单位管理主键
     * @return 结果
     */
    @Override
    public int deleteZcUnitByUnitId(Long unitId)
    {
        return zcUnitMapper.deleteZcUnitByUnitId(unitId);
    }
}