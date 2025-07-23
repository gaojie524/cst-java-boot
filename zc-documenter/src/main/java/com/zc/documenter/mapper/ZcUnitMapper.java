package com.zc.documenter.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.documenter.domain.ZcUnit;
import org.apache.ibatis.annotations.Mapper;

/**
 * 单位管理Mapper接口
 *
 * @author ruoyi
 * @date 2025-07-16
 */
@Mapper
public interface ZcUnitMapper extends BaseMapper<ZcUnit>
{
    /**
     * 查询单位管理
     *
     * @param unitId 单位管理主键
     * @return 单位管理
     */
    public ZcUnit selectZcUnitByUnitId(Long unitId);

    /**
     * 查询单位管理列表
     *
     * @param zcUnit 单位管理
     * @return 单位管理集合
     */
    public List<ZcUnit> selectZcUnitList(ZcUnit zcUnit);

    /**
     * 新增单位管理
     *
     * @param zcUnit 单位管理
     * @return 结果
     */
    public int insertZcUnit(ZcUnit zcUnit);

    /**
     * 修改单位管理
     *
     * @param zcUnit 单位管理
     * @return 结果
     */
    public int updateZcUnit(ZcUnit zcUnit);

    /**
     * 删除单位管理
     *
     * @param unitId 单位管理主键
     * @return 结果
     */
    public int deleteZcUnitByUnitId(Long unitId);

    /**
     * 批量删除单位管理
     *
     * @param unitIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZcUnitByUnitIds(Long[] unitIds);
}