package com.zc.documenter.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.documenter.domain.ZsUnit;

/**
 * 单位管理Service接口
 *
 * @author ruoyi
 * @date 2025-07-16
 */
public interface IZsUnitService extends IService<ZsUnit>
{
    /**
     * 查询单位管理
     *
     * @param unitId 单位管理主键
     * @return 单位管理
     */
    public ZsUnit selectZsUnitByUnitId(Long unitId);

    /**
     * 查询单位管理列表
     *
     * @param zsUnit 单位管理
     * @return 单位管理集合
     */
    public List<ZsUnit> selectZsUnitList(ZsUnit zsUnit);

    /**
     * 新增单位管理
     *
     * @param zsUnit 单位管理
     * @return 结果
     */
    public int insertZsUnit(ZsUnit zsUnit);

    /**
     * 修改单位管理
     *
     * @param zsUnit 单位管理
     * @return 结果
     */
    public int updateZsUnit(ZsUnit zsUnit);

    /**
     * 批量删除单位管理
     *
     * @param unitIds 需要删除的单位管理主键集合
     * @return 结果
     */
    public int deleteZsUnitByUnitIds(Long[] unitIds);

    /**
     * 删除单位管理信息
     *
     * @param unitId 单位管理主键
     * @return 结果
     */
    public int deleteZsUnitByUnitId(Long unitId);
}
