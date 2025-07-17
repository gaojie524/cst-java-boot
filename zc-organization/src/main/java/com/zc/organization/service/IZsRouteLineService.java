package com.zc.organization.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.organization.domain.ZsRouteLine;

/**
 * 工艺路线行Service接口
 *
 * @author zc
 * @date 2025-07-17
 */
public interface IZsRouteLineService extends IService<ZsRouteLine> {
    /**
     * 查询工艺路线行
     *
     * @param routeLineId 工艺路线行主键
     * @return 工艺路线行
     */
    public ZsRouteLine selectZsRouteLineByRouteLineId(Long routeLineId);

    /**
     * 查询工艺路线行列表
     *
     * @param zsRouteLine 工艺路线行
     * @return 工艺路线行集合
     */
    public List<ZsRouteLine> selectZsRouteLineList(ZsRouteLine zsRouteLine);

    /**
     * 新增工艺路线行
     *
     * @param zsRouteLine 工艺路线行
     * @return 结果
     */
    public int insertZsRouteLine(ZsRouteLine zsRouteLine);

    /**
     * 修改工艺路线行
     *
     * @param zsRouteLine 工艺路线行
     * @return 结果
     */
    public int updateZsRouteLine(ZsRouteLine zsRouteLine);

    /**
     * 批量删除工艺路线行
     *
     * @param routeLineIds 需要删除的工艺路线行主键集合
     * @return 结果
     */
    public int deleteZsRouteLineByRouteLineIds(Long[] routeLineIds);

    /**
     * 删除工艺路线行信息
     *
     * @param routeLineId 工艺路线行主键
     * @return 结果
     */
    public int deleteZsRouteLineByRouteLineId(Long routeLineId);
}