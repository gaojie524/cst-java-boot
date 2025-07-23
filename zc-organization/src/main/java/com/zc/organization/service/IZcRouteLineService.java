package com.zc.organization.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.organization.domain.ZcRouteLine;

/**
 * 工艺路线行Service接口
 *
 * @author zc
 * @date 2025-07-17
 */
public interface IZcRouteLineService extends IService<ZcRouteLine> {
    /**
     * 查询工艺路线行
     *
     * @param routeLineId 工艺路线行主键
     * @return 工艺路线行
     */
    public ZcRouteLine selectZcRouteLineByRouteLineId(Long routeLineId);

    /**
     * 查询工艺路线行列表
     *
     * @param zcRouteLine 工艺路线行
     * @return 工艺路线行集合
     */
    public List<ZcRouteLine> selectZcRouteLineList(ZcRouteLine zcRouteLine);

    /**
     * 新增工艺路线行
     *
     * @param zcRouteLine 工艺路线行
     * @return 结果
     */
    public int insertZcRouteLine(ZcRouteLine zcRouteLine);

    /**
     * 修改工艺路线行
     *
     * @param zcRouteLine 工艺路线行
     * @return 结果
     */
    public int updateZcRouteLine(ZcRouteLine zcRouteLine);

    /**
     * 批量删除工艺路线行
     *
     * @param routeLineIds 需要删除的工艺路线行主键集合
     * @return 结果
     */
    public int deleteZcRouteLineByRouteLineIds(Long[] routeLineIds);

    /**
     * 删除工艺路线行信息
     *
     * @param routeLineId 工艺路线行主键
     * @return 结果
     */
    public int deleteZcRouteLineByRouteLineId(Long routeLineId);
}