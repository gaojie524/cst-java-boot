package com.zc.organization.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zc.common.annotation.Excel;
import com.zc.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 工艺路线头对象 zs_route_head
 *
 * @author zc
 * @date 2025-07-17
 */
@Data
@TableName("zs_route_head")
public class ZsRouteHead extends BaseEntity {
    private static final long serialVersionUID=1L;


    /** 工艺路线ID */
    @TableId(value = "route_head_id",type = IdType.AUTO)
    private Long routeHeadId;

    /** 工艺路线编号 */
    @Excel(name = "工艺路线编号")
    private String routeCode;

    /** 工艺路线版本号   工艺路线编号+工艺路线版本号 */
    @Excel(name = "工艺路线版本号   工艺路线编号+工艺路线版本号")
    private String routeVersion;

    /** 工艺路线描述 */
    @Excel(name = "工艺路线描述")
    private String routeDesc;

    /** 工艺路线状态 */
    @Excel(name = "工艺路线状态")
    private String routeStatus;

    /** 创建方式 */
    @Excel(name = "创建方式")
    private String creationMethod;

    /** 备注1 */
    @Excel(name = "备注1")
    private String remark1;

    /** 备注2 */
    @Excel(name = "备注2")
    private String remark2;

    /** 备注3 */
    @Excel(name = "备注3")
    private String remark3;

    /** 备注4 */
    @Excel(name = "备注4")
    private String remark4;

    /** 备注5 */
    @Excel(name = "备注5")
    private String remark5;

    /** 最后更新标识 */
    private String lastUpdateFlag;

    /** 删除标识   0删除  1查看 */
    private Integer deleteId;


}
