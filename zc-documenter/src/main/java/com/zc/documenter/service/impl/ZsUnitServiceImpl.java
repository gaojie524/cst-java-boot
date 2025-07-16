package com.zc.documenter.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zc.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zc.documenter.mapper.ZsUnitMapper;
import com.zc.documenter.domain.ZsUnit;
import com.zc.documenter.service.IZsUnitService;

/**
 * 单位管理Service业务层处理
 *
 * @author ruoyi
 * @date 2025-07-16
 */
@Service
public class ZsUnitServiceImpl extends ServiceImpl<ZsUnitMapper, ZsUnit> implements IZsUnitService
{
    @Autowired
    private ZsUnitMapper zsUnitMapper;

    /**
     * 查询单位管理
     *
     * @param unitId 单位管理主键
     * @return 单位管理
     */
    @Override
    public ZsUnit selectZsUnitByUnitId(Long unitId)
    {
        return zsUnitMapper.selectZsUnitByUnitId(unitId);
    }

    /**
     * 查询单位管理列表
     *
     * @param zsUnit 单位管理
     * @return 单位管理
     */
    @Override
    public List<ZsUnit> selectZsUnitList(ZsUnit zsUnit)
    {
        return zsUnitMapper.selectZsUnitList(zsUnit);
    }

    /**
     * 新增单位管理
     *
     * @param zsUnit 单位管理
     * @return 结果
     */
    @Override
    public int insertZsUnit(ZsUnit zsUnit)
    {
        return zsUnitMapper.insert(zsUnit);
    }

    /**
     * 修改单位管理
     *
     * @param zsUnit 单位管理
     * @return 结果
     */
    @Override
    public int updateZsUnit(ZsUnit zsUnit)
    {
        return zsUnitMapper.updateById(zsUnit);
    }

    /**
     * 批量删除单位管理
     *
     * @param unitIds 需要删除的单位管理主键
     * @return 结果
     */
    @Override
    public int deleteZsUnitByUnitIds(Long[] unitIds)
    {
        return zsUnitMapper.deleteZsUnitByUnitIds(unitIds);
    }

    /**
     * 删除单位管理信息
     *
     * @param unitId 单位管理主键
     * @return 结果
     */
    @Override
    public int deleteZsUnitByUnitId(Long unitId)
    {
        return zsUnitMapper.deleteZsUnitByUnitId(unitId);
    }
}
