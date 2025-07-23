package com.zc.organization.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;

import com.zc.organization.domain.ZcBomLine;
import com.zc.organization.mapper.ZcBomLineMapper;
import com.zc.organization.service.IZcBomLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BOM行Service业务层处理
 *
 * @author zc
 * @date 2025-07-21
 */
@Service
public class ZcBomLineServiceImpl extends ServiceImpl<ZcBomLineMapper, ZcBomLine> implements IZcBomLineService {
    @Autowired
    private ZcBomLineMapper zcBomLineMapper;

    /**
     * 查询BOM行
     *
     * @param bomLineId BOM行主键
     * @return BOM行
     */
    @Override
    public ZcBomLine selectZcBomLineByBomLineId(Long bomLineId) {
        return zcBomLineMapper.selectZcBomLineByBomLineId(bomLineId);
    }

    /**
     * 查询BOM行列表
     *
     * @param zcBomLine BOM行
     * @return BOM行
     */
    @Override
    public List<ZcBomLine> selectZcBomLineList(ZcBomLine zcBomLine) {
        return zcBomLineMapper.selectZcBomLineList(zcBomLine);
    }

    /**
     * 新增BOM行
     *
     * @param zcBomLine BOM行
     * @return 结果
     */
    @Override
    public int insertZcBomLine(ZcBomLine zcBomLine) {
            return zcBomLineMapper.insert(zcBomLine);
    }

    /**
     * 修改BOM行
     *
     * @param zcBomLine BOM行
     * @return 结果
     */
    @Override
    public int updateZcBomLine(ZcBomLine zcBomLine) {
        return zcBomLineMapper.updateById(zcBomLine);

    }

    /**
     * 批量删除BOM行
     *
     * @param bomLineIds 需要删除的BOM行主键
     * @return 结果
     */
    @Override
    public int deleteZcBomLineByBomLineIds(Long[] bomLineIds) {
        return zcBomLineMapper.deleteZcBomLineByBomLineIds(bomLineIds);
    }

    /**
     * 删除BOM行信息
     *
     * @param bomLineId BOM行主键
     * @return 结果
     */
    @Override
    public int deleteZcBomLineByBomLineId(Long bomLineId) {
        return zcBomLineMapper.deleteZcBomLineByBomLineId(bomLineId);
    }
}