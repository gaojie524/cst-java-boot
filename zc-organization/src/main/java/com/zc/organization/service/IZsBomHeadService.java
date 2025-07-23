package com.zc.organization.service;

import java.util.List;


import com.zc.organization.domain.ZsBomHead;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * BOM头表Service接口
 *
 * @author zc
 * @date 2025-07-20
 */
public interface IZsBomHeadService extends IService<ZsBomHead> {
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
     * 新增BOM头表
     *
     * @param zsBomHead BOM头表
     * @return 结果
     */
    public int insertZsBomHead(ZsBomHead zsBomHead);

    /**
     * 修改BOM头表
     *
     * @param zsBomHead BOM头表
     * @return 结果
     */
    public int updateZsBomHead(ZsBomHead zsBomHead);

    /**
     * 批量删除BOM头表
     *
     * @param bomHeadIds
     * @return 结果
     */
    public int deleteZsBomHeadByBomHeadIds(Long[] bomHeadIds);

    /**
     * 删除BOM头表信息
     *
     * @param bomHeadId BOM头表主键
     * @return 结果
     */
    public int deleteZsBomHeadByBomHeadId(Long bomHeadId);

    /**
     * 查询BOM树结构信息
     *
     * @param
     * @return 部门树信息集合
     */
    public List<ZsBomHead> selectBomTreeList(ZsBomHead zsBomHead);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param zsBomHeads 部门列表
     * @return 下拉树结构列表
     */
    List<ZsBomHead> buildBOMTreeSelect(List<ZsBomHead> zsBomHeads);


}