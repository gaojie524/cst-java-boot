package com.zc.organization.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.organization.domain.ZsRouteHead;

import java.util.List;


/**
 * 工艺路线头Mapper接口
 *
 * @author zc
 * @date 2025-07-17
 */
public interface ZsRouteHeadMapper extends BaseMapper<ZsRouteHead> {
    /**
     * 查询工艺路线头
     *
     * @param routeHeadId 工艺路线头主键
     * @return 工艺路线头
     */
    public ZsRouteHead selectZsRouteHeadByRouteHeadId(Long routeHeadId);

    /**
     * 查询工艺路线头列表
     *
     * @param zsRouteHead 工艺路线头
     * @return 工艺路线头集合
     */
    public List<ZsRouteHead> selectZsRouteHeadList(ZsRouteHead zsRouteHead);





    /**
     * 删除工艺路线头
     *
     * @param routeHeadId 工艺路线头主键
     * @return 结果
     */
    public int deleteZsRouteHeadByRouteHeadId(Long routeHeadId);

    /**
     * 批量删除工艺路线头
     *
     * @param routeHeadIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZsRouteHeadByRouteHeadIds(Long[] routeHeadIds);
}
