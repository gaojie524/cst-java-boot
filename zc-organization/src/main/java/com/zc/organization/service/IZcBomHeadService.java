package com.zc.organization.service;

import java.util.List;

import com.zc.common.core.domain.TreeSelect;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.organization.domain.ZcBomHead;

/**
 * BOM头表Service接口
 *
 * @author zc
 * @date 2025-07-20
 */
public interface IZcBomHeadService extends IService<ZcBomHead> {
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
     * 新增BOM头表
     *
     * @param zcBomHead BOM头表
     * @return 结果
     */
    public int insertZcBomHead(ZcBomHead zcBomHead);

    /**
     * 修改BOM头表
     *
     * @param zcBomHead BOM头表
     * @return 结果
     */
    public int updateZcBomHead(ZcBomHead zcBomHead);

    /**
     * 批量删除BOM头表
     *
     * @param bomHeadIds 需要删除的BOM头表主键集合
     * @return 结果
     */
    public int deleteZcBomHeadByBomHeadIds(Long[] bomHeadIds);

    /**
     * 删除BOM头表信息
     *
     * @param bomHeadId BOM头表主键
     * @return 结果
     */
    public int deleteZcBomHeadByBomHeadId(Long bomHeadId);

    /**
     * 查询BOM树结构信息
     *
     * @param
     * @return 部门树信息集合
     */
    public List<TreeSelect> selectBomTreeList(ZcBomHead zcBomHead);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param zcBomHeads 部门列表
     * @return 下拉树结构列表
     */
    List<TreeSelect> buildBOMTreeSelect(List<ZcBomHead> zcBomHeads);
}