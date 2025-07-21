package com.zc.organization.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zc.organization.mapper.ZsBomLineMapper;
import com.zc.organization.domain.ZsBomLine;
import com.zc.organization.service.IZsBomLineService;

/**
 * BOM行Service业务层处理
 *
 * @author zc
 * @date 2025-07-21
 */
@Service
public class ZsBomLineServiceImpl extends ServiceImpl<ZsBomLineMapper, ZsBomLine> implements IZsBomLineService {
    @Autowired
    private ZsBomLineMapper zsBomLineMapper;

    /**
     * 查询BOM行
     *
     * @param bomLineId BOM行主键
     * @return BOM行
     */
    @Override
    public ZsBomLine selectZsBomLineByBomLineId(Long bomLineId) {
        return zsBomLineMapper.selectZsBomLineByBomLineId(bomLineId);
    }

    /**
     * 查询BOM行列表
     *
     * @param zsBomLine BOM行
     * @return BOM行
     */
    @Override
    public List<ZsBomLine> selectZsBomLineList(ZsBomLine zsBomLine) {
        return zsBomLineMapper.selectZsBomLineList(zsBomLine);
    }

    /**
     * 新增BOM行
     *
     * @param zsBomLine BOM行
     * @return 结果
     */
    @Override
    public int insertZsBomLine(ZsBomLine zsBomLine) {
            return zsBomLineMapper.insert(zsBomLine);
    }

    /**
     * 修改BOM行
     *
     * @param zsBomLine BOM行
     * @return 结果
     */
    @Override
    public int updateZsBomLine(ZsBomLine zsBomLine) {
        return zsBomLineMapper.updateById(zsBomLine);

    }

    /**
     * 批量删除BOM行
     *
     * @param bomLineIds 需要删除的BOM行主键
     * @return 结果
     */
    @Override
    public int deleteZsBomLineByBomLineIds(Long[] bomLineIds) {
        return zsBomLineMapper.deleteZsBomLineByBomLineIds(bomLineIds);
    }

    /**
     * 删除BOM行信息
     *
     * @param bomLineId BOM行主键
     * @return 结果
     */
    @Override
    public int deleteZsBomLineByBomLineId(Long bomLineId) {
        return zsBomLineMapper.deleteZsBomLineByBomLineId(bomLineId);
    }
}