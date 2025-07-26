package com.zc.documenter.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.documenter.domain.ZcRouteLine;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工艺路线行Mapper接口
 *
 * @author zc
 * @date 2025-07-17
 */
@Mapper
public interface ZcRouteLineMapper extends BaseMapper<ZcRouteLine> {
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
     * 删除工艺路线行
     *
     * @param routeLineId 工艺路线行主键
     * @return 结果
     */
    public int deleteZcRouteLineByRouteLineId(Long routeLineId);

    /**
     * 批量删除工艺路线行
     *
     * @param routeLineIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZcRouteLineByRouteLineIds(Long[] routeLineIds);
}