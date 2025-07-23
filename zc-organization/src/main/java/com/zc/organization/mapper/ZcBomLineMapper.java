package com.zc.organization.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.organization.domain.ZcBomLine;
import org.apache.ibatis.annotations.Mapper;

/**
 * BOM行Mapper接口
 *
 * @author zc
 * @date 2025-07-21
 */
@Mapper
public interface ZcBomLineMapper extends BaseMapper<ZcBomLine> {
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
     * 删除BOM行
     *
     * @param bomLineId BOM行主键
     * @return 结果
     */
    public int deleteZcBomLineByBomLineId(Long bomLineId);

    /**
     * 批量删除BOM行
     *
     * @param bomLineIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZcBomLineByBomLineIds(Long[] bomLineIds);
}