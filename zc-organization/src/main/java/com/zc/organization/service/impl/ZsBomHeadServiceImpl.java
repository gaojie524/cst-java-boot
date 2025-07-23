package com.zc.organization.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.*;
import java.util.stream.Collectors;


import com.zc.common.utils.spring.SpringUtils;
import com.zc.organization.domain.ZsBomLine;
import com.zc.organization.mapper.ZsBomLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zc.organization.mapper.ZsBomHeadMapper;
import com.zc.organization.domain.ZsBomHead;
import com.zc.organization.service.IZsBomHeadService;

/**
 * BOM头表Service业务层处理
 *
 * @author zc
 * @date 2025-07-20
 */
@Service
public class ZsBomHeadServiceImpl extends ServiceImpl<ZsBomHeadMapper, ZsBomHead> implements IZsBomHeadService {
    @Autowired
    private ZsBomHeadMapper zsBomHeadMapper;

    @Autowired
    private ZsBomLineMapper zsBomLineMapper;

    /**
     * 查询BOM头表
     *
     * @param bomHeadId BOM头表主键
     * @return BOM头表
     */
    @Override
    public ZsBomHead selectZsBomHeadByBomHeadId(Long bomHeadId) {
        return zsBomHeadMapper.selectZsBomHeadByBomHeadId(bomHeadId);
    }

    /**
     * 查询BOM头表列表
     *
     * @param zsBomHead BOM头表
     * @return BOM头表
     */
    @Override
    public List<ZsBomHead> selectZsBomHeadList(ZsBomHead zsBomHead) {
        return zsBomHeadMapper.selectZsBomHeadList(zsBomHead);
    }

    /**
     * 新增BOM头表
     *
     * @param zsBomHead BOM头表
     * @return 结果
     */
    @Override
    public int insertZsBomHead(ZsBomHead zsBomHead) {
            return zsBomHeadMapper.insert(zsBomHead);
    }

    /**
     * 修改BOM头表
     *
     * @param zsBomHead BOM头表
     * @return 结果
     */
    @Override
    public int updateZsBomHead(ZsBomHead zsBomHead) {
        return zsBomHeadMapper.updateById(zsBomHead);

    }

    /**
     * 批量删除BOM头表
     *
     * @param zsBomHead
     * @return 结果
     */
    @Override
    public int deleteZsBomHeadByBomHeadIds(Long[] bomHeadIds) {
        return zsBomHeadMapper.deleteZsBomHeadByBomHeadIds(bomHeadIds);
    }

    /**
     * 删除BOM头表信息
     *
     * @param bomHeadId BOM头表主键
     * @return 结果
     */
    @Override
    public int deleteZsBomHeadByBomHeadId(Long bomHeadId) {
        return zsBomHeadMapper.deleteZsBomHeadByBomHeadId(bomHeadId);
    }


    /**
     * 查询BOM树结构信息
     *
     * @param
     * @return 部门树信息集合
     */
    @Override
    public List<ZsBomHead> selectBomTreeList(ZsBomHead zsBomHead)
    {
        //只查有效的记录
        zsBomHead.setBomStatus("Y");
        List<ZsBomHead> zsBomHeads = SpringUtils.getAopProxy(this).selectZsBomHeadList(zsBomHead);

        return buildBOMTreeSelect(zsBomHeads);
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param zsBomHeads BOM头列表
     * @return 下拉树结构列表
     */
    @Override
    public List<ZsBomHead> buildBOMTreeSelect(List<ZsBomHead> zsBomHeads)
    {
        // 得到所有子级列表
        List<ZsBomLine> childList = zsBomLineMapper.selectZsBomLineList(null);

        List<ZsBomHead> returnList = new ArrayList<ZsBomHead>();

        // 遍历ZsBomHead和ZsBomLine表
        for (ZsBomHead zsBomHead : zsBomHeads) {
            // 查找匹配的子级
            // 过滤出符合条件的子级
            List<ZsBomLine> matchingChildren = childList.stream()
                    .filter(child -> zsBomHead.getParentItemId().equals(child.getChildItemId()))
                    .collect(Collectors.toList());

            // 如果有匹配的子级，提取该对象
            if (!matchingChildren.isEmpty()) {
                continue;
            }
            recursionFn(returnList,zsBomHeads,childList,zsBomHead);
            returnList.add(zsBomHead);
        }




        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<ZsBomHead> returnList, List<ZsBomHead> zsBomHeads, List<ZsBomLine> childList, ZsBomHead zsBomHead) {

        for (ZsBomLine zsBomLine : childList) {
            if (zsBomHead.getBomHeadId().equals(zsBomLine.getBomHeadId())) {
                List<ZsBomHead> children = zsBomHead.getChildren();

                // 查找匹配的父级
                ZsBomHead matchingParent = zsBomHeads.stream()
                        .filter(parentHead -> parentHead.getParentItemId().equals(zsBomLine.getChildItemId()))
                        .findFirst()
                        .orElse(null);

                if (matchingParent != null) {
                    // 设置父级数据
                    matchingParent.setBomLineId(zsBomLine.getBomLineId());
                    matchingParent.setSortOrder(zsBomLine.getSortOrder());
                    matchingParent.setBomHeadLineId(zsBomLine.getBomHeadId());
                    children.add(matchingParent);

                    // 继续递归
                    recursionFn(children, zsBomHeads, childList, matchingParent);

                } else {
                    // 如果没有找到匹配的父级，创建一个新的 ZsBomHead 对象
                    ZsBomHead zsBomHead1 = new ZsBomHead();
                    zsBomHead1.setBomLineId(zsBomLine.getBomLineId());
                    zsBomHead1.setSortOrder(zsBomLine.getSortOrder());
                    zsBomHead1.setParentItemId(zsBomLine.getChildItemId());
                    zsBomHead1.setItemName(zsBomLine.getItemName());
                    zsBomHead1.setItemCode(zsBomLine.getItemCode());
                    zsBomHead1.setBomHeadId(zsBomLine.getBomHeadId());
                    children.add(zsBomHead1);
                }
            }
        }
    }

}