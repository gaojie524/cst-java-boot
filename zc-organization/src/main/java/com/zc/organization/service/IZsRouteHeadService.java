package com.zc.organization.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.organization.domain.ZsRouteHead;

/**
 * 工艺路线头Service接口
 *
 * @author zc
 * @date 2025-07-17
 */
public interface IZsRouteHeadService extends IService<ZsRouteHead> {
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
     * 新增工艺路线头
     *
     * @param zsRouteHead 工艺路线头
     * @return 结果
     */
    public int insertZsRouteHead(ZsRouteHead zsRouteHead);

    /**
     * 修改工艺路线头
     *
     * @param zsRouteHead 工艺路线头
     * @return 结果
     */
    public int updateZsRouteHead(ZsRouteHead zsRouteHead);

    /**
     * 批量删除工艺路线头
     *
     * @param routeHeadIds 需要删除的工艺路线头主键集合
     * @return 结果
     */
    public int deleteZsRouteHeadByRouteHeadIds(Long[] routeHeadIds);

    /**
     * 删除工艺路线头信息
     *
     * @param routeHeadId 工艺路线头主键
     * @return 结果
     */
    public int deleteZsRouteHeadByRouteHeadId(Long routeHeadId);
}