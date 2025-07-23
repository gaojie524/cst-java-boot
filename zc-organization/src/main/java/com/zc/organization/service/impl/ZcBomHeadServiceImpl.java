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
    public List<TreeSelect> selectBomTreeList(ZcBomHead zcBomHead)
    {
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
    public List<TreeSelect> buildBOMTreeSelect(List<ZcBomHead> zcBomHeads)
    {
        List<ZcBomHead> bomTrees = recursionFn(zcBomHeads);
        List<TreeSelect> treeSelects = new ArrayList<>();
        bomTrees.forEach(tree -> {
            TreeSelect treeSelect = new TreeSelect();  // 先创建一个 TreeSelect 对象

            // 手动设置 TreeSelect 对象的属性
            treeSelect.setId(tree.getBomHeadId());  // 设置 id
            treeSelect.setLabel(tree.getItemCode());  // 设置 label

            // 处理 children，递归地将 ZcBomHead 的 children 转换为 TreeSelect 对象
            List<TreeSelect> children = tree.getChildren().stream()
                    .map(child -> {
                        TreeSelect childSelect = new TreeSelect();  // 创建子节点 TreeSelect
                        childSelect.setId(child.getBomLineId());  // 设置子节点 id
                        childSelect.setLabel(child.getItemCode());  // 设置子节点 label
                        childSelect.setParentId(child.getBomHeadId()); //设置父级id
                        return childSelect;
                    })
                    .collect(Collectors.toList());

            treeSelect.setChildren(children);  // 设置 children

            treeSelects.add(treeSelect);  // 将构造好的 TreeSelect 对象添加到列表
        });

        return treeSelects;
    }

    /**
     * 递归列表
     */
    private List<ZcBomHead> recursionFn(List<ZcBomHead> list) {
        // 得到所有子级列表
        List<ZcBomLine> childList = zcBomLineMapper.selectZcBomLineList(null);

        // 遍历父级列表
        for (ZcBomHead bomHead : list) {
            // 获取当前父级的ID
            Long parentId = bomHead.getBomHeadId();

            // 根据父级ID过滤出该父级的直接子级
            List<ZcBomLine> children = new ArrayList<>();
            for (ZcBomLine bomLine : childList) {
                if (bomLine.getBomHeadId().equals(parentId)) {
                    children.add(bomLine);  // 将直接子级添加到父级的children中
                }
            }

            // 设置父级的children属性
            bomHead.setChildren(children);
        }

        return list;
    }


}