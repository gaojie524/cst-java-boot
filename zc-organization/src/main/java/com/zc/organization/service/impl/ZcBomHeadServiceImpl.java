package com.zc.organization.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import com.zc.common.core.domain.TreeSelect;
import com.zc.common.utils.spring.SpringUtils;
import com.zc.organization.domain.ZcBomHead;
import com.zc.organization.domain.ZcBomLine;
import com.zc.organization.mapper.ZcBomHeadMapper;
import com.zc.organization.mapper.ZcBomLineMapper;
import com.zc.organization.service.IZcBomHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BOM头表Service业务层处理
 *
 * @author zc
 * @date 2025-07-20
 */
@Service
public class ZcBomHeadServiceImpl extends ServiceImpl<ZcBomHeadMapper, ZcBomHead> implements IZcBomHeadService {
    @Autowired
    private ZcBomHeadMapper zcBomHeadMapper;

    @Autowired
    private ZcBomLineMapper zcBomLineMapper;

    /**
     * 查询BOM头表
     *
     * @param bomHeadId BOM头表主键
     * @return BOM头表
     */
    @Override
    public ZcBomHead selectZcBomHeadByBomHeadId(Long bomHeadId) {
        return zcBomHeadMapper.selectZcBomHeadByBomHeadId(bomHeadId);
    }

    /**
     * 查询BOM头表列表
     *
     * @param zcBomHead BOM头表
     * @return BOM头表
     */
    @Override
    public List<ZcBomHead> selectZcBomHeadList(ZcBomHead zcBomHead) {
        return zcBomHeadMapper.selectZcBomHeadList(zcBomHead);
    }

    /**
     * 新增BOM头表
     *
     * @param zcBomHead BOM头表
     * @return 结果
     */
    @Override
    public int insertZcBomHead(ZcBomHead zcBomHead) {
            return zcBomHeadMapper.insert(zcBomHead);
    }

    /**
     * 修改BOM头表
     *
     * @param zcBomHead BOM头表
     * @return 结果
     */
    @Override
    public int updateZcBomHead(ZcBomHead zcBomHead) {
        return zcBomHeadMapper.updateById(zcBomHead);

    }

    /**
     * 批量删除BOM头表
     *
     * @param bomHeadIds 需要删除的BOM头表主键
     * @return 结果
     */
    @Override
    public int deleteZcBomHeadByBomHeadIds(Long[] bomHeadIds) {
        return zcBomHeadMapper.deleteZcBomHeadByBomHeadIds(bomHeadIds);
    }

    /**
     * 删除BOM头表信息
     *
     * @param bomHeadId BOM头表主键
     * @return 结果
     */
    @Override
    public int deleteZcBomHeadByBomHeadId(Long bomHeadId) {
        return zcBomHeadMapper.deleteZcBomHeadByBomHeadId(bomHeadId);
    }

    /**
     * 查询BOM树结构信息
     *
     * @param
     * @return 部门树信息集合
     */
    @Override
    public List<ZcBomHead> selectBomTreeList(ZcBomHead zcBomHead)
    {
        //只查有效的记录
        zcBomHead.setBomStatus("Y");
        List<ZcBomHead> zcBomHeads = SpringUtils.getAopProxy(this).selectZcBomHeadList(zcBomHead);

        return buildBOMTreeSelect(zcBomHeads);
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param zcBomHeads BOM头列表
     * @return 下拉树结构列表
     */
    @Override
    public List<ZcBomHead> buildBOMTreeSelect(List<ZcBomHead> zcBomHeads)
    {
        // 得到所有子级列表
        List<ZcBomLine> childList = zcBomLineMapper.selectZcBomLineList(null);

        List<ZcBomHead> returnList = new ArrayList<ZcBomHead>();

        // 遍历ZcBomHead和ZcBomLine表
        for (ZcBomHead zcBomHead : zcBomHeads) {
            // 查找匹配的子级
            // 过滤出符合条件的子级
            List<ZcBomLine> matchingChildren = childList.stream()
                    .filter(child -> zcBomHead.getParentItemId().equals(child.getChildItemId()))
                    .collect(Collectors.toList());

            // 如果有匹配的子级，提取该对象
            if (!matchingChildren.isEmpty()) {
                continue;
            }
            recursionFn(returnList,zcBomHeads,childList,zcBomHead);
            returnList.add(zcBomHead);
        }




        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<ZcBomHead> returnList, List<ZcBomHead> zcBomHeads, List<ZcBomLine> childList, ZcBomHead zcBomHead) {

        for (ZcBomLine zcBomLine : childList) {
            if (zcBomHead.getBomHeadId().equals(zcBomLine.getBomHeadId())) {
                List<ZcBomHead> children = zcBomHead.getChildren();

                // 查找匹配的父级
                ZcBomHead matchingParent = zcBomHeads.stream()
                        .filter(parentHead -> parentHead.getParentItemId().equals(zcBomLine.getChildItemId()))
                        .findFirst()
                        .orElse(null);

                if (matchingParent != null) {
                    // 设置父级数据
                    matchingParent.setBomLineId(zcBomLine.getBomLineId());
                    matchingParent.setSortOrder(zcBomLine.getSortOrder());
                    matchingParent.setBomHeadLineId(zcBomLine.getBomHeadId());
                    children.add(matchingParent);

                    // 继续递归
                    recursionFn(children, zcBomHeads, childList, matchingParent);

                } else {
                    // 如果没有找到匹配的父级，创建一个新的 ZcBomHead 对象
                    ZcBomHead zcBomHead1 = new ZcBomHead();
                    zcBomHead1.setBomLineId(zcBomLine.getBomLineId());
                    zcBomHead1.setSortOrder(zcBomLine.getSortOrder());
                    zcBomHead1.setParentItemId(zcBomLine.getChildItemId());
                    zcBomHead1.setItemName(zcBomLine.getItemName());
                    zcBomHead1.setItemCode(zcBomLine.getItemCode());
                    zcBomHead1.setBomHeadId(zcBomLine.getBomHeadId());
                    children.add(zcBomHead1);
                }
            }
        }
    }


}