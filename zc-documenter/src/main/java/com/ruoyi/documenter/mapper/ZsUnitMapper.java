package com.ruoyi.documenter.mapper;

import java.util.List;
import com.ruoyi.documenter.domain.ZsUnit;

/**
 * 单位Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
public interface ZsUnitMapper 
{
    /**
     * 查询单位
     * 
     * @param unitId 单位主键
     * @return 单位
     */
    public ZsUnit selectZsUnitByUnitId(Long unitId);

    /**
     * 查询单位列表
     * 
     * @param zsUnit 单位
     * @return 单位集合
     */
    public List<ZsUnit> selectZsUnitList(ZsUnit zsUnit);

    /**
     * 新增单位
     * 
     * @param zsUnit 单位
     * @return 结果
     */
    public int insertZsUnit(ZsUnit zsUnit);

    /**
     * 修改单位
     * 
     * @param zsUnit 单位
     * @return 结果
     */
    public int updateZsUnit(ZsUnit zsUnit);

    /**
     * 删除单位
     * 
     * @param unitId 单位主键
     * @return 结果
     */
    public int deleteZsUnitByUnitId(Long unitId);

    /**
     * 批量删除单位
     * 
     * @param unitIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZsUnitByUnitIds(Long[] unitIds);
}
