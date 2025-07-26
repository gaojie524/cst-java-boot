package com.zc.documenter.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zc.documenter.domain.ZcRouteHead;

/**
 * 工艺路线头Service接口
 *
 * @author zc
 * @date 2025-07-17
 */
public interface IZcRouteHeadService extends IService<ZcRouteHead> {
    /**
     * 查询工艺路线头
     *
     * @param routeHeadId 工艺路线头主键
     * @return 工艺路线头
     */
    public ZcRouteHead selectZcRouteHeadByRouteHeadId(Long routeHeadId);

    /**
     * 查询工艺路线头列表
     *
     * @param zcRouteHead 工艺路线头
     * @return 工艺路线头集合
     */
    public List<ZcRouteHead> selectZcRouteHeadList(ZcRouteHead zcRouteHead);

    /**
     * 新增工艺路线头
     *
     * @param zcRouteHead 工艺路线头
     * @return 结果
     */
    public int insertZcRouteHead(ZcRouteHead zcRouteHead);

    /**
     * 修改工艺路线头
     *
     * @param zcRouteHead 工艺路线头
     * @return 结果
     */
    public int updateZcRouteHead(ZcRouteHead zcRouteHead);

    /**
     * 批量删除工艺路线头
     *
     * @param routeHeadIds 需要删除的工艺路线头主键集合
     * @return 结果
     */
    public int deleteZcRouteHeadByRouteHeadIds(Long[] routeHeadIds);

    /**
     * 删除工艺路线头信息
     *
     * @param routeHeadId 工艺路线头主键
     * @return 结果
     */
    public int deleteZcRouteHeadByRouteHeadId(Long routeHeadId);
}