package com.zc.documenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zc.documenter.mapper.ZcRouteLineMapper;
import com.zc.documenter.domain.ZcRouteLine;
import com.zc.documenter.service.IZcRouteLineService;

/**
 * 工艺路线行Service业务层处理
 *
 * @author zc
 * @date 2025-07-26
 */
@Service
public class ZcRouteLineServiceImpl extends ServiceImpl<ZcRouteLineMapper, ZcRouteLine> implements IZcRouteLineService {
    @Autowired
    private ZcRouteLineMapper zcRouteLineMapper;

    /**
     * 查询工艺路线行
     *
     * @param routeLineId 工艺路线行主键
     * @return 工艺路线行
     */
    @Override
    public ZcRouteLine selectZcRouteLineByRouteLineId(Long routeLineId) {
        return zcRouteLineMapper.selectZcRouteLineByRouteLineId(routeLineId);
    }

    /**
     * 查询工艺路线行列表
     *
     * @param zcRouteLine 工艺路线行
     * @return 工艺路线行
     */
    @Override
    public List<ZcRouteLine> selectZcRouteLineList(ZcRouteLine zcRouteLine) {
        return zcRouteLineMapper.selectZcRouteLineList(zcRouteLine);
    }

    /**
     * 新增工艺路线行
     *
     * @param zcRouteLine 工艺路线行
     * @return 结果
     */
    @Override
    public int insertZcRouteLine(ZcRouteLine zcRouteLine) {
            return zcRouteLineMapper.insert(zcRouteLine);
    }

    /**
     * 修改工艺路线行
     *
     * @param zcRouteLine 工艺路线行
     * @return 结果
     */
    @Override
    public int updateZcRouteLine(ZcRouteLine zcRouteLine) {
        return zcRouteLineMapper.updateById(zcRouteLine);

    }

    /**
     * 批量删除工艺路线行
     *
     * @param routeLineIds 需要删除的工艺路线行主键
     * @return 结果
     */
    @Override
    public int deleteZcRouteLineByRouteLineIds(Long[] routeLineIds) {
        return zcRouteLineMapper.deleteZcRouteLineByRouteLineIds(routeLineIds);
    }

    /**
     * 删除工艺路线行信息
     *
     * @param routeLineId 工艺路线行主键
     * @return 结果
     */
    @Override
    public int deleteZcRouteLineByRouteLineId(Long routeLineId) {
        return zcRouteLineMapper.deleteZcRouteLineByRouteLineId(routeLineId);
    }
}