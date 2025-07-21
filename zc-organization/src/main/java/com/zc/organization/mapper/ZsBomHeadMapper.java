package com.zc.organization.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.organization.domain.ZsBomHead;

/**
 * BOM头表Mapper接口
 *
 * @author zc
 * @date 2025-07-20
 */
public interface ZsBomHeadMapper extends BaseMapper<ZsBomHead> {
    /**
     * 查询BOM头表
     *
     * @param bomHeadId BOM头表主键
     * @return BOM头表
     */
    public ZsBomHead selectZsBomHeadByBomHeadId(Long bomHeadId);

    /**
     * 查询BOM头表列表
     *
     * @param zsBomHead BOM头表
     * @return BOM头表集合
     */
    public List<ZsBomHead> selectZsBomHeadList(ZsBomHead zsBomHead);





    /**
     * 删除BOM头表
     *
     * @param bomHeadId BOM头表主键
     * @return 结果
     */
    public int deleteZsBomHeadByBomHeadId(Long bomHeadId);

    /**
     * 批量删除BOM头表
     *
     * @param bomHeadIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZsBomHeadByBomHeadIds(Long[] bomHeadIds);}
