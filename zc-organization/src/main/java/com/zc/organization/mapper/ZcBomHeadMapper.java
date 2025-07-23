package com.zc.organization.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.organization.domain.ZcBomHead;
import org.apache.ibatis.annotations.Mapper;

/**
 * BOM头表Mapper接口
 *
 * @author zc
 * @date 2025-07-20
 */
@Mapper
public interface ZcBomHeadMapper extends BaseMapper<ZcBomHead> {
    /**
     * 查询BOM头表
     *
     * @param bomHeadId BOM头表主键
     * @return BOM头表
     */
    public ZcBomHead selectZcBomHeadByBomHeadId(Long bomHeadId);

    /**
     * 查询BOM头表列表
     *
     * @param zcBomHead BOM头表
     * @return BOM头表集合
     */
    public List<ZcBomHead> selectZcBomHeadList(ZcBomHead zcBomHead);

    /**
     * 删除BOM头表
     *
     * @param bomHeadId BOM头表主键
     * @return 结果
     */
    public int deleteZcBomHeadByBomHeadId(Long bomHeadId);

    /**
     * 批量删除BOM头表
     *
     * @param bomHeadIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZcBomHeadByBomHeadIds(Long[] bomHeadIds);
}