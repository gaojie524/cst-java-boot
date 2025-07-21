package com.zc.organization.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import com.zc.common.core.domain.TreeSelect;
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
     * @param bomHeadIds 需要删除的BOM头表主键
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
    public List<TreeSelect> selectBomTreeList(ZsBomHead zsBomHead)
    {
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
    public List<TreeSelect> buildBOMTreeSelect(List<ZsBomHead> zsBomHeads)
    {
        List<ZsBomHead> bomTrees = recursionFn(zsBomHeads);
        List<TreeSelect> treeSelects = new ArrayList<>();
        bomTrees.forEach(tree -> {
            TreeSelect treeSelect = new TreeSelect();  // 先创建一个 TreeSelect 对象

            // 手动设置 TreeSelect 对象的属性
            treeSelect.setId(tree.getBomHeadId());  // 设置 id
            treeSelect.setLabel(tree.getItemCode());  // 设置 label

            // 处理 children，递归地将 ZsBomHead 的 children 转换为 TreeSelect 对象
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
    private List<ZsBomHead> recursionFn(List<ZsBomHead> list) {
        // 得到所有子级列表
        List<ZsBomLine> childList = zsBomLineMapper.selectZsBomLineList(null);

        // 遍历父级列表
        for (ZsBomHead bomHead : list) {
            // 获取当前父级的ID
            Long parentId = bomHead.getBomHeadId();

            // 根据父级ID过滤出该父级的直接子级
            List<ZsBomLine> children = new ArrayList<>();
            for (ZsBomLine bomLine : childList) {
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