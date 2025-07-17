package com.zc.organization.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.organization.domain.ZsRouteLine;


/**
 * 工艺路线行Mapper接口
 *
 * @author zc
 * @date 2025-07-17
 */
public interface ZsRouteLineMapper extends BaseMapper<ZsRouteLine> {
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
     * 删除工艺路线行
     *
     * @param routeLineId 工艺路线行主键
     * @return 结果
     */
    public int deleteZsRouteLineByRouteLineId(Long routeLineId);

    /**
     * 批量删除工艺路线行
     *
     * @param routeLineIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZsRouteLineByRouteLineIds(Long[] routeLineIds);
}
