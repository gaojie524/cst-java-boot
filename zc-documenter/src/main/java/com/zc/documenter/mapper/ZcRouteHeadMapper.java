package com.zc.documenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.common.core.domain.entity.SysUser;
import com.zc.documenter.domain.ZcRouteHead;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 工艺路线头Mapper接口
 *
 * @author zc
 * @date 2025-07-17
 */
@Mapper
public interface ZcRouteHeadMapper extends BaseMapper<ZcRouteHead> {
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
     * 删除工艺路线头
     *
     * @param routeHeadId 工艺路线头主键
     * @return 结果
     */
    public int deleteZcRouteHeadByRouteHeadId(Long routeHeadId);

    /**
     * 批量删除工艺路线头
     *
     * @param routeHeadIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZcRouteHeadByRouteHeadIds(Long[] routeHeadIds);


    /**
     * 修改工艺路线信息
     *
     * @param zcRouteHead 工艺路线信息
     * @return 结果
     */
    public int updateRouteHead(ZcRouteHead zcRouteHead);
}