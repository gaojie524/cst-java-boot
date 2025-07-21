package com.zc.organization.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.organization.domain.ZsRouteHead;
import com.zc.organization.mapper.ZsRouteHeadMapper;
import com.zc.organization.service.IZsRouteHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 工艺路线头Service业务层处理
 *
 * @author zc
 */
@Service
public class ZsRouteHeadServiceImpl extends ServiceImpl<ZsRouteHeadMapper, ZsRouteHead> implements IZsRouteHeadService {
    @Autowired
    private ZsRouteHeadMapper zsRouteHeadMapper;

    /**
     * 查询工艺路线头
     *
     * @param routeHeadId 工艺路线头主键
     * @return 工艺路线头
     */
    @Override
    public ZsRouteHead selectZsRouteHeadByRouteHeadId(Long routeHeadId) {
        return zsRouteHeadMapper.selectZsRouteHeadByRouteHeadId(routeHeadId);
    }

    /**
     * 查询工艺路线头列表
     *
     * @param zsRouteHead 工艺路线头
     * @return 工艺路线头
     */
    @Override
    public List<ZsRouteHead> selectZsRouteHeadList(ZsRouteHead zsRouteHead) {
        return zsRouteHeadMapper.selectZsRouteHeadList(zsRouteHead);
    }

    /**
     * 新增工艺路线头
     *
     * @param zsRouteHead 工艺路线头
     * @return 结果
     */
    @Override
    public int insertZsRouteHead(ZsRouteHead zsRouteHead) {
            return zsRouteHeadMapper.insert(zsRouteHead);
    }

    /**
     * 修改工艺路线头
     *
     * @param zsRouteHead 工艺路线头
     * @return 结果
     */
    @Override
    public int updateZsRouteHead(ZsRouteHead zsRouteHead) {
        return zsRouteHeadMapper.updateById(zsRouteHead);

    }

    /**
     * 批量删除工艺路线头
     *
     * @param routeHeadIds 需要删除的工艺路线头主键
     * @return 结果
     */
    @Override
    public int deleteZsRouteHeadByRouteHeadIds(Long[] routeHeadIds) {
        return zsRouteHeadMapper.deleteZsRouteHeadByRouteHeadIds(routeHeadIds);
    }

    /**
     * 删除工艺路线头信息
     *
     * @param routeHeadId 工艺路线头主键
     * @return 结果
     */
    @Override
    public int deleteZsRouteHeadByRouteHeadId(Long routeHeadId) {
        return zsRouteHeadMapper.deleteZsRouteHeadByRouteHeadId(routeHeadId);
    }
}