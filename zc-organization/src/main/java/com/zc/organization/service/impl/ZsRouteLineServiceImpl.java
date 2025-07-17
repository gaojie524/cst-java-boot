package com.zc.organization.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;

import com.zc.organization.domain.ZsRouteLine;
import com.zc.organization.mapper.ZsRouteLineMapper;
import com.zc.organization.service.IZsRouteLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 工艺路线行Service业务层处理
 *
 * @author zc
 * @date 2025-07-17
 */
@Service
public class ZsRouteLineServiceImpl extends ServiceImpl<ZsRouteLineMapper, ZsRouteLine> implements IZsRouteLineService {
    @Autowired
    private ZsRouteLineMapper zsRouteLineMapper;

    /**
     * 查询工艺路线行
     *
     * @param routeLineId 工艺路线行主键
     * @return 工艺路线行
     */
    @Override
    public ZsRouteLine selectZsRouteLineByRouteLineId(Long routeLineId) {
        return zsRouteLineMapper.selectZsRouteLineByRouteLineId(routeLineId);
    }

    /**
     * 查询工艺路线行列表
     *
     * @param zsRouteLine 工艺路线行
     * @return 工艺路线行
     */
    @Override
    public List<ZsRouteLine> selectZsRouteLineList(ZsRouteLine zsRouteLine) {
        return zsRouteLineMapper.selectZsRouteLineList(zsRouteLine);
    }

    /**
     * 新增工艺路线行
     *
     * @param zsRouteLine 工艺路线行
     * @return 结果
     */
    @Override
    public int insertZsRouteLine(ZsRouteLine zsRouteLine) {
            return zsRouteLineMapper.insert(zsRouteLine);
    }

    /**
     * 修改工艺路线行
     *
     * @param zsRouteLine 工艺路线行
     * @return 结果
     */
    @Override
    public int updateZsRouteLine(ZsRouteLine zsRouteLine) {
        return zsRouteLineMapper.updateById(zsRouteLine);

    }

    /**
     * 批量删除工艺路线行
     *
     * @param routeLineIds 需要删除的工艺路线行主键
     * @return 结果
     */
    @Override
    public int deleteZsRouteLineByRouteLineIds(Long[] routeLineIds) {
        return zsRouteLineMapper.deleteZsRouteLineByRouteLineIds(routeLineIds);
    }

    /**
     * 删除工艺路线行信息
     *
     * @param routeLineId 工艺路线行主键
     * @return 结果
     */
    @Override
    public int deleteZsRouteLineByRouteLineId(Long routeLineId) {
        return zsRouteLineMapper.deleteZsRouteLineByRouteLineId(routeLineId);
    }
}