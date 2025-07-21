package com.zc.organization.service;

import java.util.List;
import com.zc.organization.domain.ZsBomLine;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * BOM行Service接口
 *
 * @author zc
 * @date 2025-07-21
 */
public interface IZsBomLineService extends IService<ZsBomLine> {
    /**
     * 查询BOM行
     *
     * @param bomLineId BOM行主键
     * @return BOM行
     */
    public ZsBomLine selectZsBomLineByBomLineId(Long bomLineId);

    /**
     * 查询BOM行列表
     *
     * @param zsBomLine BOM行
     * @return BOM行集合
     */
    public List<ZsBomLine> selectZsBomLineList(ZsBomLine zsBomLine);

    /**
     * 新增BOM行
     *
     * @param zsBomLine BOM行
     * @return 结果
     */
    public int insertZsBomLine(ZsBomLine zsBomLine);

    /**
     * 修改BOM行
     *
     * @param zsBomLine BOM行
     * @return 结果
     */
    public int updateZsBomLine(ZsBomLine zsBomLine);

    /**
     * 批量删除BOM行
     *
     * @param bomLineIds 需要删除的BOM行主键集合
     * @return 结果
     */
    public int deleteZsBomLineByBomLineIds(Long[] bomLineIds);

    /**
     * 删除BOM行信息
     *
     * @param bomLineId BOM行主键
     * @return 结果
     */
    public int deleteZsBomLineByBomLineId(Long bomLineId);
}