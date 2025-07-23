package com.zc.organization.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.organization.domain.ZcBomLine;

/**
 * BOM行Service接口
 *
 * @author zc
 * @date 2025-07-21
 */
public interface IZcBomLineService extends IService<ZcBomLine> {
    /**
     * 查询BOM行
     *
     * @param bomLineId BOM行主键
     * @return BOM行
     */
    public ZcBomLine selectZcBomLineByBomLineId(Long bomLineId);

    /**
     * 查询BOM行列表
     *
     * @param zcBomLine BOM行
     * @return BOM行集合
     */
    public List<ZcBomLine> selectZcBomLineList(ZcBomLine zcBomLine);

    /**
     * 新增BOM行
     *
     * @param zcBomLine BOM行
     * @return 结果
     */
    public int insertZcBomLine(ZcBomLine zcBomLine);

    /**
     * 修改BOM行
     *
     * @param zcBomLine BOM行
     * @return 结果
     */
    public int updateZcBomLine(ZcBomLine zcBomLine);

    /**
     * 批量删除BOM行
     *
     * @param bomLineIds 需要删除的BOM行主键集合
     * @return 结果
     */
    public int deleteZcBomLineByBomLineIds(Long[] bomLineIds);

    /**
     * 删除BOM行信息
     *
     * @param bomLineId BOM行主键
     * @return 结果
     */
    public int deleteZcBomLineByBomLineId(Long bomLineId);
}