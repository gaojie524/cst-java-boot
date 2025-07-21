package com.zc.organization.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.organization.domain.ZsBomLine;

/**
 * BOM行Mapper接口
 *
 * @author zc
 * @date 2025-07-21
 */
public interface ZsBomLineMapper extends BaseMapper<ZsBomLine> {
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
     * 删除BOM行
     *
     * @param bomLineId BOM行主键
     * @return 结果
     */
    public int deleteZsBomLineByBomLineId(Long bomLineId);

    /**
     * 批量删除BOM行
     *
     * @param bomLineIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZsBomLineByBomLineIds(Long[] bomLineIds);
}
