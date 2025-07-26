package com.zc.documenter.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.common.core.domain.entity.SysUser;
import com.zc.documenter.domain.ZcRouteHead;
import com.zc.documenter.mapper.ZcRouteHeadMapper;
import com.zc.documenter.service.IZcRouteHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 工艺路线头Service业务层处理
 *
 * @author zc
 */
@Service
public class ZcRouteHeadServiceImpl extends ServiceImpl<ZcRouteHeadMapper, ZcRouteHead> implements IZcRouteHeadService {
    @Autowired
    private ZcRouteHeadMapper zcRouteHeadMapper;

    /**
     * 查询工艺路线头
     *
     * @param routeHeadId 工艺路线头主键
     * @return 工艺路线头
     */
    @Override
    public ZcRouteHead selectZcRouteHeadByRouteHeadId(Long routeHeadId) {
        return zcRouteHeadMapper.selectZcRouteHeadByRouteHeadId(routeHeadId);
    }

    /**
     * 查询工艺路线头列表
     *
     * @param zcRouteHead 工艺路线头
     * @return 工艺路线头
     */
    @Override
    public List<ZcRouteHead> selectZcRouteHeadList(ZcRouteHead zcRouteHead) {
        return zcRouteHeadMapper.selectZcRouteHeadList(zcRouteHead);
    }

    /**
     * 新增工艺路线头
     *
     * @param zcRouteHead 工艺路线头
     * @return 结果
     */
    @Override
    public int insertZcRouteHead(ZcRouteHead zcRouteHead) {
            return zcRouteHeadMapper.insert(zcRouteHead);
    }

    /**
     * 修改工艺路线头
     *
     * @param zcRouteHead 工艺路线头
     * @return 结果
     */
    @Override
    public int updateZcRouteHead(ZcRouteHead zcRouteHead) {
        return zcRouteHeadMapper.updateById(zcRouteHead);

    }

    /**
     * 批量删除工艺路线头
     *
     * @param routeHeadIds 需要删除的工艺路线头主键
     * @return 结果
     */
    @Override
    public int deleteZcRouteHeadByRouteHeadIds(Long[] routeHeadIds) {
        return zcRouteHeadMapper.deleteZcRouteHeadByRouteHeadIds(routeHeadIds);
    }

    /**
     * 删除工艺路线头信息
     *
     * @param routeHeadId 工艺路线头主键
     * @return 结果
     */
    @Override
    public int deleteZcRouteHeadByRouteHeadId(Long routeHeadId) {
        return zcRouteHeadMapper.deleteZcRouteHeadByRouteHeadId(routeHeadId);
    }

    /**
     * 修改工艺路线状态
     *
     * @param zcRouteHead 用户信息
     * @return 结果
     */
    @Override
    public int updateRouteHeadStatus(ZcRouteHead zcRouteHead) {
        return zcRouteHeadMapper.updateRouteHead(zcRouteHead);
    }
}